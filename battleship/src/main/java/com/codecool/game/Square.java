package com.codecool.game;

public class Square{
    private SquareStatus status;
    public Square() {
        this.status = SquareStatus.EMPTY;
    }

    public void sunk(){
        this.status = SquareStatus.SUNK;
    }

    public boolean attack(){
        switch (status){
            case BLOCKED:
            case EMPTY:
                status = SquareStatus.MISSED;
                return false;
            case SHIP:
                status = SquareStatus.HIT;
                return true;
            default:
                return false;

        }
    }

    public void placeShip(){
        this.status = SquareStatus.SHIP;
    }

    public SquareStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status.getCharacter();
    }
}
