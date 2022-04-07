package com.codecool.players;

import com.codecool.game.Board;
import com.codecool.game.SquareStatus;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipStatus;
import com.codecool.ships.ShipType;
import com.codecool.utils.ConsoleColor;
import com.codecool.utils.Display;
import com.codecool.utils.Input;
import com.codecool.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected final Display display = new Display();
    protected final Input input = new Input();
    private List<Ship> shipList = new ArrayList<>();
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract Ship placeShipManually(Board board, ShipType sType);

    public Ship placeShipRandomly(Board board, ShipType sType){
        boolean placementOk = false;
        String coordinate = null;
        String direction = null;
        int counter = 0;
        while (!placementOk){
            if(counter == 30) return null;
            coordinate = Utils.generateRandomCoordinate(board.getBoardLength());
            if(sType.getLength() == 1)
                direction = "c";
            else
                direction = Utils.generateRandomDirection();
            placementOk = board.isPlacementOk(coordinate, sType.getLength(), direction);
            if(!placementOk){
                int counterDirection = 0;
                while (counterDirection < 10) {
                    direction = Utils.generateRandomDirection();
                    placementOk = board.isPlacementOk(coordinate, sType.getLength(), direction);
                    counterDirection++;
                }
            }
            counter++;
        }
        Ship ship =  new Ship(sType);
        ship.placeShip(board,direction,coordinate);
        return ship;
    }

    public abstract String attack(Board board);

    public void setShipList(List<Ship> shipList) {
        this.shipList = shipList;
    }

    public void checkShips(){
        shipList.forEach(Ship::checkForSunk);
    }

    public boolean hasLost() {
        return shipList.stream().map(Ship::getStatus).noneMatch(s-> s == ShipStatus.ALIVE);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
