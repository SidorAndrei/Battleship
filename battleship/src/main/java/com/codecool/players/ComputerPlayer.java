package com.codecool.players;

import com.codecool.game.Board;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipType;
import com.codecool.utils.Utils;

public class ComputerPlayer extends Player{

    public ComputerPlayer() {
        super(Utils.getRandomName());
    }

    @Override
    public String attack(Board board) {
        String coordinate = Utils.generateRandomCoordinate(board.getBoardLength());
        while(board.checkCoordinateToAttack(coordinate))
            coordinate = Utils.generateRandomCoordinate(board.getBoardLength());
        return coordinate;
    }

    @Override
    public Ship placeShipManually(Board board, ShipType sType) {
        return placeShipRandomly(board, sType);
    }
}
