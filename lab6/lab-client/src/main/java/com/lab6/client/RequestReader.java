package com.lab6.client;

import com.lab6.common.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RequestReader {

    private Client client;

    private BufferedReader reader;
    private ArrayList<String> commands;

    public RequestReader(Client client) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.client = client;
        commands = client.getCommands();
    }

    public Request read() throws IOException {
        System.out.println("Введите команду: ");
        String command = reader.readLine();
        if(matches(command))
            if(command.split(" ").length == 2)
                return new Request(command.split(" ")[0],command.split(" ")[1]);
            else return new Request(command.split(" ")[0],"");
        else{
            System.out.println("Команда введена неверно");
            return read();
        }
    }
    private boolean matches(String command){
        for (int i = 0; i <commands.size(); i++) {
            if(Pattern.matches(commands.get(i), command))
                return true;
        }
        return false;
    }
}
