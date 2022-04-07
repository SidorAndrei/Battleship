package com.codecool.game;

import com.codecool.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class Board {
    private final Square[][] ocean;
    private final int lengthBoard;

    public Board(int lengthBoard) {
        this.ocean = new Square[lengthBoard][lengthBoard];
        this.lengthBoard = lengthBoard;
        initBoard();
    }

    public void initBoard(){
        for(int i=0; i<lengthBoard;i++){
            for(int j=0; j<lengthBoard; j++){
                ocean[i][j] = new Square(i,j);
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
                if(coordinates[0] + length >= ocean.length){
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
        StringBuilder sb = new StringBuilder("");
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
        sb.append(" ");
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

    public boolean attack(String attackCoordinate) {
        int[] coordinate = Utils.transformInCoordinate(attackCoordinate);
        return ocean[coordinate[0]][coordinate[1]].attack();
    }

    private List<Square> toList(){
        List<Square> squares = new ArrayList<>();
        for(Square[] row: ocean){
            squares.addAll(Arrays.asList(row));
        }
        return squares;
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public boolean checkCoordinateToAttack(String coordinate){
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        return !(ocean[coordinates[0]][coordinates[1]].getStatus() != SquareStatus.HIT && ocean[coordinates[0]][coordinates[1]].getStatus() != SquareStatus.SUNK && ocean[coordinates[0]][coordinates[1]].getStatus() != SquareStatus.MISSED);
    }

    public boolean checkCoordinateToHit(String coordinate){
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        return (ocean[coordinates[0]][coordinates[1]].getStatus() != SquareStatus.SHIP);
    }

    // SHOULD BE MOVED TO PLAYER

}
