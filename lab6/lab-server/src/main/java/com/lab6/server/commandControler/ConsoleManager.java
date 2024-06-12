package com.lab6.server.commandControler;


import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;

public class ConsoleManager {

    private BufferedReader reader;

    public ConsoleManager(BufferedReader reader) {
        this.reader = reader;
    }

    public String input(String message) throws IOException {
        System.out.println(message);
        return reader.readLine();
    }

    public Long inputLong(String message) throws IOException {
        System.out.println(message);
        return Long.parseLong(reader.readLine());
    }

    public int inputInt(String message) throws IOException {
        System.out.println(message);
        return Integer.parseInt(reader.readLine());
    }

    public ZonedDateTime inputDate(String message) throws IOException {
        System.out.println(message);
        return ZonedDateTime.parse(reader.readLine());
    }


    public static void message(String message) {
        System.out.println(message);
    }
}
