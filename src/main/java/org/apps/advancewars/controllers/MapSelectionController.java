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

    @FXML
    public void initialize() {
        // Initialization if needed
    }

    @FXML
    private void handleEonSprings() {
        selectMap("EonSprings");
    }

    @FXML
    private void handleLittleIsland() {
        selectMap("LittleIsland");
    }

    @FXML
    private void handlePistonDam() {
        selectMap("PistonDam");
    }

    private void selectMap(String mapName) {
        MainApp.showGameScene(mapName);
        Stage stage = (Stage) eonSpringsButton.getScene().getWindow();
        if (stage != null) {
            stage.close();
        } else {
            System.err.println("Stage is null, cannot close.");
        }
    }
}
