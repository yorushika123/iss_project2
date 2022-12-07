package com.javaclimb.jxnu.utils;

import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length) {
        String string= "qwertyuiopasdfghjklzxcvbnm0123456789";
        Random random = new Random();
        StringBuffer url = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(string.length());
            url.append(string.charAt(number));
        }
        return url.toString();
    }
}
