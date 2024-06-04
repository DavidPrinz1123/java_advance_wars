package org.apps.advancewars.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apps.advancewars.MainApp;

public class MapSelectionController {

    @FXML
    private ImageView mapImageView;

    @FXML
    private Button eonSpringsButton;

    @FXML
    private Button littleIslandButton;

    @FXML
    private Button pistonDamButton;

    public void initialize() {
        String selectedMapName = MainApp.getSelectedMapName();
        if (selectedMapName != null) {
            eonSpringsButton.setDisable(true);
            littleIslandButton.setDisable(true);
            pistonDamButton.setDisable(true);
        }
    }

    public void handleEonSprings() {
        selectMap("eonsprings.png", "EonSprings");
    }

    public void handleLittleIsland() {
        selectMap("littleisland.png", "LittleIsland");
    }

    public void handlePistonDam() {
        selectMap("pistondam.png", "PistonDam");
    }

    private void selectMap(String imageName, String mapName) {
        loadImage(imageName);
        MainApp.showGameScene(mapName);
    }

    private void loadImage(String imageName) {
        try {
            String imagePath = getClass().getResource("/org/apps/advancewars/images/" + imageName).toExternalForm();
            mapImageView.setImage(new Image(imagePath));
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + imageName);
        }
    }
}
