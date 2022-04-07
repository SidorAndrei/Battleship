package com.codecool.utils;

import java.util.Random;

public class Utils {
    public static String generateRandomCoordinate(int bounds){
        Random random = new Random();
        int letter = (random.nextInt(bounds));
        int col = random.nextInt(bounds)+1;
        return String.format("%s%s",(char)(letter+65),col);
    }

    public static String generateRandomDirection(){
        Random random = new Random();
        String[] directions = new String[]{"W","S","E", "N"};
        return directions[random.nextInt(directions.length)];
    }

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

    public static String getRandomName(){
        Random random = new Random();
        String[] names = new String[] {
               "Nanny", "Edith", "Marianne", "Tommie", "Baard", "Sharleen", "Midge",
                "Irmtrud", "Klaudia", "Pauletta", "Lacey", "Ione", "Lorna", "Sylvester"
        };
        return names[random.nextInt(names.length)];
    }

}
