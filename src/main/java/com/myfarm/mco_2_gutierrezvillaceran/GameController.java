package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private GameModel gameData = new GameModel();
    private FarmerAction mode;
    private String seedName;
    private boolean isEdge;

    @FXML
    private Button plantBtn;
    @FXML
    private Label gameInfo;
    @FXML
    private Label report;
    @FXML
    private GridPane farmGrid;

    private Button tileBtn;
    private Label tileLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the game info asynchronously
        Platform.runLater(this::updateGameInfo);

        // initialize seeds
        gameData.addSeed("TURNIP", new Plant("Turnip", "Root Crop", 2,
                1, 2,0, 1,2,
                1, 5, 6, 5));
        gameData.addSeed("CARROT", new Plant("Carrot", "Root Crop", 3,
                1,2,0,1,2, 1,
                10, 9, 7.5f));
        gameData.addSeed("APPLE", new Plant("Apple", "Fruit Tree", 10, 7, 7,
                5, 5, 15, 10, 200,
                5, 25));

        // initialize tools
        gameData.addTool("WATER", new Tool("Watering Can", 0, 0.5f));
        gameData.addTool("PLOW", new Tool("Plow", 0, 0.5f));
        report.setText("");

        gridInit();
        toggleTile(false);
    }

    public void updateGameInfo() {
        int currentDay = gameData.getDayCount();
        int playerLevel = gameData.getLevel();
        float playerExp = gameData.getExp();
        float playerMoney = gameData.getMoney();

        gameInfo.setText("Day: " + currentDay + "\t\t\tMoney: " + playerMoney +
                "\nLevel: " + playerLevel + "\t\t\tExp: " + playerExp);
    }

//        TODO: Implement Tools and Planting Features
    public void onToolBtnClick(ActionEvent event) {
        String tool = ((Button) event.getSource())
                .getText()
                .toLowerCase();

        toggleTile(true);

        switch (tool) {
            case "harvest" -> mode = FarmerAction.HARVEST;
            case "water" -> mode = FarmerAction.WATER;
            case "plow" -> mode = FarmerAction.PLOW;
            case "shovel" -> mode = FarmerAction.SHOVEL;
            case "pickaxe" -> mode = FarmerAction.PICKAXE;
        }

        report.setText("You are in " + tool + " mode." +
                "\nPlease Select a tile.");
    }

    public void onTileBtnClick(ActionEvent event) {
        String tempTileID = ((Button) event.getSource())
                .getText()
                .substring(5);
        int harvestTime;
        int tileID = Integer.parseInt(tempTileID);

        switch (mode) {
            case PLOW -> {
                gameData.activeTool("PLOW", tileID);
                report.setText("You plowed tile " + tileID + ".");
                toggleTile(false);
            }
            case PLANT -> {
                gameData.plantSeed(seedName, tileID);
                boolean plantSuccess = gameData.getPlantSuccess();
                int seedCost = gameData.getSeedCost(seedName);

                if (plantSuccess) {
                    harvestTime = gameData.getHarvestTime(tileID);

                    report.setText("You planted " + seedName.toLowerCase() +
                            " on tile " + tileID + " which costs " + seedCost + " Objectcoins.\n"
                            + "Harvest is in " + harvestTime + " days.");
                } else {
                    report.setText("You failed to plant " + seedName.toLowerCase() +
                            " on tile " + tileID + ". Make sure the tile is plowed \nand unoccupied. " +
                            "If it's a tree, make sure it's not on the edge of the farm.");
                }

                toggleTile(false);
            }
            case WATER -> {
                gameData.activeTool("WATER", tileID);
                report.setText("You watered tile " + tileID + ".");
                toggleTile(false);
            }
        }

        updateGameInfo();
    }

//    public void onSleepBtnClick(ActionEvent event) {
//        gameData.nextDay();
//        updateGameInfo();
//        report.setText("You slept for a day.");
//    }

    public void onPlantBtnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("plant-view.fxml"));
        Stage stage;
        Scene scene;
        Parent root = loader.load();
        PlantController plantController = loader.getController();

        // save the state of game controller before jumping to plant view
        plantController.setPreScene(plantBtn.getScene());
        plantController.setGameData(gameData);

        // load plant view
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toggleTile(boolean toggle) {
        String cropName;
        TileStatus status;
        int count;

        // if there is an action, show the tiles
        if (toggle) {
            for (count = 1; count <= 50; count++) {
                tileBtn = (Button) farmGrid.lookup("#tile" + count);
                tileBtn.setVisible(true);

                tileLbl = (Label) farmGrid.lookup("#label" + count);
                tileLbl.setVisible(false);
            }
        } else {
            // otherwise, show the crops
            for (count = 1; count <= 50; count++) {
                tileBtn = (Button) farmGrid.lookup("#tile" + count);
                tileBtn.setVisible(false);

                status = gameData.getTileStatus(count);

                // if there is a plant in tile, show the plant name
                if (status == TileStatus.PLANTED) {
                    cropName = gameData.getTile(count)
                            .getCropName()
                            .toUpperCase();

                    tileLbl = (Label) farmGrid.lookup("#label" + count);
                    tileLbl.setText(cropName);
                    tileLbl.setVisible(true);
                } else {
                    // otherwise, show the tile status
                    tileLbl = (Label) farmGrid.lookup("#label" + count);
                    tileLbl.setText(status.toString());
                    tileLbl.setVisible(true);
                }
            }
        }
    }

    public void gridInit() {
        int count = 1;
        int row;
        int column;

        // loop through grid and add buttons
        for (column = 0; column < 5; column++) {
            for (row = 0; row < 10; row++) {
                // for buttons
                tileBtn = new Button();
                tileBtn.setText("Tile " + count);
                tileBtn.setId("tile" + count);
                tileBtn.setOnAction(this::onTileBtnClick);
                tileBtn.setVisible(false);
                farmGrid.add(tileBtn, column, row);

                // center align grid elements
                GridPane.setHalignment(tileBtn, HPos.CENTER);
                GridPane.setValignment(tileBtn, VPos.CENTER);

                // for labels
                tileLbl = new Label();
                tileLbl.setId("label" + count);
                tileLbl.setVisible(false);
                farmGrid.add(tileLbl, column, row);

                // center align grid elements
                GridPane.setHalignment(tileLbl, HPos.CENTER);
                GridPane.setValignment(tileLbl, VPos.CENTER);

                // center text label
                tileLbl.setAlignment(Pos.CENTER);
                count+=5;
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

     // setters
    public void setMode(FarmerAction mode) {
        this.mode = mode;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public void setReport(String report) {
        this.report.setText(report);
    }

    public void setGameData(GameModel gameData) {
        this.gameData = gameData;
    }
}