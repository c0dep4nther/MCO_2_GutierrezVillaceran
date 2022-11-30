package com.myfarm.mco_2_gutierrezvillaceran.board;

import myfarm.TileStatus;

import java.util.HashMap;

public class Board {

//    use hashmap to store integer as key and then tile as value
    private final HashMap<Integer, Tile> tiles = new HashMap<>();

//    board constructor
    public Board() {
        for (int i = 1; i <= 50; i++) {
            tiles.put(i, new Tile());
        }
    }

    //method to access the tile value
    public Tile getTile(int tileNumber) {
        return tiles.get(tileNumber);
    }

    public TileStatus getTileStatus(int tileNumber) {
        return tiles.get(tileNumber).getStatus();
    }

    public void displayBoard(){
        for (int i = 1; i <= 50; i++) {
            if(getTileStatus(i)==TileStatus.PLANTED){
                System.out.print(i + "\t[" + getTile(i).getCropName().getName() + "]\t");
            }
            else{
                System.out.print(i + "\t[" + getTileStatus(i) + "]\t");
            }

            if (i % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
