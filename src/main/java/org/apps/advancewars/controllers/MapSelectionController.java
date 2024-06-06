package org.apps.advancewars.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apps.advancewars.MainApp;

public class MapSelectionController {

    @FXML
    private Button eonSpringsButton;

    @FXML
    private Button littleIslandButton;

    @FXML
    private Button pistonDamButton;

    public void initialize() {
        // Initialization if needed
    }

    public void handleEonSprings() {
        selectMap("EonSprings");
    }

    public void handleLittleIsland() {
        selectMap("LittleIsland");
    }

    public void handlePistonDam() {
        selectMap("PistonDam");
    }

    private void selectMap(String mapName) {
        MainApp.showGameScene(mapName);
        Stage stage = (Stage) eonSpringsButton.getScene().getWindow();
        stage.close(); // Close the map selection window
    }
}
