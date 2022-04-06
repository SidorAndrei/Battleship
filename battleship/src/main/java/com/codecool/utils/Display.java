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
            .append("What's your choice? ");
        System.out.print(menu);
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
        System.out.print("What's your name? ");
    }


    public void askForShipNumber(){
        System.out.print("How many ships? ");
    }


    public void askForBoardSize(){
        System.out.print("Choose bord size (5-20): ");
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

    public void askForCoordinate(String ship, int length){
        System.out.printf("Please provide the coordinate where do you want to place the ship (type : %s, length: %s): ", ship,length);
    }

    public void askForDirection() {
        System.out.printf("Which direction do you choose? (Must be coordinate or direction): ");
    }

    public void printBeforeInput(String message){
        System.out.print(message);
    }
}
