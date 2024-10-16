package com.example.finalproject.utils;

import com.example.finalproject.model.Role;
import lombok.experimental.UtilityClass;

import java.util.List;
@UtilityClass

public class CollectionUtils {

    public static boolean isEmpty(List<String> roles) {
        return roles.isEmpty();
    }
    public static boolean isNotEmpty(List<Role> roles) {
        return !roles.isEmpty();
    }
}
