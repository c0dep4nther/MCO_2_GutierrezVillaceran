package com.myfarm.mco_2_gutierrezvillaceran.model;

public enum FarmerType {
    /**
     * enumerate status of farmer
     */
    FARMER("Farmer",0,0,
            0,0,0,
            0),
    REGISTERED("Registered Farmer", 5,1,
            -1,0,0,
            200),
    DISTINGUISHED("Distinguished Farmer",10,2,
            -2,1,0,
            300),
    LEGENDARY("Legendary Farmer",15,4,
            -3,2,1,
            400);

    //instance fields
    private final String farmerType;
    private final int levelRequirement;
    private final int bonusEarn;
    private final int seedDiscount;
    private final int waterBL;
    private final int fertilizerBL;
    private final int registerFee;

    /**
     *
     * @param farmerType name of farmer type
     * @param levelRequirement level requirement for updated farmer type
     * @param bonusEarn bonus points earned
     * @param seedDiscount seed discount
     * @param waterBL water bonus limit
     * @param fertilizerBL fertilizer bonus limit
     * @param registerFee registration fee
     */
    FarmerType(String farmerType, int levelRequirement, int bonusEarn,
               int seedDiscount, int waterBL, int fertilizerBL,
               int registerFee) {
        this.farmerType = farmerType;
        this.levelRequirement = levelRequirement;
        this.bonusEarn = bonusEarn;
        this.seedDiscount = seedDiscount;
        this.waterBL = waterBL;
        this.fertilizerBL = fertilizerBL;
        this.registerFee = registerFee;
    }

    /**
     *
     * @return farmer type
     */
    public String getFarmerType() {
        return farmerType;
    }

    /**
     *
     * @return level requirement
     */
    public int getLvlRequirement() {
        return levelRequirement;
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
     * @return fertilizer bonus limit
     */
    public int getFertilizerBL() {
        return fertilizerBL;
    }

    /**
     *
     * @return registration fee
     */
    public int getRegisterFee() {
        return registerFee;
    }

    /**
     *
     * @return bonus earned
     */
    public int getBonusEarn() {
        return bonusEarn;
    }

    /**
     *
     * @return seed discount
     */
    public int getSeedDiscount() {
        return seedDiscount;
    }
}
