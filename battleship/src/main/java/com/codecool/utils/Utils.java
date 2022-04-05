package com.codecool.utils;

public class Utils {
    public static int[] transformInCoordinate(String stringCoordinate){
        return new int[]{
                (int)(stringCoordinate.charAt(0))-64,
                Integer.parseInt(stringCoordinate.substring(1))-1
        };
    }
}
