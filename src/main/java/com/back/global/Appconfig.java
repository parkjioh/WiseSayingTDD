package com.back.global;

public class Appconfig {
    private static String mode = "dev";

    public void setDevMode() {
        mode = "dev";
    }

    public static void setTestMode() {
        mode = "test";
    }

    public static String getMode() {
        return mode;
    }
}
