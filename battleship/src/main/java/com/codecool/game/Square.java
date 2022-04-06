package com.codecool.game;

public class Square{
    private final int X;
    private final int Y;
    private SquareStatus status;
    public Square(int X, int Y) {
        this.status = SquareStatus.EMPTY;
        this.X = X;
        this.Y = Y;
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

    public void blockSquare(){
        this.status = SquareStatus.BLOCKED;
    }

    public SquareStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status.getCharacter();
    }
}
