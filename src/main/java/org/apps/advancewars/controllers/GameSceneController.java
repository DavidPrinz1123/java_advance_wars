package org.apps.advancewars.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apps.advancewars.MainApp;
import org.apps.advancewars.units.*;

import java.io.IOException;

public class GameSceneController {

    private MapController mapController;
    private UnitController unitController;
    private GameHUDController hudController;
    private String mapName;

    public void setMapLayout(String mapName, Stage stage) {
        this.mapName = mapName;
        BorderPane mainLayout = new BorderPane();
        GridPane gameGridPane = new GridPane();
        mainLayout.setCenter(gameGridPane);

        mapController = new MapController(gameGridPane);
        unitController = new UnitController(gameGridPane, mapController.getTileSize(), mapController);
        unitController.setGameSceneController(this);
        mapController.setMapLayout(mapName);

        // Load the HUD
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/org/apps/advancewars/fxml/GameHUD.fxml"));
            Parent hudRoot = loader.load();
            hudController = loader.getController();
            hudController.setUnitController(unitController);
            hudController.setGameSceneController(this);
            mainLayout.setBottom(hudRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ensure hudController is initialized before calling its methods
        if (hudController != null) {
            switch (mapName) {
                case "EonSprings":
                    placeEonSpringsTroops();
                    break;
                case "LittleIsland":
                    placeLittleIslandTroops();
                    break;
                case "PistonDam":
                    placePistonDamTroops();
                    break;
                default:
                    System.err.println("Unknown map: " + mapName);
                    break;
            }
        } else {
            System.err.println("Failed to load HUD controller");
        }

        double initialSceneWidth = mapController.getMapLayout(mapName)[0].length * mapController.getTileSize();
        double initialSceneHeight = mapController.getMapLayout(mapName).length * mapController.getTileSize() + 100;

        Scene scene = new Scene(mainLayout, initialSceneWidth, initialSceneHeight);
        stage.setScene(scene);
        stage.setTitle(mapName);
        stage.setMinWidth(initialSceneWidth);
        stage.setMinHeight(initialSceneHeight);
        stage.setResizable(false);
        stage.show();

        gameGridPane.setOnMouseClicked(unitController::handleMouseClick);
    }

    private void placeEonSpringsTroops() {
        unitController.placeUnit(new Fighter("teamred"), 1, 1);
        unitController.placeUnit(new Infantry("teamred"), 2, 1);
        unitController.placeUnit(new Infantry("teamred"), 2, 2);
        unitController.placeUnit(new Infantry("teamred"), 3, 0);
        unitController.placeUnit(new Infantry("teamred"), 3, 1);
        unitController.placeUnit(new BattleCopter("teamred"), 6, 3);
        unitController.placeUnit(new Bomber("teamred"), 9, 4);
        unitController.placeUnit(new MechanizedInfantry("teamred"), 7, 4);
        unitController.placeUnit(new AntiAir("teamred"), 5, 3);
        unitController.placeUnit(new MobileArtillery("teamred"), 5, 1);
        unitController.placeUnit(new Tank("teamred"), 8, 2);

        unitController.placeUnit(new Fighter("teamblue"), 10, 17);
        unitController.placeUnit(new Infantry("teamblue"), 15, 17);
        unitController.placeUnit(new Infantry("teamblue"), 13, 17);
        unitController.placeUnit(new Infantry("teamblue"), 16, 16);
        unitController.placeUnit(new Infantry("teamblue"), 16, 17);
        unitController.placeUnit(new BattleCopter("teamblue"), 11, 16);
        unitController.placeUnit(new Bomber("teamblue"), 13, 18);
        unitController.placeUnit(new MechanizedInfantry("teamblue"), 7, 17);
        unitController.placeUnit(new AntiAir("teamblue"), 15, 15);
        unitController.placeUnit(new MobileArtillery("teamblue"), 9, 17);
        unitController.placeUnit(new Tank("teamblue"), 14, 17);
    }

    private void placeLittleIslandTroops() {
        unitController.placeUnit(new Infantry("teamblue"), 2, 15);
        unitController.placeUnit(new Infantry("teamred"), 2, 14);
    }

    private void placePistonDamTroops() {
        unitController.placeUnit(new Fighter("teamred"), 3, 0);
        unitController.placeUnit(new Infantry("teamred"), 3, 1);
        unitController.placeUnit(new Infantry("teamred"), 0, 0);
        unitController.placeUnit(new Infantry("teamred"), 1, 1);
        unitController.placeUnit(new Infantry("teamred"), 2, 2);
        unitController.placeUnit(new BattleCopter("teamred"), 3, 2);
        unitController.placeUnit(new Bomber("teamred"), 3, 5);
        unitController.placeUnit(new MechanizedInfantry("teamred"), 3, 6);
        unitController.placeUnit(new AntiAir("teamred"), 2, 7);
        unitController.placeUnit(new MobileArtillery("teamred"), 4, 1);
        unitController.placeUnit(new Tank("teamred"), 4, 4);

        unitController.placeUnit(new Fighter("teamblue"), 11, 15);
        unitController.placeUnit(new Infantry("teamblue"), 11, 19);
        unitController.placeUnit(new Infantry("teamblue"), 12, 21);
        unitController.placeUnit(new Infantry("teamblue"), 10, 17);
        unitController.placeUnit(new Infantry("teamblue"), 10, 19);
        unitController.placeUnit(new BattleCopter("teamblue"), 10, 21);
        unitController.placeUnit(new Bomber("teamblue"), 9, 18);
        unitController.placeUnit(new MechanizedInfantry("teamblue"), 13, 17);
        unitController.placeUnit(new AntiAir("teamblue"), 13, 16);
        unitController.placeUnit(new MobileArtillery("teamblue"), 12, 17);
        unitController.placeUnit(new Tank("teamblue"), 9, 22);
    }

    public GameHUDController getHUDcontroller() {
        return hudController;
    }

    public void endGame(String winningTeam, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/apps/advancewars/fxml/VictoryScreen.fxml"));
            Parent root = loader.load();

            VictoryScreenController victoryScreenController = loader.getController();
            victoryScreenController.setWinningTeam(winningTeam, mapName);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTurnNotification(String team) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Next Turn");
        alert.setHeaderText(null);
        alert.setContentText(team + " is now taking their turn!");
        alert.showAndWait();
    }
}