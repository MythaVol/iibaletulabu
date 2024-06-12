package com.lab6.server.command;


import com.lab6.common.Response;

public interface Command {

    Response execute();

    void setArg(String arg);
}
