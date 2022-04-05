package com.codecool.utils;

import java.util.Scanner;

public class Input {
    private final Scanner in = new Scanner(System.in);

    public Input() {
    }

    public String getCoordinate(int bounds){
        return in.nextLine();
    }

    public int getInt(){
        return in.nextInt();
    }

    public String getName(){
        return in.nextLine();
    }


    public int getBoardSize(){
        return in.nextInt();
    }
}
