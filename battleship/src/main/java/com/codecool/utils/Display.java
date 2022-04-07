package com.codecool.utils;

import java.awt.*;

public class Display {


    public Display() {
    }

    public void printMenu(){
        StringBuilder menu = new StringBuilder(ConsoleColor.ANSI_CYAN);
        menu.append("Welcome to LA's Battleship!\n")
            .append("1-Start\n")
            .append("2-Display high scores\n")
            .append("0-Exit\n")
            .append("What's your choice? ").append(ConsoleColor.ANSI_RESET);
        System.out.print(menu);
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printTurn(String playerName){
        System.out.printf("%sIt's %s's turn!\n%s",ConsoleColor.ANSI_YELLOW, playerName, ConsoleColor.ANSI_RESET);
    }

    public void printWinner(String playerName){
        System.out.printf("%s%s has won! Congrulations!%s", ConsoleColor.ANSI_YELLOW, playerName, ConsoleColor.ANSI_RESET);
    }

    public void askForName(){
        System.out.printf("%sWhat's your name? %s", ConsoleColor.ANSI_GREEN, ConsoleColor.ANSI_RESET);
    }


    public void askForShipNumber(){
        System.out.printf("%sHow many ships? %s",ConsoleColor.ANSI_GREEN, ConsoleColor.ANSI_RESET);
    }


    public void askForBoardSize(){
        System.out.printf("%sChoose bord size (5-20): %s", ConsoleColor.ANSI_BLUE, ConsoleColor.ANSI_RESET);
    }

    public void printTooManyShips() {
        System.out.printf("%sThere are too many ships for this size of board!\n%s", ConsoleColor.ANSI_RED, ConsoleColor.ANSI_RESET);
    }

    public void printInvalidBoardSize(){
        System.out.printf("%sYou must choose a size between 5 and 20!!\n%s", ConsoleColor.ANSI_RED, ConsoleColor.ANSI_RESET);
    }

    public void printNotANumber() {
        System.out.printf("%sYou should provide a number!\n%s",ConsoleColor.ANSI_RED, ConsoleColor.ANSI_RESET);
    }


    public void askForShipType(int shipNumber){
        StringBuilder sb = new StringBuilder(String.format("%sChoose type for ship number %s:\n", ConsoleColor.ANSI_CYAN,shipNumber))
                .append("1-Carrier\n")
                .append("2-Cruiser\n")
                .append("3-Battleship\n")
                .append("4-Submarine\n")
                .append("5-Destroyer\n").append(ConsoleColor.ANSI_RESET);
        System.out.println(sb);
    }

    public void askForCoordinate(String ship, int length){
        System.out.printf("%sPlease provide the coordinate where do you want to place the ship (type : %s, length: %s): %s",ConsoleColor.ANSI_BLUE, ship,length,ConsoleColor.ANSI_RESET);
    }

    public void askForDirection() {
        System.out.printf("%sWhich direction do you choose? (Must be coordinate or direction): %s", ConsoleColor.ANSI_RED, ConsoleColor.ANSI_RESET);
    }

    public void printBeforeInput(String message){
        System.out.print(message);
    }
}
