package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.model.*;
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
    private String registerType;

    @FXML
    private Button plantBtn;
    @FXML
    private Button newGame;
    @FXML
    private Label gameInfo;
    @FXML
    private Label registerInfo;
    @FXML
    private Label report;
    @FXML
    private GridPane farmGrid;

    private Button tileBtn;
    private Label tileLbl;

    /**
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the game data asynchronously
        Platform.runLater(() -> {
            registerType = gameData.getRegisterType();

            updateGameInfo();
            updateRegisterInfo();
        });

        // initialize seeds
        gameData.addSeed("TURNIP", new Plant("Turnip", "Root Crop", 2,
                1, 2,0, 1,2,
                1, 5, 6, 5));
        gameData.addSeed("CARROT", new Plant("Carrot", "Root Crop", 3,
                1,2,0,1,2,
                1, 10, 9, 7.5f));
        gameData.addSeed("POTATO", new Plant("Potato", "Root Crop", 5,
                3,4,1,2,10,
                1, 20, 3, 12.5f));
        gameData.addSeed("ROSE", new Plant("Rose", "Flower", 1,
                1, 2, 0, 1, 1,
                1, 5, 5, 2.5f));
        gameData.addSeed("TULIP", new Plant("Tulip", "Flower", 2,
                2, 3, 0, 1, 1,
                1, 10, 9, 5));
        gameData.addSeed("SUNFLOWER", new Plant("Sunflower", "Flower", 3,
                2, 3, 1, 2, 1,
                1, 20, 19, 7.5f));
        gameData.addSeed("MANGO", new Plant("Mango", "Fruit Tree", 10,
                7, 7, 4, 4, 15,
                5, 100, 8, 25));
        gameData.addSeed("APPLE", new Plant("Apple", "Fruit Tree", 10,
                7, 7, 5, 5, 15,
                10, 200, 5, 25));

        // initialize tools
        gameData.addTool("WATER", new Tool("Watering Can", 0, 0.5f));
        gameData.addTool("PLOW", new Tool("Plow", 0, 0.5f));
        gameData.addTool("FERTILIZER", new Tool("Fertilizer", 10, 4));
        gameData.addTool("SHOVEL", new Tool("Shovel", 7, 2));
        gameData.addTool("PICKAXE", new Tool("Pickaxe", 50, 15));
        report.setText("");

        gridInit();
        toggleTile(false);
    }

    /**
     * updates register type of farmer
     */
    public void updateRegisterInfo() {
        registerInfo.setText("Register Type: " + registerType);
    }

    /**
     * updates game information
     */
    public void updateGameInfo() {
        int currentDay = gameData.getDayCount();
        int playerLevel = gameData.getLevel();
        float playerExp = gameData.getExp();
        float playerMoney = gameData.getMoney();

        gameInfo.setText("Day: " + currentDay + "\t\t\tMoney: " + playerMoney +
                "\nLevel: " + playerLevel + "\t\t\tExp: " + playerExp);
    }

    /**
     *
     * @param event perform action on click
     */
    public void onToolClick(ActionEvent event) {
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
            case "fertilizer" -> mode = FarmerAction.FERTILIZER;
        }

        report.setText("You are in " + tool + " mode." +
                "\nPlease Select a tile.");
    }

    /**
     *
     * @param event perform action on click
     */

    public void onTileClick(ActionEvent event) {
        String tempTileID = ((Button) event.getSource())
                .getText()
                .substring(5);
        int harvestTime;
        int tileID = Integer.parseInt(tempTileID);

        switch (mode) {
            case PLOW -> {
                gameData.activeTool("PLOW", tileID);

                // only plow if the tile is not plowed
                if (gameData.getTile(tileID).getStatus() == TileStatus.PLOWED) {
                    report.setText("Tile " + tileID + " is now plowed.");
                } else {
                    report.setText("Can't plow tile " + tileID + ".");
                }
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
                            "If it's a tree, make sure it's not on the edge of the farm.\n " +
                            "Otherwise, check if you have enough money.");
                }
            }
            case WATER -> {
                gameData.activeTool("WATER", tileID);

                // only water if a crop exists
                if (gameData.getTile(tileID).getStatus() == TileStatus.PLANTED) {
                    report.setText("Tile " + tileID + " is now watered.");
                } else {
                    report.setText("Can't water tile " + tileID + ".");
                }
            }
            case FERTILIZER -> {
                gameData.activeTool("FERTILIZER", tileID);

                // only fertilize if a crop exists
                if (gameData.getTile(tileID).getStatus() == TileStatus.PLANTED) {
                    report.setText("Tile " + tileID + " is now fertilized.");
                } else {
                    report.setText("Can't fertilize tile " + tileID + ".");
                }
            }
            case HARVEST -> {
                gameData.harvest(tileID);
                boolean harvestSuccess = gameData.getHarvestSuccess();
                float harvestMoney = gameData.getHarvestGain();

                if (harvestSuccess) {
                    report.setText("You harvested " + gameData.getCropName(tileID)
                            + " on tile " + tileID + " and earned " + harvestMoney
                            + " Objectcoins.");
                } else {
                    report.setText("Can't harvest tile " + tileID + ".");
                }
            }
            case SHOVEL -> {
                gameData.activeTool("SHOVEL", tileID);
                report.setText("You used a shovel on tile " + tileID + ".");

            }
            case PICKAXE -> {
                gameData.activeTool("PICKAXE", tileID);

                if (gameData.getTile(tileID).getStatus() == TileStatus.ROCK) {
                    report.setText("You used a pickaxe on tile " + tileID + ".");
                } else {
                    report.setText("Can't use pickaxe on tile " + tileID + ".");
                }
            }
        }

        // refresh UI
        toggleTile(false);
        updateGameInfo();

        try {
            loseCheck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * registers farmer
     */
    public void onRegisterClick() {
        boolean registerSuccess;

        gameData.register();
        registerSuccess = gameData.getRegisterSuccess();

        if (registerSuccess) {
            registerType = gameData.getRegisterType();
            updateGameInfo();
            updateRegisterInfo();
            report.setText("You have successfully become a " + registerType + ".");
        } else if (registerType.equals("Legendary Farmer")) {
            report.setText("You have reached the max farmer rank.");
        } else {
            report.setText("You can't register to the next farmer rank yet.");
        }

        try {
            loseCheck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * lets player sleep
     */
    public void onSleepClick() {
        gameData.endDay();
        updateGameInfo();
        toggleTile(false);
        report.setText("You slept for a day.");
    }

    /**
     *
     * @param event lets player to plant
     * @throws IOException if file not found
     */
    public void onPlantClick(ActionEvent event) throws IOException {
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

    /**
     *
     * @param event creates new game
     * @throws IOException if file not found
     */
    public void onNewGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("endgame-view.fxml"));
        Stage stage;
        Scene scene;
        Parent root = loader.load();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * checks if player is losing
     * @throws IOException if file not found
     */
    public void loseCheck() throws IOException {
        boolean isGameOver = gameData.checkGameOver();

        if (isGameOver) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("endgame-view.fxml"));
            Stage stage;
            Scene scene;
            Parent root = loader.load();

            // load endgame view
            stage = (Stage) newGame.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     *
     * @param toggle if there is an action, the tiles are shown
     */
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

    /**
     * shows tile in grid
     */
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
                tileBtn.setOnAction(this::onTileClick);
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

    /**
     *
     * @param mode farmer's action mode
     */
     // setters
    public void setMode(FarmerAction mode) {
        this.mode = mode;
    }

    /**
     *
     * @param seedName name of seed
     */

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    /**
     *
     * @param report status of what is happening
     */
    public void setReport(String report) {
        this.report.setText(report);
    }

    /**
     *
     * @param gameData information of the game
     */

    public void setGameData(GameModel gameData) {
        this.gameData = gameData;
    }
}