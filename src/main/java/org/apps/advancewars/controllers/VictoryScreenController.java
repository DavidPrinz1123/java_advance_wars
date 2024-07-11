package org.apps.advancewars.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VictoryScreenController {

    @FXML
    private Label victoryLabel;

    public void setWinningTeam(String winningTeam) {
        if ("blue".equals(winningTeam)) {
            victoryLabel.setText("Team Blue Wins!");
        } else if ("red".equals(winningTeam)) {
            victoryLabel.setText("Team Red Wins!");
        }
    }
}