package com.myfarm.mco_2_gutierrezvillaceran.model;
/**
 * enumerate status of farmer
 */
public enum FarmerType {
    /**
     * player is a farmer
     */
    FARMER("Farmer",0,0,
            0,0,0,
            0),
    /**
     * player is a registered farmer
     */
    REGISTERED("Registered Farmer", 5,1,
            -1,0,0,
            200),
    /**
     * player is a distinguished farmer
     */
    DISTINGUISHED("Distinguished Farmer",10,2,
            -2,1,0,
            300),
    /**
     * player is a legendary farmer
     */
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
     * constructor for farmer type
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
     * getter for farmer type
     * @return farmer type
     */
    public String getFarmerType() {
        return farmerType;
    }

    /**
     * getter for level requirement
     * @return level requirement
     */
    public int getLvlRequirement() {
        return levelRequirement;
    }

    /**
     * getter for bonus limit
     * @return water bonus limit
     */
    public int getWaterBL() {
        return waterBL;
    }

    /**
     * getter for fertilizer bonus limit
     * @return fertilizer bonus limit
     */
    public int getFertilizerBL() {
        return fertilizerBL;
    }

    /**
     * getter for registration fee
     * @return registration fee
     */
    public int getRegisterFee() {
        return registerFee;
    }

    /**
     * getter for bonus earned
     * @return bonus earned
     */
    public int getBonusEarn() {
        return bonusEarn;
    }

    /**
     * getter for seed discount
     * @return seed discount
     */
    public int getSeedDiscount() {
        return seedDiscount;
    }
}
