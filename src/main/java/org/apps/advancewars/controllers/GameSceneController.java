package org.apps.advancewars.controllers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import org.apps.advancewars.maps.EonSprings;
import org.apps.advancewars.maps.LittleIsland;
import org.apps.advancewars.maps.PistonDam;

import java.net.URL;

public class GameSceneController {

    private final int TILE_SIZE = 60; // Size of each tile
    private GridPane gameGridPane;

    public void setMapLayout(String mapName, Stage stage) {
        gameGridPane = new GridPane();
        String[][] layout = getMapLayout(mapName);
        double initialSceneWidth = 853;
        double initialSceneHeight = 640;

        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                String terrain = layout[row][col];
                ImageView imageView = new ImageView(getImageForTerrain(terrain));
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                gameGridPane.add(imageView, col, row);
            }
        }

        // Wrap the GridPane in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(gameGridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Set the scene with the ScrollPane
        Scene scene = new Scene(scrollPane, initialSceneWidth, initialSceneHeight);
        stage.setScene(scene);
        stage.setTitle(mapName);
        stage.show();

        // Add listeners for width and height changes
        scene.widthProperty().addListener((obs, oldVal, newVal) -> resizeTiles(scene, layout));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> resizeTiles(scene, layout));
    }

    private void resizeTiles(Scene scene, String[][] layout) {
        double sceneWidth = scene.getWidth();
        double sceneHeight = scene.getHeight();

        double scaleFactorWidth = sceneWidth / (layout[0].length * TILE_SIZE);
        double scaleFactorHeight = sceneHeight / (layout.length * TILE_SIZE);
        double scaleFactor = Math.min(scaleFactorWidth, scaleFactorHeight);

        double newTileSize = TILE_SIZE * scaleFactor;

        for (var node : gameGridPane.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setFitWidth(newTileSize);
                imageView.setFitHeight(newTileSize);
            }
        }
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
