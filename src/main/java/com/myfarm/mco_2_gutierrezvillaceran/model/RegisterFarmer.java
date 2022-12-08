package com.myfarm.mco_2_gutierrezvillaceran.model;

public enum RegisterFarmer {
    FARMER("Farmer",0,0,0,0,0,0),
    REGISTERED("Registered Farmer", 10,2,-2,0,-0.05f,200),
    DISTINGUISHED("Distinguished Farmer",15,3,-3,1,-0.1f,250),
    HONORABLE("Honorable Farmer",20,5,-5,2,-0.15f,350);

    //instance fields
    private final String farmerType;
    private final int levelRequirement;
    private final int earning;
    private final int buying;
    private final int bonusLimits;
    private  final float harvestTime;
    private final int registrationFee;

    RegisterFarmer(String farmerType, int levelRequirement,  int earning, int buying, int bonusLimits, float harvestTime, int registrationFee) {
        this.farmerType = farmerType;
        this.levelRequirement = levelRequirement;
        this.earning = earning;
        this.buying = buying;
        this.bonusLimits = bonusLimits;
        this.harvestTime = harvestTime;
        this.registrationFee = registrationFee;
    }

    public String getFarmerType() {
        return farmerType;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getBonusLimits() {
        return bonusLimits;
    }

    public float getHarvestTime() {
        return harvestTime;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public int getEarning() {
        return earning;
    }

    public int getBuying() {
        return buying;
    }
}
