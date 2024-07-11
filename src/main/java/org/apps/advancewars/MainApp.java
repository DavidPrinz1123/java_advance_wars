package org.apps.advancewars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apps.advancewars.controllers.GameSceneController;
import org.apps.advancewars.controllers.VictoryScreenController;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage primaryStage;
    private static String selectedMapName;

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        showMainMenu();
    }

    public static void showMainMenu() {
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("/org/apps/advancewars/fxml/MainMenu.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Main Menu");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMapSelection() {
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("/org/apps/advancewars/fxml/MapSelection.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Map Selection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showGameScene(String mapName) {
        selectedMapName = mapName;
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/org/apps/advancewars/fxml/GameScene.fxml"));
            Parent root = loader.load();
            GameSceneController controller = loader.getController();
            Stage stage = new Stage();
            controller.setMapLayout(mapName, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showVictoryScreen(String winningTeam) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/org/apps/advancewars/fxml/VictoryScreen.fxml"));
            Parent root = loader.load();
            VictoryScreenController controller = loader.getController();
            controller.setWinningTeam(winningTeam, selectedMapName);  // Übergabe des mapName
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Victory!");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSelectedMapName() {
        return selectedMapName;
    }

    public static void main(String[] args) {
        launch(args);
    }
}