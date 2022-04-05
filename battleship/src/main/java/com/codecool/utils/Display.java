package com.codecool.utils;

public class Display {


    public Display() {
    }

    public void printMenu(){
        StringBuilder menu = new StringBuilder();
        menu.append("Welcome to LA's Battleship!\n")
            .append("1-Start\n")
            .append("2-Display high scores\n")
            .append("0-Exit\n")
            .append("What's your choice?");
        System.out.println(menu);
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printTurn(String playerName){
        System.out.printf("It's %s turn!", playerName);
    }

    public void printWinner(String playerName){
        System.out.printf("%s has won! Congrulations!", playerName);
    }

    public void askForName(){
        System.out.println("What's your name?");
    }


    public void askForShipNumber(){
        System.out.println("How many ships?");
    }


    public void ashForBoardSize(){
        System.out.println("Choose bord size (5-20):");
    }

    public void printTooManyShips() {
        System.out.println("There are too many ships for this size of board!");
    }

    public void printInvalidBoardSize(){
        System.out.println("You must choose a size between 5 and 20!!");
    }

    public void printNotANumber() {
        System.out.println("You should provide a number!");
    }


    public void askForShipType(int shipNumber){
        StringBuilder sb = new StringBuilder(String.format("Choose type for ship number %s:\n",shipNumber))
                .append("1-Carrier\n")
                .append("2-Cruiser\n")
                .append("3-Battleship\n")
                .append("4-Submarine\n")
                .append("5-Destroyer\n");
        System.out.println(sb);
    }
}
