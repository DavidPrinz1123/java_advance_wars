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
        unitController.placeUnit(new Fighter("teamred"), 6, 1);
        unitController.placeUnit(new Infantry("teamred"), 6, 4);
        unitController.placeUnit(new Infantry("teamred"), 7, 5);
        unitController.placeUnit(new BattleCopter("teamred"), 6, 3);
        unitController.placeUnit(new Bomber("teamred"), 8, 4);
        unitController.placeUnit(new MechanizedInfantry("teamred"), 7, 4);
        unitController.placeUnit(new AntiAir("teamred"), 5, 3);
        unitController.placeUnit(new MobileArtillery("teamred"), 6, 2);
        unitController.placeUnit(new Tank("teamred"), 8, 2);

        unitController.placeUnit(new Fighter("teamblue"), 2, 15);
        unitController.placeUnit(new Infantry("teamblue"), 2, 14);
        unitController.placeUnit(new Infantry("teamblue"), 3, 16);
        unitController.placeUnit(new BattleCopter("teamblue"), 1, 16);
        unitController.placeUnit(new Bomber("teamblue"), 4, 16);
        unitController.placeUnit(new MechanizedInfantry("teamblue"), 3, 13);
        unitController.placeUnit(new AntiAir("teamblue"), 3, 14);
        unitController.placeUnit(new MobileArtillery("teamblue"), 2, 12);
        unitController.placeUnit(new Tank("teamblue"), 4, 12);
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
}
