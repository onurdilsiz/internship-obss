package com.example.finalproject.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    @UtilityClass
    public static class ResponseCodes{
        public static final long SUCCESS = 1000000L;
        public static final long UNKNOWN_ERROR = 1000001L;
        public static final long USER_NOT_FOUND = 1000003L;
        public static final long ACCESS_DENIED = 1000004L;

    }

    @UtilityClass
    public static class Roles{
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_USER";


    }


}
