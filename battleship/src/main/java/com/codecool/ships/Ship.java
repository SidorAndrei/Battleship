package com.codecool.ships;

import com.codecool.game.Board;
import com.codecool.game.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private ShipType shipType;
    private final List<Square> squares= new ArrayList<Square>();

    public Ship(ShipType shipType) {
        this.shipType = shipType;

    }

    public void placeShip(int bounds, Board board){
        // place ship in direction
    }

}
