package com.myfarm.mco_2_gutierrezvillaceran.model;

import com.myfarm.mco_2_gutierrezvillaceran.model.board.Board;
import com.myfarm.mco_2_gutierrezvillaceran.model.board.Tile;

import java.util.Objects;

public class Farmer {
    private FarmerType type = FarmerType.FARMER;
    private float exp = 0;
    private float money = 100;
    private int level = 1;

    /**
     * increase level of farmer
     */
    private void levelUp() {
        // for every 100 exp, level up
        if (exp >= 100) {
            level++;
            exp -= 100;
            System.out.println("You leveled up to level " + level + "!");
        }
    }

    /**
     *
     * @param item tool used
     * @param farmLand initialized board
     * @return updated board
     */
    public Board useTool(Tool item, Board farmLand, int tileNumber) {
        Tile farmLot;
        boolean success;

        farmLot = farmLand.getTile(tileNumber);
        item.toolAction(farmLot);

        success = item.getActionSuccess();

        if (success) {
            exp += item.getExpGain();
            money -= item.getCost();
        }

        // set status to board
        farmLand.getTile(tileNumber).setStatus(farmLot.getStatus());
        levelUp();

        return farmLand;
    }

    /**
     * Planting of seeds
     * @param seed plant information
     * @param farmLand board
     * @return updated board
     */
    public Board plantSeed(Plant seed, Board farmLand, int tileNumber) {
        int dayCount = farmLand.getDayCount();
        int harvestTime = seed.getHarvestTime();
        Tile farmLot = farmLand.getTile(tileNumber);

        // reset success tracker
        farmLand.setPlantSuccess(false);

        // if crop type is Fruit Tree, occupy surrounding tiles
        if (seed.getType().equals("Fruit Tree") && farmLot.getStatus().equals(TileStatus.PLOWED) && checkMoney(seed.getSeedCost())) {
            boolean tileEdge = farmLot.getIsEdge();

            // check if planting location is edge otherwise, check if surrounding tiles are empty
            if (!tileEdge) {
                TileStatus leftStatus = farmLand.getTile(tileNumber - 1).getStatus();
                TileStatus rightStatus = farmLand.getTile(tileNumber + 1).getStatus();
                TileStatus topStatus = farmLand.getTile(tileNumber - 5).getStatus();
                TileStatus bottomStatus = farmLand.getTile(tileNumber + 5).getStatus();
                TileStatus topLeftStatus = farmLand.getTile(tileNumber - 6).getStatus();
                TileStatus topRightStatus = farmLand.getTile(tileNumber - 4).getStatus();
                TileStatus bottomLeftStatus = farmLand.getTile(tileNumber + 6).getStatus();
                TileStatus bottomRightStatus = farmLand.getTile(tileNumber + 4).getStatus();

                if ((leftStatus == TileStatus.UNPLOWED || leftStatus == TileStatus.PLOWED)
                        && (rightStatus == TileStatus.UNPLOWED || rightStatus == TileStatus.PLOWED)
                        && (topStatus == TileStatus.UNPLOWED || topStatus == TileStatus.PLOWED)
                        && (bottomStatus == TileStatus.UNPLOWED || bottomStatus == TileStatus.PLOWED)
                        && (topLeftStatus == TileStatus.UNPLOWED || topLeftStatus == TileStatus.PLOWED)
                        && (topRightStatus == TileStatus.UNPLOWED || topRightStatus == TileStatus.PLOWED)
                        && (bottomLeftStatus == TileStatus.UNPLOWED || bottomLeftStatus == TileStatus.PLOWED)
                        && (bottomRightStatus == TileStatus.UNPLOWED || bottomRightStatus == TileStatus.PLOWED)) {

                    // perform planting action
                    farmLot.setCrop(seed);
                    farmLot.setStatus(TileStatus.PLANTED);
                    farmLot.setHarvestDate(dayCount, harvestTime);
                    farmLand.setPlantSuccess(true);
                    money -= seed.getSeedCost();
                }
            }
        // aside from Fruit Trees, all other crops are to be planted normally
        } else if (farmLot.getStatus() == TileStatus.PLOWED && checkMoney(seed.getSeedCost())) {
            farmLot.setCrop(seed);
            farmLot.setStatus(TileStatus.PLANTED);
            farmLot.setHarvestDate(dayCount, harvestTime);
            farmLand.setPlantSuccess(true);
            money -= seed.getSeedCost();
        }

        return farmLand;
    }

    /**
     *
     * @param farmLand board
     * @return updated board
     */
    public Board harvestPlant(Board farmLand, int tileNumber) {
        Tile farmLot = farmLand.getTile(tileNumber);
        TileStatus status = farmLot.getStatus();
        String cropName = farmLot.getCrop().getName();
        String cropType = farmLot.getCrop().getType();
        int waterLevel = farmLot.getWaterLevel();
        int waterBL = farmLot.getCrop().getWaterBL() +
                type.getWaterBL();
        int fertilizerLevel = farmLot.getFertilizerLevel();
        int fertilizerBL = farmLot.getCrop().getFertilizerBL() +
                type.getFertilizerBL();
        int maxProduce = farmLot.getCrop().getMaxProduce();
        int minProduce = farmLot.getCrop().getMinProduce();
        int totalProduce;
        int sellPrice = farmLot.getCrop().getSellPrice();
        float harvestTotal;
        float waterBonus;
        float fertilizerBonus;
        float finalPrice;

        // reset success tracker
        farmLand.setHarvestSuccess(false);

        // if tile is harvestable, harvest plant
        if (status == TileStatus.HARVESTABLE) {
            // cap water and fertilizer levels based on crop's water and fertilizer BL
            if (waterLevel > waterBL) {
                waterLevel = waterBL;
            }
            if (fertilizerLevel > fertilizerBL) {
                fertilizerLevel = fertilizerBL;
            }

            // using RNG, calculate the amount of produce harvested
            totalProduce = (int) (Math.random() *
                    (maxProduce - minProduce + 1) + minProduce);

            harvestTotal = totalProduce * (sellPrice + type.getBonusEarn());
            waterBonus = (float) (0.2 * (waterLevel - 1));
            fertilizerBonus = harvestTotal * (float) (0.5 * fertilizerLevel);
            finalPrice = harvestTotal + waterBonus + fertilizerBonus;

            if (Objects.equals(cropType, "Flower")) {
                finalPrice *= 1.1f;
            }
            money += finalPrice;
            exp += farmLot.getCrop().getExpGain();

            System.out.println("You harvested " +totalProduce+ " "+ cropName + " and sold it for " + finalPrice + " Objectcoins.");
            farmLot.setStatus(TileStatus.UNPLOWED);
            farmLand.setHarvestSuccess(true);
            farmLand.setHarvestGain(finalPrice);
        }
        levelUp();
        return farmLand;
    }

    // getters

    /**
     *
     * @return experience of player
     */
    public float getExp() {
        return exp;
    }

    /**
     *
     * @return money
     */
    public float getMoney() {
        return money;
    }

    /**
     *
     * @param money sets money of user
     */
    public boolean checkMoney(float money){
        float temporary=this.money-money;
        if(temporary<0){
            return false;
        }
        else{
            return true;
        }
    }
    

    /**
     *
     * @return player's level
     */
    public int getLevel() {
        return level;
    }
}
