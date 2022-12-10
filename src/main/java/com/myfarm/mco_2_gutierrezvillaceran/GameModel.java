package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.model.Farmer;
import com.myfarm.mco_2_gutierrezvillaceran.model.Plant;
import com.myfarm.mco_2_gutierrezvillaceran.model.TileStatus;
import com.myfarm.mco_2_gutierrezvillaceran.model.Tool;
import com.myfarm.mco_2_gutierrezvillaceran.model.board.Board;
import com.myfarm.mco_2_gutierrezvillaceran.model.board.Tile;

import java.util.HashMap;

public class GameModel {
    private Board farmLand = new Board();
    private final Farmer player = new Farmer();
    private final HashMap<String, Plant> seedList = new HashMap<>();
    public final HashMap<String, Tool> toolInventory = new HashMap<>();

    public void addSeed(String seedID, Plant seed) {
        seedList.put(seedID, seed);
    }

    public void addTool(String toolID, Tool tool) {
        toolInventory.put(toolID, tool);
    }

    public void activeTool(String toolID, int tileID) {
        Tool tool = toolInventory.get(toolID);
        farmLand = player.useTool(tool, farmLand, tileID);
    }

    public void plantSeed(String seedID, int tileID) {
        Plant seed = seedList.get(seedID);
        farmLand = player.plantSeed(seed, farmLand, tileID);
    }

    public void endDay() {
        farmLand.endDay();
    }

    public void harvest(int tileID) {
        farmLand = player.harvestPlant(farmLand, tileID);
    }

    public void register() {
        player.registerFarmer();
    }

    public boolean checkGameOver() {
       boolean isGameOver = false;
       boolean plantExists = false;
       int witherCount = 0;

        // loop through board to check if all plants are withered
        for (Tile tile : farmLand.getTiles().values()) {
            if (tile.getStatus() == TileStatus.PLANTED) {
                plantExists = true;
                break;
            }

            if (tile.getStatus() == TileStatus.WITHERED) {
                witherCount++;
            }
        }

        // if player can't afford anything, and no plant
        // exists on board, game over
        if (player.getMoney() < 5 && !plantExists || witherCount == 50) {
            isGameOver = true;
        }

        return isGameOver;
    }

    // getters
    public boolean getPlantSuccess() {
        return farmLand.getPlantSuccess();
    }

    public int getDayCount() {
        return farmLand.getDayCount();
    }

    public int getLevel() {
        return player.getLevel();
    }

    public float getMoney() {
        return player.getMoney();
    }

    public float getExp() {
        return player.getExp();
    }

    public Tile getTile(int tileID) {
        return farmLand.getTile(tileID);
    }

    public TileStatus getTileStatus(int tileID) {
        return farmLand.getTile(tileID).getStatus();
    }

    public int getHarvestTime(int tileID) {
        return farmLand.getTile(tileID).getCrop().getHarvestTime();
    }

    public int getSeedCost(String seedID) {
        int seedDiscount = player.getRegister().getSeedDiscount();

        return seedList.get(seedID).getSeedCost() + seedDiscount;
    }

    public boolean getHarvestSuccess() {
        return farmLand.getHarvestSuccess();
    }

    public float getHarvestGain() {
        return farmLand.getHarvestGain();
    }

    public String getCropName(int tileID) {
        return farmLand.getTile(tileID).getCropName();
    }

    public String getRegisterType() {
        return player.getRegisterType();
    }

    public boolean getRegisterSuccess() {
        return player.getRegisterSuccess();
    }
}
