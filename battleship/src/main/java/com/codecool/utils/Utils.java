package com.codecool.utils;

public class Utils {
    public static int[] transformInCoordinate(String stringCoordinate){
        return new int[]{
                (int)(stringCoordinate.charAt(0))-65,
                Integer.parseInt(stringCoordinate.substring(1))-1
        };
    }

    public static void useDisplayPrintError(String message){
        Display d = new Display();
        d.printMessage(message);
    }

    public static void useDisplayPrintMessage(String message){
        Display d = new Display();
        d.printBeforeInput(message);
    }
}
