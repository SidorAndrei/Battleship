package com.codecool.ships;

public enum ShipType {
    CARRIER,
    CRUISER,
    BATTLESHIP,
    SUBMARINE,
    DESTROYER;

    public int getLength(){
        switch (this){
            case CARRIER:
                return 1;
            case CRUISER:
                return 2;
            case BATTLESHIP:
                return 3;
            case SUBMARINE:
                return 4;
            case DESTROYER:
                return 5;
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (this){
            case CARRIER:
                return "CARRIER";
            case CRUISER:
                return "CRUISER";
            case BATTLESHIP:
                return "BATTLESHIP";
            case SUBMARINE:
                return "SUBMARINE";
            case DESTROYER:
                return "DESTROYER";
        }
        return "";
    }
}
