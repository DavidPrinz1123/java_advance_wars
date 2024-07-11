package org.apps.advancewars.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.apps.advancewars.units.unit;
import org.apps.advancewars.terrain.Terrain;

import java.util.HashMap;
import java.util.Map;

import static org.apps.advancewars.MainApp.showVictoryScreen;

public class UnitController {
    private final int TILE_SIZE;
    private GridPane gameGridPane;
    private Map<String, unit> units;
    private unit selectedUnit;
    private unit selectedField;
    private MapController mapController;
    private GameSceneController gameSceneController;
    private String player = "teamred";

    public UnitController(GridPane gameGridPane, int tileSize, MapController mapController) {
        this.gameGridPane = gameGridPane;
        this.TILE_SIZE = tileSize;
        this.units = new HashMap<>();
        this.mapController = mapController;
    }

    public void setGameSceneController(GameSceneController gameSceneController) {
        this.gameSceneController = gameSceneController;
    }

    public void placeUnit(unit unit, int row, int col) {
        units.put(unit.getName() + row + "_" + col, unit);
        unit.setPosition(row, col);
        ImageView imageView = unit.getImageView();
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        gameGridPane.add(imageView, col, row);
    }

    public void handleMouseClick(MouseEvent event) {
        int col = (int) (event.getX() / TILE_SIZE);
        int row = (int) (event.getY() / TILE_SIZE);
        System.out.println("Clicked on: (" + row + ", " + col + ")");

        if (selectedUnit == null && getUnitAt(row, col) != null) {
            if (getUnitAt(row, col).getTeam().equals(player)) {
                selectedUnit = getUnitAt(row, col);
                System.out.println("Selected unit: " + selectedUnit.getName());
            }
        } else if (selectedUnit != null) {
            selectedField = getUnitAt(row, col);

            if (selectedField == null) {
                if (canMoveTo(selectedUnit, row, col) && !selectedUnit.getMovementBlocked()) {
                    System.out.println("Moving unit: " + selectedUnit.getName() + " to (" + row + ", " + col + ")");
                    moveUnit(selectedUnit, row, col);
                }
                selectedUnit = null;
                selectedField = null;
            } else if (!selectedField.getTeam().equals(player)) {
                System.out.println("Attempting attack on unit: " + selectedField.getName());
                if (selectedUnit.canAttack(selectedField, row, col) && !selectedUnit.getAttackBlocked()) {
                    System.out.println("Attacking unit: " + selectedField.getName());
                    attack(selectedField, selectedUnit);
                } else {
                    System.out.println("Cannot attack unit: " + selectedField.getName());
                }
                selectedUnit = null;
                selectedField = null;
            } else if (selectedField.getTeam().equals(player)) {
                selectedUnit = selectedField;
                selectedField = null;
            }
            if (areAllUnitsBlocked() && !canAnyAttack()) {
                changePlayer();
            }
            selectedUnit = null;
        }
    }

    private unit getUnitAt(int row, int col) {
        return units.values().stream()
                .filter(unit -> unit.getRow() == row && unit.getCol() == col)
                .findFirst()
                .orElse(null);
    }

    private boolean canMoveTo(unit unit, int row, int col) {
        if (getUnitAt(row, col) != null) {
            return false;
        }
        Terrain terrain = mapController.getTerrainAt(row, col);
        int movementCost = unit.getMovementCost(terrain);
        return unit.getMovementRange() >= movementCost && unit.canMoveTo(row, col, terrain);
    }

    private void moveUnit(unit unit, int newRow, int newCol) {
        units.remove(unit.getName() + unit.getRow() + "_" + unit.getCol());
        gameGridPane.getChildren().remove(unit.getImageView());
        unit.move(newRow, newCol);
        gameGridPane.add(unit.getImageView(), newCol, newRow);
        units.put(unit.getName() + newRow + "_" + newCol, unit);
        unit.setMovementBlocked(true);
    }

    public boolean areAllUnitsBlocked() {
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit unit = entry.getValue();
            if (unit.getTeam().equals(player) && !unit.getMovementBlocked()) {
                return false;
            }
        }
        return true;
    }

    public void unblockUnits() {
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit unit = entry.getValue();
            if (unit.getTeam().equals(player)) {
                unit.reset();
            }
        }
    }

    public void changePlayer() {
        if (player.equals("teamred")) {
            player = "teamblue";
            if (checkRedWinner()) {
                showVictoryScreen("teamred");
            }
        } else if (player.equals("teamblue")) {
            player = "teamred";
            if (checkBlueWinner()) {
                showVictoryScreen("teamblue");
            }
        }
        unblockUnits();
    }

    private boolean checkRedWinner() {
        return units.values().stream().noneMatch(unit -> unit.getTeam().equals("teamblue"));
    }

    private boolean checkBlueWinner() {
        return units.values().stream().noneMatch(unit -> unit.getTeam().equals("teamred"));
    }

    public void attack(unit defensive, unit offensive) {
        System.out.println("Executing attack from " + offensive.getName() + " on " + defensive.getName());
        String attackedUnit = defensive.getName();
        double modifier = 1.0;
        switch (attackedUnit) {
            case "Anti Air":
                modifier = offensive.getAntiAirModifier();
                break;
            case "BattleCopter":
                modifier = offensive.getBattleCopterModifier();
                break;
            case "Bomber":
                modifier = offensive.getBomberModifier();
                break;
            case "Fighter":
                modifier = offensive.getFighterModifier();
                break;
            case "Infantry":
                modifier = offensive.getInfantryModifier();
                break;
            case "Mechanized Infantry":
                modifier = offensive.getMechanizedInfantryModifier();
                break;
            case "Mobile Artillery":
                modifier = offensive.getMobileArtilleryModifier();
                break;
            case "Tank":
                modifier = offensive.getTankModifier();
                break;
            default:
                break;
        }
        double attackPower = offensive.getAttackPower() * modifier;
        int roundedAttackPower = (int) attackPower;
        defensive.setHealth(defensive.getHealth() - roundedAttackPower);
        System.out.println("Defensive unit " + defensive.getName() + " health after attack: " + defensive.getHealth());
        offensive.setAttackBlocked(true);
        if (defensive.getHealth() <= 0) {
            removeUnit(defensive);
            checkVictory();
        }
    }

    private void removeUnit(unit unit) {
        String unitKey = unit.getName() + unit.getRow() + "_" + unit.getCol();
        units.remove(unitKey);
        gameGridPane.getChildren().remove(unit.getImageView());
        System.out.println("Unit removed: " + unitKey);
    }

    private void checkVictory() {
        if (checkRedWinner()) {
            showVictoryScreen("teamred");
        } else if (checkBlueWinner()) {
            showVictoryScreen("teamblue");
        }
    }

    public boolean canAnyAttack() {
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit ownUnit = entry.getValue();
            if (ownUnit.getTeam().equals(player) && !ownUnit.getAttackBlocked()) {
                int row = ownUnit.getRow();
                int col = ownUnit.getCol();
                int minAttackRange = ownUnit.getMinAttackRange();
                int maxAttackRange = ownUnit.getMaxAttackRange();
                for (int i = -maxAttackRange; i <= maxAttackRange; i++) {
                    for (int j = -maxAttackRange; j <= maxAttackRange; j++) {
                        if (Math.abs(i) + Math.abs(j) < minAttackRange || Math.abs(i) + Math.abs(j) > maxAttackRange)
                            continue;
                        int targetRow = row + i;
                        int targetCol = col + j;
                        if (mapController.isWithinBounds(targetRow, targetCol)) {
                            unit targetUnit = getUnitAt(targetRow, targetCol);
                            if (targetUnit != null && !targetUnit.getTeam().equals(player)) {
                                if (ownUnit.canAttack(targetUnit, targetRow, targetCol)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String makeInfoString(unit infoUnit) {
        String unitName = "Einheit : " + infoUnit.getName();
        String unitHealth = "Besitzt : " + infoUnit.getHealth() + " Leben ! ";
        String unitPower = "Hat eine Angriffsstärke von : " + infoUnit.getAttackPower() + " Punkten ";
        String unitMovement = "Kann auf normalem Gebiet " + infoUnit.getMovementRange() + " Felder gehen ";
        String unitOperationArea;
        if (infoUnit.canAttackGroundUnit() && !infoUnit.canAttackAirUnit()) {
            unitOperationArea = "Wird gegen Bodentruppen eingesetzt ";
        } else if (!infoUnit.canAttackGroundUnit() && infoUnit.canAttackAirUnit()) {
            unitOperationArea = "Wird gegen Lufttruppen eingesetzt ";
        } else {
            unitOperationArea = "Wird gegen Luft - und Bodentruppen eingesetzt ";
        }
        return unitName + "\n" +
                unitHealth + "\n" +
                unitPower + "\n" +
                unitMovement + "\n" +
                unitOperationArea;
    }
}
