package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.model.FarmerAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PlantController {
    private GameModel gameData;
    private Scene preScene;

    /**
     * action performed on select seed
     * @param event selecting seed
     * @throws IOException
     */
    public void onSelectSeed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Scene scene;
        Stage stage;
        String seed;
        Parent root = loader.load();
        GameController gameController = loader.getController();

        // get the text from the button
        seed = ((Button) event.getSource()).getText();

        // set GameController properties
        gameController.setGameData(gameData);
        gameController.toggleTile(true);
        gameController.setMode(FarmerAction.PLANT);
        gameController.setSeedName(seed);
        gameController.setReport("You are in plant mode.\nPlease select a tile.");

        // load the game view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * cancel action
     * @param event cancel the action
     */
    public void onCancel(ActionEvent event) {
        Stage stage;

        // load the game view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }

    // setters

    /**
     * setter for pre scene
     * @param preScene sets scene
     */
    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    /**
     * setter for game data
     * @param gameData holds information of game
     */
    public void setGameData(GameModel gameData) {
        this.gameData = gameData;
    }
}
