package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.board.Board;

import java.util.HashMap;

public class GameModel {
    private Board farmLand = new Board();
    private Farmer player = new Farmer();
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
        return player.getTotalExp();
    }
}
