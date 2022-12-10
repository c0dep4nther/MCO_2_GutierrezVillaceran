package com.myfarm.mco_2_gutierrezvillaceran.model.board;

import com.myfarm.mco_2_gutierrezvillaceran.model.TileStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Board {
    // use hashmap to store integer as key and then tile as value
    private final HashMap<Integer, Tile> tiles = new HashMap<>();
    private boolean plantSuccess;
    private boolean harvestSuccess;
    private float harvestGain;
    private int dayCount = 1;

    /**
     * constructs board
     */
    public Board() {
        File rockLocations = new File("src/main/resources/com" +
                "/myfarm/mco_2_gutierrezvillaceran" +
                "/rock.txt");
        Scanner rockScanner;
        int rockTotal;
        int count;

        for (count = 1; count <= 50; count++) {
            if (count < 6 || count > 45 || count % 5 == 0 || count % 5 == 1) {
                tiles.put(count, new Tile(true));
            } else {
                tiles.put(count, new Tile(false));
            }
        }

        try {
            rockScanner = new Scanner(rockLocations);

            // read 2nd line then randomize rock placement
            rockScanner.nextLine();
            rockTotal = rockScanner.nextInt();

            // loop through rockTotal times
            // then randomize rock placement
            for (count = 0; count < rockTotal; count++) {
                // generate random number between 1 and 50
                int rockLocation = (int) (Math.random() * 50) + 1;

                tiles.get(rockLocation).setStatus(TileStatus.ROCK);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * checks if plant has withered or can be harvested
     */
    public void endDay() {
        boolean wither = false;
        int fertilizerNeed;
        int fertilizerLevel;
        int harvestDate;
        int waterNeed;
        int waterLevel;
        int witherCount = 0;
        TileStatus cropStatus;
        Tile farmLot;

        // increment day
        dayCount++;

        // for each tile, check if it's planted and if harvest date is reached
        for (int i = 1; i <= 50; i++) {
            farmLot = tiles.get(i);
            if (farmLot.getCrop() != null) {
                waterLevel = farmLot.getWaterLevel();
                fertilizerLevel = farmLot.getFertilizerLevel();
                waterNeed = farmLot.getCrop().getWaterNeed();
                fertilizerNeed = farmLot.getCrop().getFertilizerNeed();
                cropStatus = farmLot.getStatus();
                harvestDate = farmLot.getHarvestDate();

                // if crop is still not harvested, it withers next day
                if (cropStatus == TileStatus.HARVESTABLE) {
                    farmLot.setStatus(TileStatus.WITHERED);
                    wither = true;
                    witherCount++;
                }

                // if crop's needs are met before harvest day, crop is harvestable
                if (cropStatus == TileStatus.PLANTED && harvestDate == dayCount) {
                    // check if water and fertilizer needed is met
                    if (waterLevel >= waterNeed && fertilizerLevel >= fertilizerNeed) {
                        farmLot.setStatus(TileStatus.HARVESTABLE);
                    } else {
                        wither = true;
                        farmLot.setStatus(TileStatus.WITHERED);
                        witherCount++;
                    }
                }
            }
        }

        if (wither) {
            if (witherCount == 1) {
                System.out.println("a crop has withered.");
            } else {
                System.out.println(witherCount + " crops have withered.");
            }
        }
    }

    /**
     * @param tileNumber number of tile
     * @return access the tile value
     */
    public Tile getTile(int tileNumber) {
        // return null if out of bounds
        if (tileNumber > 50 || tileNumber < 1) {
            return null;
        }

        return tiles.get(tileNumber);
    }

    /**
     *
     * @param tileNumber number of tile
     * @return access tile status
     */
    public TileStatus getTileStatus(int tileNumber) {
        return tiles.get(tileNumber).getStatus();
    }

    public int countWither() {
        int count=0;
        for (int i = 1; i <= 50; i++) {
            if (getTileStatus(i) == TileStatus.WITHERED) {
                count++;
            }
        }
        return count;
    }

    // getters
    /**
     * @return day number/count
     */
    public int getDayCount() {
        return dayCount;
    }

    public boolean getPlantSuccess() {
        return plantSuccess;
    }

    public boolean getHarvestSuccess() {
        return harvestSuccess;
    }

    public float getHarvestGain() {
        return harvestGain;
    }

    // setters
    public void setPlantSuccess(boolean plantSuccess) {
        this.plantSuccess = plantSuccess;
    }

    public void setHarvestSuccess(boolean harvestSuccess) {
        this.harvestSuccess = harvestSuccess;
    }

    public void setHarvestGain(float harvestGain) {
        this.harvestGain = harvestGain;
    }
}