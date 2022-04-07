package com.codecool.game;

import com.codecool.players.Player;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipType;
import com.codecool.utils.Display;
import com.codecool.utils.Input;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory{
    private static final Display display = new Display();
    private static final Input input = new Input();
    public static List<Ship> randomPlacement(List<ShipType> gameShips, Board board, Player player){
        List<Ship> ships = new ArrayList<>();

        int counter = 0;
        Ship ship = null;
        while (ship == null) {
            if (counter == 50) return null;
            for (ShipType sType : gameShips) {
                ship = player.placeShipRandomly(board, sType);
                if (ship == null) break;
                ships.add(ship);
            }
//            System.out.println("boardfactory counter ++");
            counter++;
        }
        return ships;
    }

    public static List<Ship> manualPlacement(List<ShipType> gameShips, Board board, Player player){
        List<Ship> ships = new ArrayList<>();
        for(ShipType sType: gameShips){
            ships.add(player.placeShipManually(board,sType));
        }
        return ships;
    }
}
