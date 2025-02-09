package com.example.demo.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class StringUtils {
    public static final String EMPTY = "";

    public static boolean isBlank(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    public static Boolean isNotBlank(String str){
        return !isBlank(str);
    }

    public static String generateRandomString(int targetStringLength) {

        int leftLimit = 48;
        int rightLimit = 122; // letter 'z'
        Random random = new Random();


        return random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57|| i >= 65) && (i <= 90 || i >=97)) .
        limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}


