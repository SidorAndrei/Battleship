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

    public String getShipCoordinate(int bounds){
        String coordinate = in.nextLine().toUpperCase();
        try {
            Integer.parseInt(coordinate.substring(1));
        }catch (Exception e){
            display.printMessage("Not a valid coordinate!");
            display.printBeforeInput("Please provide the coordinate where do you want to place the ship: ");
            return getShipCoordinate(bounds);
        }
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        if(coordinates[0] < bounds && coordinates[1] < bounds && coordinates[0] >= 0 && coordinates[1] >= 0)
            return coordinate;
        else {
            Utils.useDisplayPrintError("Out of bounds coordinate!");
            return getShipCoordinate(bounds);
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

    public int getMenuChoice(){
        try {
            display.printMenu();
            int choice = Integer.parseInt(in.nextLine());
            if(choice != 1 && choice != 0) {
                display.printMessage(String.format("%sThat's not a choice!%s",ConsoleColor.ANSI_RED,ConsoleColor.ANSI_RESET));
                return getMenuChoice();
            }
            else
                return choice;
        }catch (Exception e){
            display.printNotANumber();
            return getMenuChoice();
        }
    }

    public int getGameMode(){
        try {
            display.printGameModes();
            int choice = Integer.parseInt(in.nextLine());
            if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 0) {
                display.printMessage(String.format("%sThat's not a choice!%s",ConsoleColor.ANSI_RED,ConsoleColor.ANSI_RESET));
                return getGameMode();
            }
            else
                return choice;
        }catch (Exception e){
            display.printNotANumber();
            return getGameMode();
        }
    }


    public String getName(){
        display.askForName();
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


    public String getAttackCoordinate(int bounds){
        display.printBeforeInput("Please provide the coordinate where do you want to attack: ");
        String coordinate = in.nextLine().toUpperCase();
        try {
            Integer.parseInt(coordinate.substring(1));
        }catch (Exception e){
            display.printMessage("Not a valid coordinate!");
            return getAttackCoordinate(bounds);
        }
        int[] coordinates = Utils.transformInCoordinate(coordinate);
        if(coordinates[0] < bounds && coordinates[1] < bounds && coordinates[0] >= 0 && coordinates[1] >= 0)
            return coordinate;
        else {
            display.printMessage("Out of bounds coordinate!");
            return getAttackCoordinate(bounds);
        }
    }
}
