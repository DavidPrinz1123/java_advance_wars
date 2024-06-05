package org.apps.advancewars.controllers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apps.advancewars.maps.EonSprings;
import org.apps.advancewars.maps.LittleIsland;
import org.apps.advancewars.maps.PistonDam;
import org.apps.advancewars.units.Infantry;
import org.apps.advancewars.units.Tank;
import org.apps.advancewars.units.unit;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GameSceneController {

    private final int TILE_SIZE = 48; // Base size of each tile
    private GridPane gameGridPane;
    private Map<String, unit> units;
    private unit selectedUnit;

    public void setMapLayout(String mapName, Stage stage) {
        gameGridPane = new GridPane();
        units = new HashMap<>();
        String[][] layout = getMapLayout(mapName);

        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                String terrain = layout[row][col];
                ImageView imageView = new ImageView(getImageForTerrain(terrain));
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                gameGridPane.add(imageView, col, row);
            }
        }

        // Example placement of units
        placeUnit(new Infantry(), 3, 3);
        placeUnit(new Infantry(), 5, 5);

        // Calculate the initial scene size based on the map dimensions
        double initialSceneWidth = layout[0].length * TILE_SIZE;
        double initialSceneHeight = layout.length * TILE_SIZE;

        // Set the scene with the GridPane
        Scene scene = new Scene(gameGridPane, initialSceneWidth, initialSceneHeight);
        stage.setScene(scene);
        stage.setTitle(mapName);
        stage.setMinWidth(initialSceneWidth);
        stage.setMinHeight(initialSceneHeight);
        stage.setResizable(false); // Make the stage non-resizable
        stage.show();

        // Add event handler for selecting and moving units
        gameGridPane.setOnMouseClicked(this::handleMouseClick);
    }

    private void placeUnit(unit unit, int row, int col) {
        units.put(unit.getName(), unit);
        unit.setPosition(row, col);
        ImageView imageView = unit.getImageView();
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        gameGridPane.add(imageView, col, row);
    }

    private void handleMouseClick(MouseEvent event) {
        int col = (int) (event.getX() / TILE_SIZE);
        int row = (int) (event.getY() / TILE_SIZE);

        if (selectedUnit == null) {
            // Select a unit if one is at the clicked position
            selectedUnit = getUnitAt(row, col);
        } else {
            // Try to move the selected unit
            if (selectedUnit.canMoveTo(row, col)) {
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

    private void moveUnit(unit unit, int newRow, int newCol) {
        gameGridPane.getChildren().remove(unit.getImageView());
        unit.move(newRow, newCol);
        gameGridPane.add(unit.getImageView(), newCol, newRow);
    }

    private Image getImageForTerrain(String terrain) {
        String imagePath = "/org/apps/advancewars/images/" + terrain.replace(" ", "_") + ".png";
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Image not found for terrain: " + terrain);
            return new Image(getClass().getResource("/org/apps/advancewars/images/default.png").toExternalForm());  // Fallback to a default image
        }
        return new Image(imageUrl.toExternalForm());
    }

    private String[][] getMapLayout(String mapName) {
        switch (mapName) {
            case "EonSprings":
                return EonSprings.LAYOUT;
            case "LittleIsland":
                return LittleIsland.LAYOUT;
            case "PistonDam":
                return PistonDam.LAYOUT;
            default:
                return new String[0][0];
        }
    }
}
