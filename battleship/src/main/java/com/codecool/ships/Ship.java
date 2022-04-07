package com.codecool.ships;

import com.codecool.game.Board;
import com.codecool.game.Square;
import com.codecool.game.SquareStatus;
import com.codecool.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final ShipType shipType;
    private final List<Square> squares= new ArrayList<Square>();
    private ShipStatus status = ShipStatus.ALIVE;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
    }

    public void placeShip(Board board,String direction, String coordinate){
        // place ship in direction
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        direction = direction.toUpperCase();
        switch (direction){
            case "N":
            case "UP":
            case "U":
                if(coordinates[0]+1 < board.getOcean().length){
                    board.getOcean()[coordinates[0]+1][coordinates[1]].blockSquare();
                }
                for (int i=0;i< shipType.getLength(); i++){
                    board.getOcean()[coordinates[0]-i][coordinates[1]].placeShip();
                    squares.add(board.getOcean()[coordinates[0]-i][coordinates[1]]);
                    if(coordinates[1]+1 < board.getOcean().length){
                        board.getOcean()[coordinates[0]-i][coordinates[1]+1].blockSquare();
                    }
                    if(coordinates[1]-1 >= 0){
                        board.getOcean()[coordinates[0]-i][coordinates[1]-1].blockSquare();
                    }
                }
                if(coordinates[0]- board.getBoardLength() >= 0){
                    board.getOcean()[coordinates[0]- board.getBoardLength()][coordinates[1]].blockSquare();
                }
                break;
            case "S":
            case"DOWN":
            case "D":
                if(coordinates[0]-1 > 0){
                    board.getOcean()[coordinates[0]-1][coordinates[1]].blockSquare();
                }
                for (int i=0;i< shipType.getLength(); i++){
                    board.getOcean()[coordinates[0]+i][coordinates[1]].placeShip();
                    squares.add(board.getOcean()[coordinates[0]+i][coordinates[1]]);
                    if(coordinates[1]+1 < board.getOcean().length){
                        board.getOcean()[coordinates[0]+i][coordinates[1]+1].blockSquare();
                    }
                    if(coordinates[1]-1 >= 0){
                        board.getOcean()[coordinates[0]+i][coordinates[1]-1].blockSquare();
                    }
                }
                if(coordinates[0] + shipType.getLength() < board.getOcean().length){
                    board.getOcean()[coordinates[0]+ shipType.getLength()][coordinates[1]].blockSquare();
                }
                break;
            case "W":
            case"LEFT":
            case "L":
                if(coordinates[1]+1 < board.getOcean().length){
                    board.getOcean()[coordinates[0]][coordinates[1]+1].blockSquare();
                }
                for (int i=0;i< shipType.getLength(); i++){
                    board.getOcean()[coordinates[0]][coordinates[1]-i].placeShip();
                    squares.add(board.getOcean()[coordinates[0]][coordinates[1]-i]);
                    if(coordinates[0]+1 < board.getOcean().length){
                        board.getOcean()[coordinates[0]+1][coordinates[1]-i].blockSquare();
                    }
                    if(coordinates[0]-1 >= 0){
                        board.getOcean()[coordinates[0]-1][coordinates[1]-i].blockSquare();
                    }
                }
                if(coordinates[1]- shipType.getLength() >= 0){
                    board.getOcean()[coordinates[0]][coordinates[1]- shipType.getLength()].blockSquare();
                }
                break;
            case "E":
            case "RIGHT":
            case "R":
                if(coordinates[1]-1 >= 0){
                    board.getOcean()[coordinates[0]][coordinates[1]-1].blockSquare();
                }
                for (int i=0;i< shipType.getLength(); i++){
                    board.getOcean()[coordinates[0]][coordinates[1]+i].placeShip();
                    squares.add(board.getOcean()[coordinates[0]][coordinates[1]+i]);
                    if(coordinates[0]+1 < board.getOcean().length){
                        board.getOcean()[coordinates[0]+1][coordinates[1]+i].blockSquare();
                    }
                    if(coordinates[0]-1 >= 0){
                        board.getOcean()[coordinates[0]-1][coordinates[1]+i].blockSquare();
                    }
                }
                if(coordinates[1]+ shipType.getLength() < board.getOcean().length){
                    board.getOcean()[coordinates[0]][coordinates[1]+ shipType.getLength()].blockSquare();
                }
                break;
            case "C":
                board.getOcean()[coordinates[0]][coordinates[1]].placeShip();
                squares.add(board.getOcean()[coordinates[0]][coordinates[1]]);
                if(coordinates[0]-1 >= 0){
                    board.getOcean()[coordinates[0]-1][coordinates[1]].blockSquare();
                }
                if(coordinates[0]+1 < board.getOcean().length){
                    board.getOcean()[coordinates[0]+1][coordinates[1]].blockSquare();
                }
                if(coordinates[1]-1 >= 0){
                    board.getOcean()[coordinates[0]][coordinates[1]-1].blockSquare();
                }
                if(coordinates[1]+1 < board.getOcean().length){
                    board.getOcean()[coordinates[0]][coordinates[1]+1].blockSquare();
                }

        }
    }

    public void checkForSunk(){
        if(squares.stream().noneMatch(s->s.getStatus() == SquareStatus.SHIP)){
            sunkShip();
        }
    }

    private void sunkShip(){
        squares.forEach(Square::sunkSquare);
        status = ShipStatus.SUNKED;
    }

    public ShipStatus getStatus() {
        return status;
    }
}
