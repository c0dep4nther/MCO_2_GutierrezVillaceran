package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * starts game application
 */
public class GameApplication extends Application {
    /**
     * default constructor for GameApplication
     */
    public GameApplication(){}

    /**
     * starts game
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException if file is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Farm");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * String class
     * @param args stores java command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}