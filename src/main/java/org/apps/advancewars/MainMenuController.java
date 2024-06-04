package org.apps.advancewars;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    @FXML
    private Button startGameButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) throws IOException {
        startGameButton.setOnAction(event -> startGame());
    }

    @FXML
    protected void startGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("map_selector.fxml"));
        loader.setController(new MapSelectionController());
        Parent mapSelectionScene = loader.load();

        // Switch to the map selection scene (stage management)
        primaryStage.setScene(new Scene(mapSelectionScene));
    }
}