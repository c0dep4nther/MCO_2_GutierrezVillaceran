package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.board.Tile;

public class Tool {
    private final String name;
    private final int cost;
    private final float expGain;

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
     * @return tile
     */
    public Tile toolAction (Tile farmLot) {
        switch (name) {
            case "Watering Can" -> {
                System.out.println("Tile watered.");
                farmLot.setWaterLevel(farmLot.getWaterLevel() + 1);
            }
            case "Plow" -> {
                System.out.println("Tile is now plowed.");
                farmLot.setStatus(TileStatus.PLOWED);
            }
        }

        return farmLot;
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
}
