package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.board.Board;
import com.myfarm.mco_2_gutierrezvillaceran.board.Tile;

import java.util.Objects;
import java.util.Scanner;

public class Farmer {
    private float exp = 0;
    private float totalExp;
    private float money = 100;
    private int level = 1;
    private FarmerType type = FarmerType.FARMER;

    /**
     * increase level of farmer
     */
    private void levelUp() {
        // add for total exp
        totalExp += exp;

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

        farmLot = farmLand.getTile(tileNumber);
        item.toolAction(farmLot);
        exp += item.getExpGain();

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
    public Board plantSeed(Plant seed,  Board farmLand, int tileNumber) {
        int dayCount = farmLand.getDayCount();
        int harvestTime = seed.getHarvestTime();
        Tile farmLot = farmLand.getTile(tileNumber);

        // TODO: adjust for planting trees

        // if tile is plowed and empty, plant seed
        if (farmLot.getStatus() == TileStatus.PLOWED) {
            farmLot.setCrop(seed);
            farmLot.setStatus(TileStatus.PLANTED);
            farmLot.setHarvestDate(dayCount, harvestTime);
            farmLand.setPlantSuccess(true);
            money -= seed.getSeedCost();
            farmLand.getTile(tileNumber).setCrop(seed);
            System.out.println("You planted " + seed.getName() + " which costs " + seed.getSeedCost() + " Objectcoins.");
            System.out.println("Harvest is in " + harvestTime + " days.");
        } else {
            farmLand.setPlantSuccess(false);
            System.out.println("You can't plant a seed on a tile that is occupied or isn't plowed.");
        }

        return farmLand;
    }

    /**
     *
     * @param farmLand board
     * @return updated board
     */
    public Board harvestPlant(Board farmLand) {
        Scanner input = new Scanner(System.in);
        int tileNumber = input.nextInt();
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
        } else {
            System.out.println("You can't harvest a plant that isn't planted or isn't ready to be harvested.");
        }
        levelUp();

        System.out.println("Press enter to continue...");
        input.nextLine();
        input.nextLine();

        return farmLand;
    }

    // getters

    /**
     *
     * @return experience of player
     */
    public float getTotalExp() {
        return totalExp;
    }

    /**
     *
     * @return objectCoins
     */
    public float getMoney() {
        return money;
    }

    /**
     *
     * @return player's level
     */
    public int getLevel() {
        return level;
    }
}
