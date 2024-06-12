package com.lab6.server.commandControler;


import com.lab6.server.command.ArgCommand;
import com.lab6.server.command.Command;

public class CommandExecutor {

    public void execute(Command command) {
        command.execute();
    }

    public void executeArc(ArgCommand command, String arg) {
        command.setArg(arg);
        command.execute();
    }
}
