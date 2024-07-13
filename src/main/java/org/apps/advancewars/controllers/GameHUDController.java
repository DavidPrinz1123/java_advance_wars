package org.apps.advancewars.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

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
        surrenderBlueButton.setOnAction(event -> handleSurrender("teamblue"));
        surrenderRedButton.setOnAction(event -> handleSurrender("teamred"));
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
        if (unitController != null) {
            unitController.changePlayer();
        }
    }

    private void handleSurrender(String team) {
        Stage stage = (Stage) surrenderBlueButton.getScene().getWindow();
        String winningTeam = team.equals("teamblue") ? "teamred" : "teamblue";
        gameSceneController.endGame(winningTeam, stage);
    }

    public void showTurnNotification(String team) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(exitGameButton.getScene().getWindow());
        popupStage.setResizable(false);

        StackPane popupContent = new StackPane();
        Label label = new Label(team + " is now taking their turn!");
        popupContent.getChildren().add(label);
        popupContent.setStyle("-fx-padding: 20px; -fx-background-color: rgba(0, 0, 0, 0.8); -fx-border-color: white; -fx-border-width: 2px;");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16pt;");

        Scene popupScene = new Scene(popupContent);
        popupStage.setScene(popupScene);
        popupStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2.0));
        delay.setOnFinished(event -> popupStage.close());
        delay.play();
    }
}