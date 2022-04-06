package com.codecool.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private final Scanner in = new Scanner(System.in);
    private final List<String> directions = new ArrayList<>();
    private final Display display = new Display();


    public Input() {
        directions.add("N");
        directions.add("W");
        directions.add("E");
        directions.add("S");
        directions.add("D");
        directions.add("U");
        directions.add("L");
        directions.add("R");
        directions.add("DOWN");
        directions.add("UP");
        directions.add("LEFT");
        directions.add("RIGHT");
    }

    public String getCoordinate(int bounds){
        String coordinate = in.nextLine().toUpperCase();
        try {
            Integer.parseInt(coordinate.substring(1));
        }catch (Exception e){
            Utils.useDisplayPrintError("Not a valid coordinate!");
            Utils.useDisplayPrintMessage("Please provide the coordinate where do you want to place the ship: ");
            return getCoordinate(bounds);
        }
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        if(coordinates[0] < bounds && coordinates[1] < bounds && coordinates[0] >= 0 && coordinates[1] >= 0)
            return coordinate;
        else {
            Utils.useDisplayPrintError("Out of bounds coordinate!");
            return getCoordinate(bounds);
        }
    }

    public int getInt(){
        try{
            String input = in.nextLine();
            return Integer.parseInt(input);
        }catch (Exception e){
            display.printNotANumber();
            return getInt();
        }
    }


    public String getName(){
        return in.nextLine();
    }


    public int getBoardSize(){
        display.askForBoardSize();
        try{
            String input = in.nextLine();
            return Integer.parseInt(input);
        }catch (Exception e){
            Utils.useDisplayPrintMessage("You should provide a number!");
            return getBoardSize();
        }
    }

    public String getDirection() {
        display.askForDirection();
        String direction = in.nextLine();
        if(directions.contains(direction.toUpperCase())){
            return direction;
        }
        return getDirection();
    }

    public int getShipNumber() {
        display.askForShipNumber();
        return getInt();
    }
}
