package com.codecool.players;

import com.codecool.game.Board;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipType;
import com.codecool.utils.ConsoleColor;
import com.codecool.utils.Utils;

public class ImpossibleComputerPlayer extends Player{
    public ImpossibleComputerPlayer() {
        super(Utils.getRandomName());
    }

    @Override
    public Ship placeShipManually(Board board, ShipType sType) {
        return placeShipRandomly(board, sType);
    }

    @Override
    public String attack(Board board) {
        display.printMessage(String.format("%s%s is thinking which ship to attack!%s", ConsoleColor.ANSI_GREEN, name,ConsoleColor.ANSI_RESET));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            display.printMessage(String.format("%s%s has been interrupted!%s",ConsoleColor.ANSI_RED,name,ConsoleColor.ANSI_RESET));
        }
        String coordinate = Utils.generateRandomCoordinate(board.getBoardLength());
        while(board.checkCoordinateToHit(coordinate))
            coordinate = Utils.generateRandomCoordinate(board.getBoardLength());
        return coordinate;
    }
}
