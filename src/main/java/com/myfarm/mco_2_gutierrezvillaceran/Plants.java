package com.myfarm.mco_2_gutierrezvillaceran;

public class Plants {
    private String name;
    private String type;
    private int harvestTime;
    private int waterNeeded;
    private int waterBonus;
    private int fertilizerNeeded;
    private int fertilizerBonus;
    private int maxProduce;
    private int seedCost;
    private int sellPrice;
    private int minProduce;
    private float expYield;

    public Plants(String name, String type, int harvestTime, int waterNeeded,
                  int fertilizerNeeded, int minProduce,  int maxProduce, int seedCost, int sellPrice, float expYield ) {
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeeded = waterNeeded;
        this.fertilizerNeeded = fertilizerNeeded;
        this.minProduce=minProduce;
        this.maxProduce = maxProduce;
        this.seedCost = seedCost;
        this.sellPrice = sellPrice;
        this.expYield=expYield;


        //@mark edit the bonus
        switch (name) {
            case "Turnip", "Carrot", "Rose" -> {
                waterBonus = 2;
                fertilizerBonus = 1;
            }
            case "Stargazer" -> {
                waterBonus = 3;
                fertilizerBonus = 1;
            }
        }
    }

//    getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    public int getMaxProduce() {
        return maxProduce;
    }

    public int getSeedCost() {
        return seedCost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getWaterBonus() {return waterBonus;}

    public int getFertilizerBonus() {return fertilizerBonus;}

    //    setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWaterNeeded(int waterNeeded) {
        this.waterNeeded = waterNeeded;
    }
    public void setWaterBonus(int waterBonus) {this.waterBonus = waterBonus;}
    public void setFertilizerNeeded(int fertilizerNeeded) {
        this.fertilizerNeeded = fertilizerNeeded;
    }
    public void setFertilizerBonus(int fertilizerBonus) {this.fertilizerBonus = fertilizerBonus;}

    public void setMaxProduce(int maxProduce) {
        this.maxProduce = maxProduce;
    }

    public void setSeedCost(int seedCost) {
        this.seedCost = seedCost;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    public int getMinProduce() {
        return minProduce;
    }

    public void setMinProduce(int minProduce) {
        this.minProduce = minProduce;
    }

    public float getExpYield() {
        return expYield;
    }

    public void setExpYield(float expYield) {
        this.expYield = expYield;
    }


    //edit override
    @Override
    public String toString() {
        return "myfarm.Plants [name=" + name + ", type=" + type + ", harvestTime=" +
                harvestTime + ", waterNeeded=" + waterNeeded + ", fertilizerNeeded=" +
                fertilizerNeeded + ", produceCountMax=" +
                maxProduce + ", seedCost=" + seedCost + ", sellPrice=" + sellPrice + "]";
    }
}
