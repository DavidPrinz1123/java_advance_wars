package org.apps.advancewars.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.apps.advancewars.units.unit;
import org.apps.advancewars.terrain.Terrain;

import java.util.HashMap;
import java.util.Map;

public class UnitController {
    private final int TILE_SIZE;
    private GridPane gameGridPane;
    private Map<String, unit> units;
    private unit selectedUnit;
    private unit selectedField;
    private MapController mapController;
    private GameSceneController gameScene = new GameSceneController();
    private GameHUDController gHC;
    private String player = "teamred";

    public UnitController(GridPane gameGridPane, int tileSize, MapController mapController) {
        this.gameGridPane = gameGridPane;
        this.TILE_SIZE = tileSize;
        this.units = new HashMap<>();
        this.mapController = mapController;
        this.gHC = gameScene.getHUDcontroller();
    }

    public void placeUnit(unit unit, int row, int col) {
        units.put(unit.getName() + row + "_" + col, unit); // Unique key for each unit
        unit.setPosition(row, col);
        ImageView imageView = unit.getImageView();
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        gameGridPane.add(imageView, col, row);
    }

    public void handleMouseClick(MouseEvent event) {
        int col = (int) (event.getX() / TILE_SIZE);
        int row = (int) (event.getY() / TILE_SIZE);


        if (selectedUnit == null && getUnitAt(row, col) != null) {
            if (getUnitAt(row, col).getTeam() == player) {
                selectedUnit = getUnitAt(row, col);
            }
//Anzeigen von Daten
         //   if(player.equals("teamred")){
          //      gHC.updateTeamRedInfo(makeInfoString(selectedUnit));
          //  }else{
           //     gHC.updateTeamBlueInfo(makeInfoString(selectedUnit));
          //  }
        } else if (selectedUnit != null) {
            selectedField = getUnitAt(row, col);
            //Anzeigen von Daten
        //    if(player.equals("teamred")){
        //        gHC.updateTeamBlueInfo(makeInfoString(selectedUnit));
        //    }else{
           //     gHC.updateTeamRedInfo(makeInfoString(selectedUnit));
         //   }

            if (selectedField == null && canMoveTo(selectedUnit, row, col) && !selectedUnit.getMovementBlocked()) {
                //bewegen

                moveUnit(selectedUnit, row, col);
                selectedUnit = null;
                selectedField = null;

            } else if (selectedField != null && selectedField.getTeam() != player && selectedUnit.canAttack(selectedField, row, col) && !selectedUnit.getAttackBlocked()) {
                //angreifen

                attack(selectedField, selectedUnit);
                selectedUnit = null;
                selectedField = null;
            } else if (selectedField != null && selectedField.getTeam() == player) {
                selectedUnit = selectedField;
                selectedField = null;
            }
            if (areAllUnitsBlocked() && !canAnyAttack()) {
                changePlayer();
            }
            selectedUnit = null; // Deselect the unit after moving
        }
    }

    private unit getUnitAt(int row, int col) {
        return units.values().stream()
                .filter(unit -> unit.getRow() == row && unit.getCol() == col)
                .findFirst()
                .orElse(null);
    }

    private boolean canMoveTo(unit unit, int row, int col) {
        Terrain terrain = mapController.getTerrainAt(row, col);
        // Check if there is no other unit at the destination
        //ToDo überlegen wie man dijkstra implementiert
        // Check movement cost based on terrain
        int movementCost = unit.getMovementCost(terrain);
        if (unit.isAirUnit()) {
            return unit.getMovementRange() >= movementCost&&unit.canMoveTo(row,col,terrain);
        } else if (unit.isGroundUnit()) {
            return unit.getMovementRange() >= movementCost&&unit.canMoveTo(row,col,terrain);
        } else {
            return unit.getMovementRange() >= movementCost&&unit.canMoveTo(row,col,terrain);
        }
    }


    private void moveUnit(unit unit, int newRow, int newCol) {
        // Remove the unit from the old position in the map
        units.remove(unit.getName() + unit.getRow() + "_" + unit.getCol());

        // Update the UI
        gameGridPane.getChildren().remove(unit.getImageView());
        unit.move(newRow, newCol);
        gameGridPane.add(unit.getImageView(), newCol, newRow);

        // Add the unit to the new position in the map
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

        } else if (player.equals("teamblue")) {
            player = "teamred";
        }
        unblockUnits();
    }

    public void attack(unit defensive, unit offensive) {
        String attackedUnit = defensive.getName();
        double modifier = 0;
        switch (attackedUnit) {
            case "Anti Air": modifier = offensive.getAntiAirModifier();
                break;
            case "BattleCopter": modifier = offensive.getBattleCopterModifier();
                break;
            case "Bomber": modifier = offensive.getBomberModifier();
                break;
            case "Fighter": modifier = offensive.getFighterModifier();
                break;
            case "Infantry": modifier = offensive.getInfantryModifier();
                break;
            case "Mechanized Infantry": modifier = offensive.getMechanizedInfantryModifier();
                break;
            case "Mobile Artillery": modifier = offensive.getMobileArtilleryModifier();
                break;
            case "Tank": modifier = offensive.getTankModifier();
                break;
            default:
                break;
        }
        double attackpower = offensive.getAttackPower()*modifier;
        int roundedAttackPower = (int) attackpower;
        defensive.setHealth(defensive.getHealth() - roundedAttackPower);
        offensive.setAttackBlocked(true);
        if(defensive.getHealth() <= 0) {
            units.remove(defensive.getName() + defensive.getRow() + "_" + defensive.getCol(), defensive);
        }
    }

    public boolean canAnyAttack() {
        // Durchsuche alle Einheiten auf der Karte
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit ownUnit = entry.getValue();


            if (ownUnit.getTeam().equals(player) && !ownUnit.getAttackBlocked()) {
                int row = ownUnit.getRow();
                int col = ownUnit.getCol();
                int minAttackRange = ownUnit.getMinAttackRange();
                int maxAttackRange = ownUnit.getMaxAttackRange();

                // Prüfe in alle Richtungen innerhalb der Angriffsreichweite
                for (int i = -maxAttackRange; i <= maxAttackRange; i++) {
                    for (int j = -maxAttackRange; j <= maxAttackRange; j++) {
                        if (Math.abs(i) + Math.abs(j) < minAttackRange || Math.abs(i) + Math.abs(j) > maxAttackRange)
                            continue; // Angriffsreichweite prüfen

                        int targetRow = row + i;
                        int targetCol = col + j;

                        // Überprüfen, ob die Zielposition innerhalb der Grenzen der Karte liegt
                        if (mapController.isWithinBounds(targetRow, targetCol)) {
                            unit targetUnit = getUnitAt(targetRow, targetCol);

                            // Prüfe, ob die Zielposition eine gegnerische Einheit enthält
                            if (targetUnit != null && !targetUnit.getTeam().equals(player) && ownUnit.canAttack(targetUnit, targetRow, targetCol)) {
                                return true; // Eine Einheit kann angreifen
                            }
                        }
                    }
                }
            }
        }
        return false; // Keine Einheit kann angreifen
    }
    public String makeInfoString(unit infoUnit){
        String infoString = "";
        String unitName = "Einheit : " + infoUnit.getName();
        String unitHealth = "Besitzt : " + infoUnit.getHealth() + " Leben ! ";
        String unitPower = "Hat eine Angriffsstärke von : " + infoUnit.getAttackPower() + " Punkten ";
        String unitMovement = "Kann auf normalem Gebiet " + infoUnit.getMovementRange() + " Felder gehen ";
        String unitOperationArea;
        if(infoUnit.canAttackGroundUnit()&&!infoUnit.canAttackAirUnit()){
            unitOperationArea = "Wird gegen Bodentruppen eingesetzt ";
        }
        else if(!infoUnit.canAttackGroundUnit()&&infoUnit.canAttackAirUnit()){
            unitOperationArea = "Wird gegen Lufttruppen eingesetzt ";
        }
        else{
            unitOperationArea = "Wird gegen Luft - und Bodentruppen eingesetzt ";
        }
        infoString = unitName + "\n" +
                unitHealth + "\n" +
                unitPower + "\n" +
                unitMovement + "\n" +
                unitOperationArea;
        return infoString;
    }
}