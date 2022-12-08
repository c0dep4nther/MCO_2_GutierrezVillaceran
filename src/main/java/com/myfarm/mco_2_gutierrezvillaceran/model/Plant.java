package com.myfarm.mco_2_gutierrezvillaceran.model;

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
     *
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
     * @return name of plant
     */
    public String getName() {
        return name;
    }

    /**
     * @return type of plant
     */
    public String getType() {
        return type;
    }

    /**
     * @return harvest time of plant
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /**
     * @return amount of water needed by plant
     */
    public int getWaterNeed() {
        return waterNeed;
    }

    /**
     *
     * @return water bonus limit
     */
    public int getWaterBL() {
        return waterBL;
    }

    /**
     *
     * @return amount of fertilizer needed by plant
     */
    public int getFertilizerNeed() {
        return fertilizerNeed;
    }

    /**
     *
     * @return fertilizer bonus limit
     */
    public int getFertilizerBL() {
        return fertilizerBL;
    }

    /**
     *
     * @return maximum crop produce of plant
     */
    public int getMaxProduce() {
        return maxProduce;
    }

    /**
     *
     * @return minimum crop produce by plant
     */
    public int getMinProduce() {
        return minProduce;
    }

    /**
     *
     * @return cost of buying seed
     */

    public int getSeedCost() {
        return seedCost;
    }

    /**
     *
     * @return price of selling crop
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * @return experience gained per crop
     */
    public float getExpGain() {
        return expGain;
    }
}
