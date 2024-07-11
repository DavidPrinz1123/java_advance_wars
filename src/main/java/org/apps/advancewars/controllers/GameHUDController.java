package org.apps.advancewars.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameHUDController {

    @FXML
    private Button exitGameButton;

    @FXML
    private Button handleEndMove;

    @FXML
    private Button surrenderBlueButton;

    @FXML
    private Button surrenderRedButton;

    private UnitController unitController;
    private GameSceneController gameSceneController;

    @FXML
    public void initialize() {
        surrenderBlueButton.setOnAction(event -> handleSurrender("blue"));
        surrenderRedButton.setOnAction(event -> handleSurrender("red"));
    }

    public void handleExitGame(ActionEvent event) {
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.close();
    }

    public void setUnitController(UnitController unitController) {
        this.unitController = unitController;
    }

    public void setGameSceneController(GameSceneController gameSceneController) {
        this.gameSceneController = gameSceneController;
    }

    @FXML
    private void handleEndMove(ActionEvent event) {
        unitController.changePlayer();
    }

    private void handleSurrender(String team) {
        Stage stage = (Stage) surrenderBlueButton.getScene().getWindow(); // Abrufen der aktuellen Stage
        gameSceneController.endGame(team, stage);
    }
}