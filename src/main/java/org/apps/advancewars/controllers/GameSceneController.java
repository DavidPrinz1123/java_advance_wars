package org.apps.advancewars.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void setMapLayout(String mapName, Stage stage) {
        BorderPane mainLayout = new BorderPane();
        GridPane gameGridPane = new GridPane();
        mainLayout.setCenter(gameGridPane);

        mapController = new MapController(gameGridPane);
        unitController = new UnitController(gameGridPane, mapController.getTileSize(), mapController);
        mapController.setMapLayout(mapName);

        // Load the HUD
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/org/apps/advancewars/fxml/GameHUD.fxml"));
            Parent hudRoot = loader.load();
            hudController = loader.getController();
            hudController.setUnitController(unitController);  // Set the unit controller in HUD controller
            hudController.setGameSceneController(this); // Set GameSceneController in HUD controller
            mainLayout.setBottom(hudRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ensure hudController is initialized before calling its methods
        if (hudController != null) {
            // Place troops differently based on the selected map
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

        // Calculate the initial scene size based on the map dimensions
        double initialSceneWidth = mapController.getMapLayout(mapName)[0].length * mapController.getTileSize();
        double initialSceneHeight = mapController.getMapLayout(mapName).length * mapController.getTileSize() + 100; // Added extra height for HUD

        // Set the scene with the main layout
        Scene scene = new Scene(mainLayout, initialSceneWidth, initialSceneHeight);
        stage.setScene(scene);
        stage.setTitle(mapName);
        stage.setMinWidth(initialSceneWidth);
        stage.setMinHeight(initialSceneHeight);
        stage.setResizable(false); // Make the stage non-resizable
        stage.show();

        // Add event handler for selecting and moving units
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

        hudController.updateTeamRedInfo("Eon Springs Team Red Units: Fighter, Battle Copter, Mechanized Infantry, Mobile Artillery");
        hudController.updateTeamBlueInfo("Eon Springs Team Blue Units: Infantry, Bomber, Anti-Air, Tank");
    }

    private void placeLittleIslandTroops() {
        unitController.placeUnit(new Infantry("teamblue"), 2, 15);
        unitController.placeUnit(new Infantry("teamred"), 2, 14);

        hudController.updateTeamRedInfo("Little Island Team Red Units: Infantry, Bomber, Anti-Air, Tank");
        hudController.updateTeamBlueInfo("Little Island Team Blue Units: Fighter, Battle Copter, Mechanized Infantry, Mobile Artillery");
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

        hudController.updateTeamRedInfo("Piston Dam Team Red Units: Fighter, Battle Copter, Mechanized Infantry, Mobile Artillery");
        hudController.updateTeamBlueInfo("Piston Dam Team Blue Units: Infantry, Bomber, Anti-Air, Tank");
    }

    public GameHUDController getHUDcontroller() {
        return hudController;
    }

    public void endGame(String winningTeam, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/apps/advancewars/fxml/VictoryScreen.fxml"));
            Parent root = loader.load();

            VictoryScreenController victoryScreenController = loader.getController();
            victoryScreenController.setWinningTeam(winningTeam);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}