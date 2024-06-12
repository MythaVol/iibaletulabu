package com.lab6.server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Closer closer = new Closer(server);
        new Thread(server).start();
        new Thread(closer).start();
    }
}
