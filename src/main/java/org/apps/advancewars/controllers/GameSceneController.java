package org.apps.advancewars.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.apps.advancewars.maps.EonSprings;
import org.apps.advancewars.maps.LittleIsland;
import org.apps.advancewars.maps.PistonDam;

import java.net.URL;

public class GameSceneController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gameGridPane;

    public void initialize() {
        // Initialization if needed
    }

    public void setMapLayout(String mapName) {
        String[][] layout = getMapLayout(mapName);
        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                String terrain = layout[row][col];
                ImageView imageView = new ImageView(getImageForTerrain(terrain));
                imageView.setFitWidth(60);
                imageView.setFitHeight(60);
                gameGridPane.add(imageView, col, row);
            }
        }

        // Adjust grid pane to fit its content
        gameGridPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gameGridPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gameGridPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // Add the grid pane to the scroll pane
        scrollPane.setContent(gameGridPane);
    }

    private Image getImageForTerrain(String terrain) {
        String imagePath = "/org/apps/advancewars/images/" + terrain.replace(" ", "_") + ".png";
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Image not found for terrain: " + terrain);
            return new Image("/org/apps/advancewars/images/default.png");  // Fallback to a default image
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
