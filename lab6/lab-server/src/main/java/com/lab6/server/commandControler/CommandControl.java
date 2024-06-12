package com.lab6.server.commandControler;


import com.lab6.server.collection.CollectionManager;
import com.lab6.server.command.ArgCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandControl {

    private final Validator validator;

    public CommandControl(CollectionManager collectionManager, BufferedReader reader) {
        this.validator = new Validator(collectionManager, reader);
    }

    public void go() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandExecutor commandExecutor = new CommandExecutor();
        System.out.println("Введите команду: ");

        while (true) {
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            } else if (validator.validate(command)) {
                if (command.split(" ").length == 1) {
                    commandExecutor.execute(validator.getCommands().get(command));
                } else {
                    commandExecutor.executeArc((ArgCommand) validator.getCommands().get(command.split(" ")[0]), command.split(" ")[1]);
                }
            }
        }
    }

    public Validator getValidator() {
        return validator;
    }
}
