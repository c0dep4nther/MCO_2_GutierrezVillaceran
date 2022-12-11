package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.model.Farmer;
import com.myfarm.mco_2_gutierrezvillaceran.model.Plant;
import com.myfarm.mco_2_gutierrezvillaceran.model.TileStatus;
import com.myfarm.mco_2_gutierrezvillaceran.model.Tool;
import com.myfarm.mco_2_gutierrezvillaceran.model.board.Board;
import com.myfarm.mco_2_gutierrezvillaceran.model.board.Tile;

import java.util.HashMap;

/**
 * class for game logic
 */
public class GameModel {

    private Board farmLand = new Board();
    private final Farmer player = new Farmer();
    private final HashMap<String, Plant> seedList = new HashMap<>();

    /**
     * Hash map for tool inventory
     */
    public final HashMap<String, Tool> toolInventory = new HashMap<>();
    /**
     * default constructor for GameModel
     */
    public GameModel(){}

    /**
     * adds seed to the hashmap
     * @param seedID ID of seed
     * @param seed the seed of plant
     */
    public void addSeed(String seedID, Plant seed) {
        seedList.put(seedID, seed);
    }

    /**
     * adds tool of farmer to the hashmap
     * @param toolID ID of tool
     * @param tool the tool of farmer
     */
    public void addTool(String toolID, Tool tool) {
        toolInventory.put(toolID, tool);
    }

    /**
     * uses tool
     * @param toolID ID of tool
     * @param tileID ID of tile
     */
    public void activeTool(String toolID, int tileID) {
        Tool tool = toolInventory.get(toolID);
        farmLand = player.useTool(tool, farmLand, tileID);
    }

    /**
     * plants seed to the selected tile
     * @param seedID ID of seed
     * @param tileID ID of tile
     */
    public void plantSeed(String seedID, int tileID) {
        Plant seed = seedList.get(seedID);
        farmLand = player.plantSeed(seed, farmLand, tileID);
    }

    /**
     * ends day and proceeds to the next
     */
    public void endDay() {
        farmLand.endDay();
    }

    /**
     * harvests in tileID
     * @param tileID ID of tile
     */
    public void harvest(int tileID) {
        farmLand = player.harvestPlant(farmLand, tileID);
    }

    /**
     * registers farmer to the next type
     */
    public void register() {
        player.registerFarmer();
    }

    /**
     * checks if game is over
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
     * getter for plant success
     * @return plant's success to be planted
     */
    public boolean getPlantSuccess() {
        return farmLand.getPlantSuccess();
    }

    /**
     * getter for number of days
     * @return number of days that passed
     */
    public int getDayCount() {
        return farmLand.getDayCount();
    }

    /**
     * getter for farmer's level
     * @return level of farmer
     */
    public int getLevel() {
        return player.getLevel();
    }

    /**
     * getter for farmer's money
     * @return money of farmer
     */
    public float getMoney() {
        return player.getMoney();
    }

    /**
     * getter for experience
     * @return experience of farmer
     */
    public float getExp() {
        return player.getExp();
    }

    /**
     * getter for tile
     * @param tileID ID of tile
     * @return tile
     */
    public Tile getTile(int tileID) {
        return farmLand.getTile(tileID);
    }

    /**
     * getter for tile Status
     * @param tileID ID of tile
     * @return status of tile
     */
    public TileStatus getTileStatus(int tileID) {
        return farmLand.getTile(tileID).getStatus();
    }

    /**
     * getter for harvest time
     * @param tileID ID of tile
     * @return harvest time of plant
     */
    public int getHarvestTime(int tileID) {
        return farmLand.getTile(tileID).getCrop().getHarvestTime();
    }

    /**
     * getter for seed cost
     * @param seedID ID of seed
     * @return cost of seed
     */

    public int getSeedCost(String seedID) {
        int seedDiscount = player.getRegister().getSeedDiscount();

        return seedList.get(seedID).getSeedCost() + seedDiscount;
    }

    /**
     * getter for harvest's success
     * @return success of harvest
     */
    public boolean getHarvestSuccess() {
        return farmLand.getHarvestSuccess();
    }

    /**
     * getter for harvest's gain
     * @return gain in harvest
     */
    public float getHarvestGain() {
        return farmLand.getHarvestGain();
    }

    /**
     * gets the name of crop
     * @param tileID ID of tile
     * @return name of crop
     */
    public String getCropName(int tileID) {
        return farmLand.getTile(tileID).getCropName();
    }

    /**
     * getter for farmer's register type
     * @return type of farmer's register
     */
    public String getRegisterType() {
        return player.getRegisterType();
    }

    /**
     * getter for success of register
     * @return success in registering
     */
    public boolean getRegisterSuccess() {
        return player.getRegisterSuccess();
    }
}
