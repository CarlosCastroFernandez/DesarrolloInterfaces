package org.example;

public class Sesion {
    private static String user;

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Sesion.user= user;
    }
}
