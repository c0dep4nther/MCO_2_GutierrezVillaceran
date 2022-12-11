package com.myfarm.mco_2_gutierrezvillaceran.model;

/**
 * class for plant
 */
public class Plant {
    private final String name;
    private final String type;
    private final int harvestTime;
    private final int waterNeed;
    private final int waterBL;
    private final int fertilizerNeed;
    private final int fertilizerBL;
    private final int maxProduce;
    private final int minProduce;
    private final int seedCost;
    private final int sellPrice;
    private final float expGain;

    /**
     * constructor for plant
     * @param name  name of plant
     * @param type  type of plant
     * @param harvestTime   harvest time of plant
     * @param waterNeed amount of water needed by plant
     * @param waterBL   bonus limit of plant
     * @param fertilizerNeed    amount of fertilizer needed by plant
     * @param fertilizerBL fertilizer limit of plant
     * @param maxProduce    maximum crop produced by plant
     * @param minProduce    minimum crop produce by plant
     * @param seedCost  cost of buying seed
     * @param sellPrice price of selling crop
     * @param expGain   experience gained per crop
     */
    public Plant(String name, String type, int harvestTime,
                 int waterNeed, int waterBL, int fertilizerNeed, int fertilizerBL, int maxProduce,
                 int minProduce, int seedCost, int sellPrice,
                 float expGain) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.waterBL = waterBL;
        this.fertilizerNeed = fertilizerNeed;
        this.fertilizerBL = fertilizerBL;
        this.maxProduce = maxProduce;
        this.minProduce = minProduce;
        this.seedCost = seedCost;
        this.sellPrice = sellPrice;
        this.expGain = expGain;
    }

    // getters

    /**
     * getter for name of plant
     * @return name of plant
     */
    public String getName() {
        return name;
    }

    /**
     * getter for type of plant
     * @return type of plant
     */
    public String getType() {
        return type;
    }

    /**
     * getter for harvest time of plant
     * @return harvest time of plant
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /**
     * getter for water needed by plant
     * @return amount of water needed by plant
     */
    public int getWaterNeed() {
        return waterNeed;
    }

    /**
     * getter for water bonus limit
     * @return water bonus limit
     */
    public int getWaterBL() {
        return waterBL;
    }

    /**
     * getter for amount of fertilizer needed by plant
     * @return amount of fertilizer needed by plant
     */
    public int getFertilizerNeed() {
        return fertilizerNeed;
    }

    /**
     * getter for fertilizer bonus limit
     * @return fertilizer bonus limit
     */
    public int getFertilizerBL() {
        return fertilizerBL;
    }

    /**
     * getter for maximum crop produce of plant
     * @return maximum crop produce of plant
     */
    public int getMaxProduce() {
        return maxProduce;
    }

    /**
     * getter for minimum crop produce by plant
     * @return minimum crop produce by plant
     */
    public int getMinProduce() {
        return minProduce;
    }

    /**
     * getter for cost of buying seed
     * @return cost of buying seed
     */

    public int getSeedCost() {
        return seedCost;
    }

    /**
     * getter for price of selling crop
     * @return price of selling crop
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * getter for experience gained per crop
     * @return experience gained per crop
     */
    public float getExpGain() {
        return expGain;
    }
}
