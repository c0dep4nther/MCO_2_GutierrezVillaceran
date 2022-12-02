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
    private enum action {
        PLANT, WATER, PLOW, HARVEST, SHOVEL, PICKAXE
    }
    private final GameModel gameModel = new GameModel();
    private int currentDay;
    private int playerLevel;
    private float playerExp;
    private float playerMoney;
    private action mode;

    @FXML
    private Label gameInfo;
    @FXML
    private Label report;
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
        // initialize seeds
        gameModel.addSeed(1, new Plant("Turnip", "Root Crop", 2,
                1, 2,0, 1,2,
                1, 5, 6, 5));
        gameModel.addSeed(2, new Plant("Carrot", "Root Crop", 3,
                1,2,0,1,2, 1,
                10, 9, 7.5f));

        // initialize tools
        gameModel.addTool("WATER", new Tool("Watering Can", 0, 0.5f));
        gameModel.addTool("PLOW", new Tool("Plow", 0, 0.5f));

        updateReport();
        addButtons();
    }

    public void updateReport() {
        currentDay = gameModel.getDayCount();
        playerMoney = gameModel.getMoney();
        playerLevel = gameModel.getLevel();
        playerExp = gameModel.getExp();
        gameInfo.setText("Day: " + currentDay + "\t\t\tMoney: " + playerMoney +
                "\nLevel: " + playerLevel + "\t\t\tExp: " + playerExp);
    }

//        TODO: complete plow and water
    public void onToolBtnClick(ActionEvent actionEvent) {
        String tool = ((Button) actionEvent.getSource()).getText().toLowerCase();

        setTileVisibility(true);

        switch (tool) {
            case "plant" -> mode = action.PLANT;
            case "harvest" -> mode = action.HARVEST;
            case "water" -> mode = action.WATER;
            case "plow" -> mode = action.PLOW;
            case "shovel" -> mode = action.SHOVEL;
            case "pickaxe" -> mode = action.PICKAXE;

        }
            report.setText("You are in " + tool + " mode." +
                    "\nPlease Select a tile.");
    }

    public void onTileBtnClick(ActionEvent actionEvent) {
        if (mode == action.PLOW) {
//            gameModel.plow();
        }
        updateReport();
    }

    public void addButtons() {
        int count = 1;
        int row;
        int column;


        for (column = 0; column < 5; column++) {
            for (row = 0; row < 10; row++) {
                tileBtn = new Button();
                tileBtn.setText("Tile " + count);
                tileBtn.setId("tile" + count);
                tileBtn.setOnAction(this::onTileBtnClick);
                tileBtn.setVisible(false);
                farmGrid.add(tileBtn, column, row);
                count+=5;

                // hide tiles

                // center align grid elements
                GridPane.setHalignment(tileBtn, HPos.CENTER);
                GridPane.setValignment(tileBtn, VPos.CENTER);
            }

            count = switch (count) {
                case 51 -> 2;
                case 52 -> 3;
                case 53 -> 4;
                case 54 -> 5;
                default -> 1;
            };
        }
    }

     public void setTileVisibility(boolean actionClicked) {
        int count;

        // if there is an action, show the tiles
        if (actionClicked) {
            for (count = 1; count <= 50; count++) {
                tileBtn = (Button) farmGrid.lookup("#tile" + count);
                tileBtn.setVisible(true);
            }
        } else {
            for (count = 1; count <= 50; count++) {
                tileBtn = (Button) farmGrid.lookup("#tile" + count);
                tileBtn.setVisible(false);
            }
        }
     }
}