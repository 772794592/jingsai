package com.example.jingsai.utils;

public class Util {

    public static int toKb(String res) {
        if (res.endsWith("g")) {
            String val = res.replace("g", "");
            return (int) Double.parseDouble(val) * 1024 * 1024;
        }
        return Integer.parseInt(res);
    }

}
