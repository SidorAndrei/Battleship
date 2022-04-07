package com.codecool.game;

import com.codecool.players.ComputerPlayer;
import com.codecool.players.HumanPlayer;
import com.codecool.players.Player;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipType;
import com.codecool.utils.ConsoleColor;
import com.codecool.utils.Display;
import com.codecool.utils.Input;

import java.util.*;
import java.util.stream.Collectors;

public class Battleship {

    private final Display display = new Display();
    private final Input input = new Input();
    private final Board[] board = new Board[2];
    private List<ShipType> gameShips;
    private final Player[] players = new Player[2];
    private final int boardSize;
    private int shipNumber;


    public Battleship() {
        // ask for board size
        this.boardSize = getBoardSize();

       // initialize board0 & board1
        board[0] = new Board(boardSize);
        board[1] = new Board(boardSize);



    }

    public void start(){
        //
        switch (input.getMenuChoice()){
            case 1:
                switch (input.getGameMode()){
                    case 1:
                        players[0] = new HumanPlayer(input.getName());
                        players[1] = new HumanPlayer(input.getName());
                        break;
                    case 2:
                        players[0] = new HumanPlayer(input.getName());
                        players[1] = new ComputerPlayer();
                        break;
                    case 3:
                        players[0] = new ComputerPlayer();
                        players[1] = new HumanPlayer(input.getName());
                        break;
                    case 4:
                        players[0] = new ComputerPlayer();
                        players[1] = new ComputerPlayer();
                        break;
                    case 0:
                        System.exit(0);
                }
                break;
            case 0:  System.exit(0);
        }

        printBoards(1);

        // ask for ships number
        gameShips = new ArrayList<>();
        shipNumber= input.getShipNumber();

        // ask for ships types
        initShipTypes();

        // place player0 & player1 ships
//        placeShips();
        placeRandomShips();

        // start attacking
        int player = 0;
        int enemy = 1;
        boolean hasWin = false;
        while (!hasWin){
            display.printTurn(players[player].toString());
            printBoards(player);
            if(board[enemy].attack(players[player].attack(board[enemy])))
                players[enemy].checkShips();
            if (players[enemy].hasLost()) hasWin = true;
            else
                switch (player){
                    case 0:
                        player = 1;
                        enemy = 0;
                        break;
                    case 1:
                        player = 0;
                        enemy = 1;
                        break;
                }
        }
        printBoards(player);
        display.printWinner(players[player].toString());

    }

    // PRIVATE METHODS
    private void placeShips(){
        display.printMessage(String.format("%s is placing ships!",players[0]));
        players[0].setShipList(BoardFactory.manualPlacement(gameShips,board[0], players[0]));
        display.printMessage(String.format("%s is placing ships!",players[1]));
        players[1].setShipList(BoardFactory.manualPlacement(gameShips,board[1], players[1]));
    }

    private void placeRandomShips(){
        List<Ship> ships = null;
        while (ships == null) ships = BoardFactory.randomPlacement(gameShips,board[0], players[0]);
        players[0].setShipList(ships);
        System.out.println("Player 1 placed!");
        ships = null;
        while (ships == null) ships = BoardFactory.randomPlacement(gameShips,board[1], players[1]);
        players[1].setShipList(ships);
        System.out.println("Player 2 placed!");
    }

    private int getNumberOfShips(int boardLength){
        int numberShips = input.getShipNumber();
        if (numberShips > (boardLength / 2) + 1) {
            display.printTooManyShips();
            return getNumberOfShips(boardLength);
        }
        return numberShips;
    }

    private int getBoardSize()
    {
        int boardSize = input.getBoardSize();
        if (boardSize > 20 || boardSize < 5) {
            display.printInvalidBoardSize();
            return getBoardSize();
        }
        return boardSize;

    }

    private void initShipTypes() {
        for(int i=0; i<shipNumber; i++){
            display.askForShipType(i+1);
            ShipType shipType = getShipType();
            gameShips.add(shipType);
        }
//        gameShips = gameShips.parallelStream().sorted(Comparator.comparing(ShipType::getLength)).collect(Collectors.toList());
        Collections.reverse(gameShips);
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
                display.printMessage(String.format("%sNot a valid option! Try again!%s", ConsoleColor.ANSI_RED, ConsoleColor.ANSI_RESET));
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
        if(playerTurn == 0) {
            for (int i = 0; i < boardSize; i++) {
                sb.append(board[0].getAllyRow(i))
                        .append("\t\t")
                        .append(board[1].getEnemyRow(i))
                        .append("\n");

            }
        }
        else{
            for (int i = 0; i < boardSize; i++) {
                        sb.append(board[0].getEnemyRow(i))
                        .append("\t\t")
                        .append(board[1].getAllyRow(i))
                        .append("\n");

            }
        }
        display.printMessage(sb.toString());
    }


}
