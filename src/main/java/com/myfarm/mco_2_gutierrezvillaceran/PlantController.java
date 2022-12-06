package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PlantController {
    public void onSelectSeed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        GameController gameController = loader.getController();
        Scene scene;
        Stage stage;
        String seed;
        Parent root = loader.load();

        // get the text from the button
        seed = ((Button) event.getSource()).getText();
        gameController.toggleTile(true);
        gameController.setMode(FarmerAction.PLANT);
        gameController.setSeedName(seed);

        // load the game view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onCancel(ActionEvent event) throws IOException {
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
