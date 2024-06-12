package com.lab6.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Closer implements Runnable{

    private BufferedReader reader;

    private Server server;

    public Closer(Server server) {
        this.server = server;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String command = reader.readLine();
                if (command.equals("exit")) {
                    server.stop();
                    break;
                } else {
                    System.out.println("Неизвестная команда.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
