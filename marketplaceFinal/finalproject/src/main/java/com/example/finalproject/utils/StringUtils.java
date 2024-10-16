package com.example.finalproject.utils;

import lombok.experimental.UtilityClass;

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

}
