package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    private Button plantBtn;
    @FXML
    private Button harvestBtn;
    @FXML
    private Button waterBtn;
    @FXML
    private Button fertilize;
    @FXML
    private Button shovel;
    @FXML
    private Button pickaxe;
    @FXML
    private Button plowBtn;
    @FXML
    private Button tileBtn;
    @FXML
    private GridPane farmGrid;
    private Stage stage;


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

        // center report label
//        report.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        report.setText("Day: " + currentDay + "\nMoney: " + currentMoney);

        addButtons();
    }

    public void onPlowBtnClick(ActionEvent actionEvent) {
//        gameModel.plow();
//        report.setText("Day: " + currentDay + "\nMoney: " + currentMoney);
    }

    // loop through the grid and add button
    public void addButtons() {
        int count = 1;
        int row;
        int column;

        for (column = 0; column < 5; column++) {
            for (row = 0; row < 10; row++) {
                tileBtn = new Button();
                tileBtn.setText("Tile " + count);
                farmGrid.add(tileBtn, column, row);
                count+=5;

                // center align grid elements
                GridPane.setHalignment(tileBtn, HPos.CENTER);
                GridPane.setValignment(tileBtn, VPos.CENTER);
            }

            count = switch (count) {
                case 51 -> 2;
                case 52 -> 3;
                case 53 -> 4;
                case 54 -> 5;
                default -> 0;
            };
        }
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
        if ("P".equals(choice)) {
            report.setText("What would you like to plant? \n[1] Turnip\n[2] Carrot");

            //seedChoice = input.nextInt();
            //input.nextLine();

            //System.out.println("Where would you like to plant it?");
            //farmLand = player.plantSeed(seedList.get(seedChoice), farmLand);
        }
    }
}