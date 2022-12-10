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

    /**
     *
     * @param seedID ID of seed
     * @param tileID ID of tile
     */
    public void plantSeed(String seedID, int tileID) {
        Plant seed = seedList.get(seedID);
        farmLand = player.plantSeed(seed, farmLand, tileID);
    }

    /**
     * ends day
     */
    public void endDay() {
        farmLand.endDay();
    }

    /**
     *
     * @param tileID ID of tile
     */
    public void harvest(int tileID) {
        farmLand = player.harvestPlant(farmLand, tileID);
    }

    /**
     * registers farmer
     */
    public void register() {
        player.registerFarmer();
    }

    /**
     *
     * @return checks if game is over
     */
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

    /**
     *
     * @return plant's success to be planted
     */
    public boolean getPlantSuccess() {
        return farmLand.getPlantSuccess();
    }

    /**
     *
     * @return number of days that passed
     */
    public int getDayCount() {
        return farmLand.getDayCount();
    }

    /**
     *
     * @return level of farmer
     */
    public int getLevel() {
        return player.getLevel();
    }

    /**
     *
     * @return money of farmer
     */
    public float getMoney() {
        return player.getMoney();
    }

    /**
     *
     * @return experience of farmer
     */
    public float getExp() {
        return player.getExp();
    }

    /**
     *
     * @param tileID ID of tile
     * @return tile
     */
    public Tile getTile(int tileID) {
        return farmLand.getTile(tileID);
    }

    /**
     *
     * @param tileID ID of tile
     * @return status of tile
     */
    public TileStatus getTileStatus(int tileID) {
        return farmLand.getTile(tileID).getStatus();
    }

    /**
     *
     * @param tileID ID of tile
     * @return harvest time of plant
     */
    public int getHarvestTime(int tileID) {
        return farmLand.getTile(tileID).getCrop().getHarvestTime();
    }

    /**
     *
     * @param seedID ID of seed
     * @return cost of seed
     */

    public int getSeedCost(String seedID) {
        int seedDiscount = player.getRegister().getSeedDiscount();

        return seedList.get(seedID).getSeedCost() + seedDiscount;
    }

    /**
     *
     * @return success of harvest
     */
    public boolean getHarvestSuccess() {
        return farmLand.getHarvestSuccess();
    }

    /**
     *
     * @return gain in harvest
     */
    public float getHarvestGain() {
        return farmLand.getHarvestGain();
    }

    /**
     *
     * @param tileID ID of tile
     * @return name of crop
     */
    public String getCropName(int tileID) {
        return farmLand.getTile(tileID).getCropName();
    }

    /**
     *
     * @return type of farmer's register
     */
    public String getRegisterType() {
        return player.getRegisterType();
    }

    /**
     *
     * @return success in registering
     */
    public boolean getRegisterSuccess() {
        return player.getRegisterSuccess();
    }
}
