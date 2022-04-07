package com.codecool.players;

import com.codecool.game.Board;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipType;
import com.codecool.utils.ConsoleColor;

public class HumanPlayer extends Player{
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String attack(Board board) {
        return input.getAttackCoordinate(board.getBoardLength());
    }

    @Override
    public Ship placeShipManually(Board board, ShipType sType) {
        display.printMessage(board.toString());
        boolean placementOk = false;
        String coordinate = null;
        String direction = null;


        while (!placementOk){
            display.askForCoordinate(sType.toString(),sType.getLength());
            coordinate = input.getShipCoordinate(board.getBoardLength());
            if(sType.getLength() == 1)
                direction = "c";
            else
                direction = input.getDirection();
            placementOk = board.isPlacementOk(coordinate, sType.getLength(), direction);
        if(!placementOk)
                display.printMessage(String.format("%sYou can't place with chosen coordinates and direction%s", ConsoleColor.ANSI_RED,ConsoleColor.ANSI_RESET));

        }
        Ship ship =  new Ship(sType);
        ship.placeShip(board,direction,coordinate);
        return ship;
    }
}
