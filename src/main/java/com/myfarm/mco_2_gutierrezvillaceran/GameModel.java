package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.board.Board;

import java.util.HashMap;

public class GameModel {
    private Board gameBoard = new Board();
    private Farmer player = new Farmer();
    private final HashMap<Integer, Plant> seedList = new HashMap<>();
    public final HashMap<String, Tool> toolInventory = new HashMap<>();

    public void addSeed(int seedID, Plant seed) {
        seedList.put(seedID, seed);
    }

    public void addTool(String toolID, Tool tool) {
        toolInventory.put(toolID, tool);
    }

    // getters
    public int getDayCount() {
        return gameBoard.getDayCount();
    }

    public float getMoney() {
        return player.getMoney();
    }

    public int getLevel() {
        return player.getLevel();
    }

    public float getExp() {
        return player.getTotalExp();
    }

}
