package com.codecool.game;

import com.codecool.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class Board {
    private final Square[][] ocean;

    public Board(int lengthBoard) {
        this.ocean = new Square[lengthBoard][lengthBoard];
        for(int i=0; i<lengthBoard;i++){
            for(int j=0; j<lengthBoard; j++){
                ocean[i][j] = new Square(i,j);
            }
        }
    }

    public void placeShipOnBoard(String coordinate,int length, String direction){
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        direction = direction.toUpperCase();
        switch (direction){
            case "N":
            case "UP":
            case "U":
                if(coordinates[0]+1 < ocean.length){
                    ocean[coordinates[0]+1][coordinates[1]].blockSquare();
                }
                for (int i=0;i<length; i++){
                    ocean[coordinates[0]-i][coordinates[1]].placeShip();
                    if(coordinates[1]+1 < ocean.length){
                        ocean[coordinates[0]-i][coordinates[1]+1].blockSquare();
                    }
                    if(coordinates[1]-1 >= 0){
                        ocean[coordinates[0]-i][coordinates[1]-1].blockSquare();
                    }
                }
                if(coordinates[0]-length >= 0){
                    ocean[coordinates[0]-length][coordinates[1]].blockSquare();
                }
                break;
            case "S":
            case"DOWN":
            case "D":
                if(coordinates[0]-1 > 0){
                    ocean[coordinates[0]-1][coordinates[1]].blockSquare();
                }
                for (int i=0;i<length; i++){
                    ocean[coordinates[0]+i][coordinates[1]].placeShip();
                    if(coordinates[1]+1 < ocean.length){
                        ocean[coordinates[0]+i][coordinates[1]+1].blockSquare();
                    }
                    if(coordinates[1]-1 >= 0){
                        ocean[coordinates[0]+i][coordinates[1]-1].blockSquare();
                    }
                }
                if(coordinates[0]+length < ocean.length){
                    ocean[coordinates[0]+length][coordinates[1]].blockSquare();
                }
                break;
            case "W":
            case"LEFT":
            case "L":
                if(coordinates[1]+1 < ocean.length){
                    ocean[coordinates[0]][coordinates[1]+1].blockSquare();
                }
                for (int i=0;i<length; i++){
                    ocean[coordinates[0]][coordinates[1]-i].placeShip();
                    if(coordinates[0]+1 < ocean.length){
                        ocean[coordinates[0]+1][coordinates[1]-i].blockSquare();
                    }
                    if(coordinates[0]-1 >= 0){
                        ocean[coordinates[0]-1][coordinates[1]-i].blockSquare();
                    }
                }
                if(coordinates[1]-length >= 0){
                    ocean[coordinates[0]][coordinates[1]-length].blockSquare();
                }
                break;
            case "E":
            case "RIGHT":
            case "R":
                if(coordinates[1]-1 >= 0){
                    ocean[coordinates[0]][coordinates[1]-1].blockSquare();
                }
                for (int i=0;i<length; i++){
                    ocean[coordinates[0]][coordinates[1]+i].placeShip();
                    if(coordinates[0]+1 < ocean.length){
                        ocean[coordinates[0]+1][coordinates[1]+i].blockSquare();
                    }
                    if(coordinates[0]-1 >= 0){
                        ocean[coordinates[0]-1][coordinates[1]+i].blockSquare();
                    }
                }
                if(coordinates[1]+length < ocean.length){
                    ocean[coordinates[0]][coordinates[1]+length].blockSquare();
                }
                break;
            case "C":
                ocean[coordinates[0]][coordinates[1]].placeShip();
                if(coordinates[0]-1 >= 0){
                    ocean[coordinates[0]-1][coordinates[1]].blockSquare();
                }
                if(coordinates[0]+1 < ocean.length){
                    ocean[coordinates[0]+1][coordinates[1]].blockSquare();
                }
                if(coordinates[1]-1 >= 0){
                    ocean[coordinates[0]][coordinates[1]-1].blockSquare();
                }
                if(coordinates[1]+1 < ocean.length){
                    ocean[coordinates[0]][coordinates[1]+1].blockSquare();
                }

        }
    }

    public boolean isPlacementOk(String coordinate, int length, String direction){
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        direction = direction.toUpperCase();
        switch (direction){
            case "N":
            case "UP":
            case "U":
                if(coordinates[0] - length < 0){
                    return false;
                }
                else{
                    for (int i=0;i<length; i++){
                        if(ocean[coordinates[0]-i][coordinates[1]].getStatus()!=SquareStatus.EMPTY){
                            return false;
                        }
                    }
                    return true;
                }
            case "S":
            case"DOWN":
            case "D":
                if(coordinates[0] + length > ocean.length){
                    return false;
                }
                else{
                    for (int i=0;i<length; i++){
                        if(ocean[coordinates[0]+i][coordinates[1]].getStatus()!=SquareStatus.EMPTY){
                            return false;
                        }
                    }
                    return true;
                }
            case "W":
            case"LEFT":
            case "L":
                if(coordinates[1] - length < 0){
                    return false;
                }
                else{
                    for (int i=0;i<length; i++){
                        if(ocean[coordinates[0]][coordinates[1]-i].getStatus()!=SquareStatus.EMPTY){
                            return false;
                        }
                    }
                    return true;
                }
            case "E":
            case "RIGHT":
            case "R":
                if(coordinates[1] + length > ocean.length){
                    return false;
                }
                else{
                    for (int i=0;i<length; i++){
                        if(ocean[coordinates[0]][coordinates[1]+i].getStatus()!=SquareStatus.EMPTY){
                            return false;
                        }
                    }
                    return true;
                }
            case "C":
                return ocean[coordinates[0]][coordinates[1]].getStatus() == SquareStatus.EMPTY;
            default:
                return false;
        }
    }

    public void checkForSunk(String coordinate){

    }

    public String getAllyRow(int row){
        StringBuilder sb = new StringBuilder(String.format("%s ", (char)(65+row)));
        for(int i=0; i<ocean.length; i++){
            sb.append(ocean[row][i]);
        }
        return sb.toString();
    }

    public String getEnemyRow(int row){
        StringBuilder sb = new StringBuilder(String.format("%s ", (char)(65+row)));
        for(int i=0; i<ocean.length; i++){
            if(!ocean[row][i].getStatus().equals(SquareStatus.SHIP))
                sb.append(ocean[row][i]);
            else{
                sb.append(SquareStatus.EMPTY.getCharacter());
            }
        }
        return sb.toString();
    }

    public String getFirstLine(){
        StringBuilder sb = new StringBuilder();
        for(int i =1; i<=ocean.length; i++){
            if(i!= ocean.length) {
                if(i<10) {
                    sb.append(String.format(" %s ", i));
                    if ((i % 3 != 0 && i < 10)) {
                        sb.append(" ");
                    }
                }else{
                    sb.append(String.format(" %s", i));
                    if (i % 3 != 0 && i != 11) {
                        sb.append(" ");
                    }
                }
            }else{
                sb.append(String.format(" %s", i));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  ")
                .append(getFirstLine())
                .append("\n");
        for(int i=0; i<ocean.length; i++){
            sb.append(String.format("%s ", (char)(65+i)));
            for(int j=0; j<ocean.length; j++){
                sb.append(ocean[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getBoardLength(){
        return ocean.length;
    }

    public void attack(String attackCoordinate) {
        int[] coordinate = Utils.transformInCoordinate(attackCoordinate);
        if (ocean[coordinate[0]][coordinate[1]].attack()){
            checkForSunk(attackCoordinate);
        }
    }

    private List<Square> toList(){
        List<Square> squares = new ArrayList<>();
        for(Square[] row: ocean){
            for(Square square: row){
                squares.add(square);
            }
        }
        return squares;
    }

    public boolean hasLost(){
        return toList().stream().noneMatch(s -> s.getStatus()== SquareStatus.SHIP);
    }
}
