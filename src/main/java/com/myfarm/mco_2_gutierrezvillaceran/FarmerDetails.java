package com.myfarm.mco_2_gutierrezvillaceran;

import java.util.Scanner;

public class FarmerDetails {

    private int seedCount=0;
    private int fertilizerCount=5;
    private int objectCoins=100;
    private int farmerLevel=0;
    private RegisterFarmer farmerStatus=RegisterFarmer.FARMER;
    private int experience=0;


    /**method to increase money and experience when harvesting*/
    public void harvestPlant(Plants plant, int numOfHarvest){
        int sellingPrice=0;
        setExperience(experience+1); //increases experience when harvesting

        //NOT SURE SA COMPUTATION NG WATER, FERTILIZER, AND CROP BONUS
        sellingPrice=farmerStatus.getBonusLimits()+plant.getSellPrice()+plant.getFertilizerBonus()+ plant.getWaterBonus();
        setObjectCoins(objectCoins+sellingPrice*numOfHarvest);

    }

    public int getSeedCount() {
        return seedCount;
    }

    public void setSeedCount(int seedCount) {
        this.seedCount = seedCount;
    }

    public int getFertilizerCount() {
        return fertilizerCount;
    }

    public void setFertilizerCount(int fertilizerCount) {
        this.fertilizerCount = fertilizerCount;
    }

    public int getObjectCoins() {
        return objectCoins;
    }

    public void setObjectCoins(int objectCoins) {
        this.objectCoins = objectCoins;
    }

    public int getFarmerLevel() {
        return farmerLevel;
    }

    public void setFarmerLevel(int experience) {
        this.farmerLevel = this.experience/100;
    }

    public RegisterFarmer getFarmerStatus() {
        return farmerStatus;
    }

    public void setFarmerStatus(RegisterFarmer farmerStatus) {
        this.farmerStatus = farmerStatus;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void displayFarmerDetails(){
        System.out.println();
        System.out.println("FARMER DETAILS");
        System.out.println("Crops planted: "+getSeedCount());
        System.out.println("Experience: "+getExperience());
        System.out.println("Level: "+getFarmerLevel());
        System.out.println("Status: "+getFarmerStatus().getFarmerType());
        System.out.println("Objectcoins: "+getObjectCoins());
        System.out.println("Fertilizer count: "+getFertilizerCount());
    }

    public void buySeeds(Plants plant){
        //subtracts player's money
        setObjectCoins(getObjectCoins()-plant.getSeedCost());

        //increases player's seed count
        setSeedCount(getSeedCount()+1);

        //prints transaction
        System.out.println("You planted "+ plant.getName()+" which costs " + plant.getSeedCost()+" Objectcoins.");
        System.out.println("You only have "+getObjectCoins()+" Objectcoins left.");
    }

    public void displayFarmerType(){
        System.out.println();
        System.out.printf("%-30s%-20s%-20s%-35s%-20s%-20s%n","Farmer Type","Level Requirement","Earning/Buying",
                "Water/Fertilizer bonus limits","Harvest time","Registration Fee");
        for(RegisterFarmer register: RegisterFarmer.values())
            System.out.printf("%-30s%-20s+/-%-17s+%-35s%-20s%-20s%n", register.getFarmerType(),register.getLevelRequirement(),
                    register.getEarning(),register.getBonusLimits(),register.getHarvestTime(),register.getRegistrationFee());
        System.out.println();
    }

    public void buyFertilizer(){
        Scanner input=new Scanner(System.in);

        System.out.println("You don't have enough fertilizers! Would you like to buy one for 10 Objectcoins? Y/N");
        String buyFertilizer=input.nextLine();
        if(buyFertilizer.equals("Y")||buyFertilizer.equals("y")){
            System.out.println("How many fertilizers?");
            int numFertilizer=input.nextInt();
            input.nextLine();

            int cost=numFertilizer*10;
            if(cost<=getObjectCoins()){
                setObjectCoins(getObjectCoins()-cost);
                setFertilizerCount(getFertilizerCount()+numFertilizer);
            }
            else{
                System.out.println("Not enough money!");
            }
        }
        else{
            System.out.println("Can't fertilize!");
        }
    }


}
