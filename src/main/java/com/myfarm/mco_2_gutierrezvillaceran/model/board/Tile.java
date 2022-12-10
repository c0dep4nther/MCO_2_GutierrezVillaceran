package com.myfarm.mco_2_gutierrezvillaceran.model.board;

import com.myfarm.mco_2_gutierrezvillaceran.model.Plant;
import com.myfarm.mco_2_gutierrezvillaceran.model.TileStatus;

public class Tile {
    private TileStatus status;
    private Plant crop;
    private String cropName;
    private String cropType;
    private final boolean isEdge;
    private int waterLevel;
    private int fertilizerLevel;
    private int harvestDate;

    /**
     * tile constructor
     */
    public Tile(boolean isEdge) {
        status = TileStatus.UNPLOWED;
        crop = null;
        cropName = null;
        cropType = null;
        this.isEdge = isEdge;
        waterLevel = 0;
        fertilizerLevel = 0;
    }

    /**
     * increases water level
     */
    public void addWaterLevel() {
        waterLevel++;
    }

    /**
     * resets tile's information
     */

    public void resetTile() {
        status = TileStatus.UNPLOWED;
        crop = null;
        cropName = null;
        cropType = null;
        waterLevel = 0;
        fertilizerLevel = 0;
    }

    /**
     * increases fertilizer level
     */
    public void addFertilizerLevel() {
        fertilizerLevel++;
    }

    // getters

    /**
     *
     * @return tile status
     */
    public TileStatus getStatus() {
        return status;
    }

    /**
     *
     * @return plant/crop
     */
    public Plant getCrop() {
        return crop;
    }

    /**
     *
     * @return crop name
     */
    public String getCropName() {
        return cropName;
    }

    /**
     *
     * @return level of water applied
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     *
     * @return date of plant's harvest
     */
    public int getHarvestDate() {
        return harvestDate;
    }

    /**
     *
     * @return amount of fertilizer applied
     */
    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    /**
     *
     * @return if the selected plot is on the edge
     */
    public boolean getIsEdge() {
        return isEdge;
    }

    // setters

    /**
     *
     * @param status status of tile
     */
    public void setStatus(TileStatus status) {
        this.status = status;
    }

    /**
     *
     * @param harvestDate date of plant's harvest
     * @param harvestTime amount of days for plant to be harvested
     */
    public void setHarvestDate(int harvestDate, int harvestTime) {
        // get harvest time from crop
        // add it to day count from board
        this.harvestDate = harvestDate + harvestTime;
    }

    /**
     *
     * @param crop plant details
     */
    public void setCrop(Plant crop) {
        this.crop = crop;
        this.cropName = crop.getName();
        this.cropType = crop.getType();
    }
}
