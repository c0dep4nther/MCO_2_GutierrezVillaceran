package com.myfarm.mco_2_gutierrezvillaceran;

import com.myfarm.mco_2_gutierrezvillaceran.board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {
    private Board gameBoard = new Board();
    private FarmerDetails farmerDetails = new FarmerDetails();

    private static final List<Plants> seedList = new ArrayList<>(Arrays.asList(
            new Plants("Turnip", "Root crop", 2,1,
                    0,1,2,5,
                    6,5f),
            new Plants("Carrot","Root crop",3,1,
                    0,1,2,10,
                    9,7.5f),
            new Plants("Rose","Flower",1,1,
                    0,1,1,5,
                    5,2.5f),
            new Plants("Tulips","Flower",2,2,
                    0,1,1,10,
                    9,5)
    ));
}
