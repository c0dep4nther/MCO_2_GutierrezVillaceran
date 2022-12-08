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
    public void toolAction (Tile farmLot) {
        switch (name) {
            case "Watering Can" -> {
                actionSuccess = false;

                // water only if a crop exists
                if (farmLot.getCrop() != null) {
                    farmLot.addWaterLevel();
                    actionSuccess = true;
                }
            }
            case "Plow" -> {
                actionSuccess = false;

                // only plow if tile is unplowed
                if (farmLot.getStatus() == TileStatus.UNPLOWED) {
                    farmLot.setStatus(TileStatus.PLOWED);
                    actionSuccess = true;
                }
            }
            case "Fertilizer" -> {
                actionSuccess = false;

                // fertilize only if a crop exists
                if (farmLot.getCrop() != null) {
                    farmLot.addFertilizerLevel();
                    actionSuccess = true;
                }
            }
//            case "Pickaxe" -> {
//
//            }
            case "Shovel" -> {
                actionSuccess = true;

                // reset the tile
                farmLot.resetTile();
            }
        }
    }

    /**
     *
     * @return name of tool
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return experience gained by using tool
     */
    public float getExpGain() {
        return expGain;
    }

    public int getCost() {
        return cost;
    }

    public boolean getActionSuccess() {
        return actionSuccess;
    }
}
