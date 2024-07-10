package org.apps.advancewars.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameHUDController {

    @FXML
    private VBox teamRedInfo;


    @FXML
    private VBox teamBlueInfo;

    @FXML
    private Button exitGameButton;


    public void updateTeamRedInfo(String info) {

        teamRedInfo.getChildren().clear();
        Label infoLabel = new Label(info);
        teamRedInfo.getChildren().add(infoLabel);
    }

    // Methode zum Aktualisieren der teamBlueInfo VBox
    public void updateTeamBlueInfo(String info) {

        teamBlueInfo.getChildren().clear();
        Label infoLabel = new Label(info);
        teamBlueInfo.getChildren().add(infoLabel);
    }
    public void handleExitGame(ActionEvent event) {
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.close();
    }
}

