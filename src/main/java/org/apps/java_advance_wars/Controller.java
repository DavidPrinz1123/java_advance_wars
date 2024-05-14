package org.apps.java_advance_wars;

import javafx.application.Platform;
import javafx.fxml.FXML;


public class Controller {
    @FXML
    private void handleNewGame() {
        // Logic for starting a new game
        System.out.println("Starting a new game...");
        // You can add code here to initialize a new game session
    }

    @FXML
    private void handleLoadGame() {
        // Logic for loading a saved game
        System.out.println("Loading a saved game...");
        // You can add code here to load a previously saved game
    }

    @FXML
    private void handleOptions() {
        // Logic for displaying options
        System.out.println("Displaying options...");
        // You can add code here to open a settings or options window
    }

    @FXML
    private void handleExit() {
        // Logic for exiting the game
        System.out.println("Exiting the game...");
        // You can add code here to save the game state before exiting, if necessary
        Platform.exit(); // This exits the JavaFX application
    }
}