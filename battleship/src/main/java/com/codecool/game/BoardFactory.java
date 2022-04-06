package com.codecool.game;

import com.codecool.ships.ShipType;
import com.codecool.utils.Display;
import com.codecool.utils.Input;

public class BoardFactory{
    private static final Display display = new Display();
    private static final Input input = new Input();
    public static void randomPlacement(){}

    public static void manualPlacement(ShipType[] gameShips, Board board){
        boolean placementOk;
        for(ShipType sType: gameShips){
            display.printMessage(board.toString());
            placementOk = false;
            String coordinate = null;
            String direction = null;
            while (!placementOk){
                display.askForCoordinate(sType.toString(),sType.getLength());
                coordinate = input.getCoordinate(board.getBoardLength());
                if(sType.getLength() == 1)
                    direction = "c";
                else
                    direction = input.getDirection();
                placementOk = board.isPlacementOk(coordinate, sType.getLength(), direction);
                if(!placementOk)
                    display.printMessage("You can't place with chosen coordinates and direction");
            }
            board.placeShipOnBoard(coordinate, sType.getLength(), direction);
        }
        display.printMessage(board.toString());
    }
}
