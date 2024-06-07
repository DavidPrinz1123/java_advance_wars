package org.apps.advancewars.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apps.advancewars.units.*;

public class GameSceneController {

    private MapController mapController;
    private UnitController unitController;

    public void setMapLayout(String mapName, Stage stage) {
        GridPane gameGridPane = new GridPane();
        mapController = new MapController(gameGridPane);
        unitController = new UnitController(gameGridPane, mapController.getTileSize(), mapController);

        mapController.setMapLayout(mapName);

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

        // Calculate the initial scene size based on the map dimensions
        double initialSceneWidth = mapController.getMapLayout(mapName)[0].length * mapController.getTileSize();
        double initialSceneHeight = mapController.getMapLayout(mapName).length * mapController.getTileSize();

        // Set the scene with the GridPane
        Scene scene = new Scene(gameGridPane, initialSceneWidth, initialSceneHeight);
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
        unitController.placeUnit(new Infantry("teamblue"), 1, 2);
        unitController.placeUnit(new BattleCopter("teamred"), 1, 3);
        unitController.placeUnit(new Bomber("teamblue"), 1, 4);
        unitController.placeUnit(new MechanizedInfantry("teamred"), 1, 5);
        unitController.placeUnit(new AntiAir("teamblue"), 1, 6);
        unitController.placeUnit(new MobileArtillery("teamred"), 1, 7);
        unitController.placeUnit(new Tank("teamblue"), 1, 8);
    }

    private void placeLittleIslandTroops() {
        unitController.placeUnit(new Fighter("teamblue"), 2, 1);
        unitController.placeUnit(new Infantry("teamred"), 2, 2);
        unitController.placeUnit(new BattleCopter("teamblue"), 2, 3);
        unitController.placeUnit(new Bomber("teamred"), 2, 4);
        unitController.placeUnit(new MechanizedInfantry("teamblue"), 2, 5);
        unitController.placeUnit(new AntiAir("teamred"), 2, 6);
        unitController.placeUnit(new MobileArtillery("teamblue"), 2, 7);
        unitController.placeUnit(new Tank("teamred"), 2, 8);
    }

    private void placePistonDamTroops() {
        unitController.placeUnit(new Fighter("teamred"), 3, 1);
        unitController.placeUnit(new Infantry("teamblue"), 3, 2);
        unitController.placeUnit(new BattleCopter("teamred"), 3, 3);
        unitController.placeUnit(new Bomber("teamblue"), 3, 4);
        unitController.placeUnit(new MechanizedInfantry("teamred"), 3, 5);
        unitController.placeUnit(new AntiAir("teamblue"), 3, 6);
        unitController.placeUnit(new MobileArtillery("teamred"), 3, 7);
        unitController.placeUnit(new Tank("teamblue"), 3, 8);
    }
}
