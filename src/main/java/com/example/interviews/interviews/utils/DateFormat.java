package com.example.interviews.interviews.utils;

import java.util.Objects;

public class DateFormat {

    public static String getHour(String timestamp) {

        if (Objects.isNull(timestamp))
            return null;
        return timestamp.substring(0, 2) + "h";
    }
}
