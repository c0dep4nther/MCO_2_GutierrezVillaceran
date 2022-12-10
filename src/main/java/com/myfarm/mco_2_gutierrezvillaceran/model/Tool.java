package com.myfarm.mco_2_gutierrezvillaceran.model;

import com.myfarm.mco_2_gutierrezvillaceran.model.board.Tile;

public class Tool {
    private final String name;
    private final int cost;
    private final float expGain;
    private boolean actionSuccess;

    /**
     *
     * @param name name of tool
     * @param cost cost of tool
     * @param expGain experience gained by using tool
     */
    public Tool(String name, int cost, float expGain) {
        this.name = name;
        this.cost = cost;
        this.expGain = expGain;
    }

    /**
     * @param farmLot tile performed action on
     */
    public void toolAction (Tile farmLot, float money) {

        // reset success tracking
        actionSuccess = false;

        // check if money is sufficient
        if (money >= cost) {
            switch (name) {
                case "Watering Can" -> {
                    // water only if a crop exists
                    if (farmLot.getCrop() != null) {
                        farmLot.addWaterLevel();
                        actionSuccess = true;
                    }
                }
                case "Plow" -> {
                    // only plow if tile is unplowed
                    if (farmLot.getStatus() == TileStatus.UNPLOWED) {
                        farmLot.setStatus(TileStatus.PLOWED);
                        actionSuccess = true;
                    }
                }
                case "Fertilizer" -> {
                    // fertilize only if a crop exists
                    if (farmLot.getCrop() != null) {
                        farmLot.addFertilizerLevel();
                        actionSuccess = true;
                    }
                }
                case "Pickaxe" -> {
                    // only use on rock tiles
                    if (farmLot.getStatus() == TileStatus.ROCK) {
                        farmLot.setStatus(TileStatus.UNPLOWED);
                        actionSuccess = true;
                    }
                }
                case "Shovel" -> {
                    actionSuccess = true;

                    // reset the tile
                    farmLot.resetTile();
                }
            }
        }
    }

    /**
     *
     * @return experience gained by using tool
     */
    public float getExpGain() {
        return expGain;
    }

    /**
     *
     * @return cost of action
     */
    public int getCost() {
        return cost;
    }

    /**
     *
     * @return success of action
     */
    public boolean getActionSuccess() {
        return actionSuccess;
    }
}
