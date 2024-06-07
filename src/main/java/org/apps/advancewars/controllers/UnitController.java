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
    private MapController mapController;

    public UnitController(GridPane gameGridPane, int tileSize, MapController mapController) {
        this.gameGridPane = gameGridPane;
        this.TILE_SIZE = tileSize;
        this.units = new HashMap<>();
        this.mapController = mapController;
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

        if (selectedUnit == null) {
            // Select a unit if one is at the clicked position
            selectedUnit = getUnitAt(row, col);
        } else {
            // Try to move the selected unit
            if (canMoveTo(selectedUnit, row, col)) {
                moveUnit(selectedUnit, row, col);
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
        // Check if there is no other unit at the destination
        if (getUnitAt(row, col) != null) {
            return false;
        }

        // Check if the unit can move to the destination within its movement range
        if (!unit.canMoveTo(row, col)) {
            return false;
        }

        // Check if the terrain is passable
        Terrain terrain = mapController.getTerrainAt(row, col);
        // Check movement cost based on terrain
        int movementCost = unit.getMovementCost(terrain);
        if (unit.isAirUnit()) {
            return terrain.isPassableByAirUnits() && unit.getMovementRange() >= movementCost;
        } else if (unit.isInfantry()) {
            return terrain.isPassableByInfantry() && unit.getMovementRange() >= movementCost;
        } else {
            return terrain.isPassableByGroundUnits() && unit.getMovementRange() >= movementCost;
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
    }
}
