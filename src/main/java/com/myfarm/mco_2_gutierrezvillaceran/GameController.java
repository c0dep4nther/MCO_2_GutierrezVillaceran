package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private int currentDay;
    private float currentMoney;
    private GameModel gameModel;

    @FXML
    private Label report;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;
    @FXML
    private Button plant;
    @FXML
    private Button harvest;
    @FXML
    private Button water;
    @FXML
    private Button fertilize;
    @FXML
    private Button shovel;
    @FXML
    private Button pickaxe;
    @FXML
    private Button plow;
    @FXML
    private GridPane farmGrid;


    String choice;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameModel = new GameModel();

        // initialize seeds
        gameModel.addSeed(1, new Plant("Turnip", "Root Crop", 2,
                1, 2,0, 1,2,
                1, 5, 6, 5));
        gameModel.addSeed(2, new Plant("Carrot", "Root Crop", 3,
                1,2,0,1,2, 1,
                10, 9, 7.5f));

        // initialize tools
        gameModel.addTool("W", new Tool("Watering Can", 0, 0.5f));
        gameModel.addTool("PL", new Tool("Plow", 0, 0.5f));

        // initialize player details
        currentDay = gameModel.getDayCount();
        currentMoney = gameModel.getMoney();

        report.setText("Day: " + currentDay + "\nMoney: " + currentMoney);
    }


    public void submit(ActionEvent event){
        try{
            choice=myTextField.getText();
            System.out.println(choice);
        }
        catch (Exception e){
            System.out.println(e);
        }
        evaluateChoice(choice);
    }

    public void evaluateChoice(String choice){
        switch (choice) {
            case "P" -> {
                report.setText("What would you like to plant? \n[1] Turnip\n[2] Carrot");

                //seedChoice = input.nextInt();
                //input.nextLine();

                //System.out.println("Where would you like to plant it?");
                //farmLand = player.plantSeed(seedList.get(seedChoice), farmLand);
            }
        }
    }
}