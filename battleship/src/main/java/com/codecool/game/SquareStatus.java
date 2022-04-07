package com.codecool.game;

import com.codecool.utils.ConsoleColor;

public enum SquareStatus {
    EMPTY, // no ship placed
    MISSED, // empty attacked
    BLOCKED, // ship can't be placed in this square
    SHIP, // ship placed
    HIT, // ship attacked
    SUNK; // ship destroyed (all ship squares attacked)

    public String getCharacter(){
        StringBuilder s = new StringBuilder();
        switch (this){
            case HIT:
                s.append(ConsoleColor.ANSI_YELLOW_BACKGROUND)
                .append(" \uD83D\uDCA5 ")
                .append(ConsoleColor.ANSI_RESET);
                break;
            case SHIP:
                s.append(ConsoleColor.ANSI_BLUE_BACKGROUND)
                .append(" \uD83D\uDEA2 ")
                .append(ConsoleColor.ANSI_RESET);
                break;
            case SUNK:
                s.append(ConsoleColor.ANSI_RED_BACKGROUND)
                .append(" \uD83D\uDC80 ")
                .append(ConsoleColor.ANSI_RESET);
                break;
            case MISSED:
                s.append(ConsoleColor.ANSI_GREEN_BACKGROUND)
                .append(" \uD83C\uDF0A ")
                .append(ConsoleColor.ANSI_RESET);
                break;
            case BLOCKED:
            case EMPTY:
                s.append(ConsoleColor.ANSI_WHITE_BACKGROUND)
                .append(" \uD83C\uDF0A ")
                .append(ConsoleColor.ANSI_RESET);
                break;
        }
        return s.toString();
    }
}
