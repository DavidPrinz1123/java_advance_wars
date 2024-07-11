package org.apps.advancewars.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import org.apps.advancewars.MainApp;

public class VictoryScreenController {

    @FXML
    private Label victoryLabel;

    @FXML
    private Button mapChangeButton;

    @FXML
    private Button exitGameButton;

    private String mapName;

    public void setWinningTeam(String winningTeam, String mapName) {
        this.mapName = mapName;
        if ("blue".equals(winningTeam)) {
            victoryLabel.setText("Team Blue Wins!");
        } else if ("red".equals(winningTeam)) {
            victoryLabel.setText("Team Red Wins!");
        }
    }

    @FXML
    private void handleMapChange() {
        try {
            Stage stage = (Stage) mapChangeButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/org/apps/advancewars/fxml/MapSelection.fxml"));
            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExitGame() {
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.close();
    }
}
