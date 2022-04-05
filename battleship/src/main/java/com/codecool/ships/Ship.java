package com.codecool.ships;

import com.codecool.game.Board;
import com.codecool.game.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private ShipType shipType;
    private final List<Square> squares= new ArrayList<Square>();
    private int length;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
         switch (shipType){
             case CARRIER:
                 this.length = 1;
                 break;
             case CRUISER:
                 this.length = 2;
                 break;
             case BATTLESHIP:
                 this.length = 3;
                 break;
             case SUBMARINE:
                 this.length = 4;
                 break;
             case DESTROYER:
                 this.length = 5;
                 break;
         }
    }

    public void placeShip(int bounds, Board board){
        // place ship in direction

    }
}
