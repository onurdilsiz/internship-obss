package com.example.finalproject.utils;

import com.example.finalproject.security.UserAuthDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {
    public static String getCurrentUser(){
        if (SecurityContextHolder.getContext() !=null && SecurityContextHolder.getContext().getAuthentication() != null) {
            final Object authPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (authPrincipal instanceof UserAuthDetails) {
                return  ((UserAuthDetails) authPrincipal).getUsername().toString();
            }
        }
        return StringUtils.EMPTY;
    }
}
