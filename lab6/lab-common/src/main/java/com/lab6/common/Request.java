package com.lab6.common;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String argument;
    public Request(String command, String argument){
        this.command = command;
        this.argument = argument;
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }
}
