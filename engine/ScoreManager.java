package com.jessepiologo.DinoMeteorShower.engine;


/**
 * Created by Jessé Piologo on 6/1/2017.
 */

public class ScoreManager {
    public static int[] intToArray(int num) {
        StringBuffer sTest = new StringBuffer("00000");
        sTest.append(String.valueOf(num));
        String formatted = sTest.substring(sTest.length() - 5, sTest.length());
        int[] numbers = new int[5];
        for (int i = 0; i <= 4; i++) {
            char c = formatted.charAt(i);
            String s = Character.toString(c);
            numbers[i] = Integer.parseInt(s);
        }
        return numbers;
    }
}
