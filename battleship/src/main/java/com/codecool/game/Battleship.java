package com.codecool.game;

import com.codecool.ships.ShipType;
import com.codecool.utils.Display;
import com.codecool.utils.Input;

public class Battleship {

    private final Display display = new Display();
    private final Input input = new Input();
    private final Board[] board = new Board[2];
    private ShipType[] gameShips;
    private final int boardSize;


    public Battleship() {
        // ask for board size
        this.boardSize = getBoardSize();

        // initialize board0 & board1
        board[0] = new Board(boardSize);
        board[1] = new Board(boardSize);

        printBoards(1);

        // ask for ships number
        gameShips = new ShipType[getNumberOfShips(boardSize)];

        // ask for ships types
        initShipTypes();

        // place player0 & player1 ships

        // start attacking

    }

    public void start(){

    }

    // PRIVATE METHODS
    private int getNumberOfShips(int boardLength){
        try{
            display.askForShipNumber();
            int numberShips = input.getInt();
            if (numberShips > (boardLength / 2) + 1) {
                display.printTooManyShips();
                return getNumberOfShips(boardLength);
            }
            return numberShips;
        }catch (Exception e){
            display.printNotANumber();
            return getNumberOfShips(boardLength);
        }
    }

    private int getBoardSize(){
        try{
            display.ashForBoardSize();
            int boardSize = input.getBoardSize();
            if (boardSize > 20 || boardSize < 5) {
                display.printInvalidBoardSize();
                return getBoardSize();
            }
            return boardSize;
        }catch (Exception e){
            display.printNotANumber();
            return getBoardSize();
        }

    }

    private void initShipTypes() {
        for(int i=0; i<gameShips.length; i++){
            display.askForShipType(i+1);
            ShipType shipType = getShipType();
            gameShips[i] = shipType;
        }
    }

    private ShipType getShipType(){
        switch (input.getInt()){
            case 1:
                return ShipType.CARRIER;
            case 2:
                return ShipType.CRUISER;
            case 3:
                return ShipType.BATTLESHIP;
            case 4:
                return ShipType.SUBMARINE;
            case 5:
                return ShipType.DESTROYER;
            default:
                display.printMessage("Not a valid option! Try again!");
                return getShipType();
        }
    }

    private void printBoards(int playerTurn ){
        String firstLine = board[0].getFirstLine();
        StringBuilder sb = new StringBuilder("  ")
                .append(firstLine)
                .append("\t\t  ")
                .append(firstLine)
                .append("\n");
        if(playerTurn == 1) {
            for (int i = 0; i < boardSize; i++) {
                sb.append(board[0].getAllyRow(i))
                        .append("\t\t")
                        .append(board[1].getEnemyRow(i))
                        .append("\n");

            }
        }
        else{
            for (int i = 0; i < boardSize; i++) {
                sb.append(String.format("%s ", (char) (65 + i)))
                        .append(board[0].getEnemyRow(i))
                        .append("\t\t")
                        .append(String.format("%s ", (char) (65 + i)))
                        .append(board[1].getAllyRow(i))
                        .append("\n");

            }
        }
        display.printMessage(sb.toString());
    }

}
