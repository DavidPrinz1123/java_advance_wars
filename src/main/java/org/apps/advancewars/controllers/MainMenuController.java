package org.apps.advancewars.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apps.advancewars.MainApp;

public class MainMenuController {

    @FXML
    private Button startGameButton;

    public void handleStartGame(ActionEvent event) {
        MainApp.showMapSelection();
    }

    public void handleExitGame(ActionEvent event) {
        Stage stage = (Stage) startGameButton.getScene().getWindow();
        stage.close();
    }
}
