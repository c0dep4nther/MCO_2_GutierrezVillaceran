package com.myfarm.mco_2_gutierrezvillaceran.model.board;

import com.myfarm.mco_2_gutierrezvillaceran.model.Plant;
import com.myfarm.mco_2_gutierrezvillaceran.model.TileStatus;

/**
 * class for tile
 */
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
     * sets tile default information
     * @param isEdge if tile is on edge
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
     * getter for status of tile
     * @return tile status
     */
    public TileStatus getStatus() {
        return status;
    }

    /**
     * getter for crop
     * @return plant/crop
     */
    public Plant getCrop() {
        return crop;
    }

    /**
     * getter for crop name
     * @return crop name
     */
    public String getCropName() {
        return cropName;
    }

    /**
     * getter for water level
     * @return level of water applied
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * getter for date of harvest
     * @return date of plant's harvest
     */
    public int getHarvestDate() {
        return harvestDate;
    }

    /**
     * getter for fertilizer level
     * @return amount of fertilizer applied
     */
    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    /**
     * getter for if edge tile
     * @return if the selected plot is on the edge
     */
    public boolean getIsEdge() {
        return isEdge;
    }

    // setters

    /**
     * setter for status of tile
     * @param status status of tile
     */
    public void setStatus(TileStatus status) {
        this.status = status;
    }

    /**
     * setter for harvest date
     * @param harvestDate date of plant's harvest
     * @param harvestTime amount of days for plant to be harvested
     */
    public void setHarvestDate(int harvestDate, int harvestTime) {
        // get harvest time from crop
        // add it to day count from board
        this.harvestDate = harvestDate + harvestTime;
    }

    /**
     * setter for crop
     * @param crop plant details
     */
    public void setCrop(Plant crop) {
        this.crop = crop;
        this.cropName = crop.getName();
        this.cropType = crop.getType();
    }
}
