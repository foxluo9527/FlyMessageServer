package com.util;

import java.util.Random;

public class GetRandom {
    private static final char[] checkWords = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String getRandom() {
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            words.append(checkWords[new Random().nextInt(9)]);
        }
        return words.toString();
    }
}
