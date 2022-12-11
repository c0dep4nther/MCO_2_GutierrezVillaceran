package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * controller for ending of game
 */
public class EndGameController {
    /**
     * default constructor for EndGameController
     */
    public EndGameController() {}

    /**
     * Restarts on event
     * @param event opens starting scene
     * @throws IOException if file is not found
     */
    public void onRestart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Scene scene;
        Stage stage;
        Parent root = loader.load();

        // load the game view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
