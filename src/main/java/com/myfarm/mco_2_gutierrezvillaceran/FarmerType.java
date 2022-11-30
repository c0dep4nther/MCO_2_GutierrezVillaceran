package com.myfarm.mco_2_gutierrezvillaceran;

public enum FarmerType {
    FARMER("Farmer",0,0,
            0,0,0,
            0),
    REGISTERED("Registered Farmer", 5,1,
            -1,0,0,
            200),
    DISTINGUISHED("Distinguished Farmer",10,2,
            -2,1,0,
            300),
    LEGENDARY("Honorable Farmer",15,4,
            -3,2,1,
            400);

    //instance fields
    private final String farmerType;
    private final int levelRequirement;
    private final int bonusEarn;
    private final int seedDiscount;
    private final int waterBL;
    private final int fertilizerBL;
    private final int registrationFee;

    FarmerType(String farmerType, int levelRequirement, int bonusEarn, int seedDiscount, int waterBL, int fertilizerBL, int registrationFee) {
        this.farmerType = farmerType;
        this.levelRequirement = levelRequirement;
        this.bonusEarn = bonusEarn;
        this.seedDiscount = seedDiscount;
        this.waterBL = waterBL;
        this.fertilizerBL = fertilizerBL;
        this.registrationFee = registrationFee;
    }

    public String getFarmerType() {
        return farmerType;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getWaterBL() {
        return waterBL;
    }

    public int getFertilizerBL() {
        return fertilizerBL;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public int getBonusEarn() {
        return bonusEarn;
    }

    public int getSeedDiscount() {
        return seedDiscount;
    }
}
