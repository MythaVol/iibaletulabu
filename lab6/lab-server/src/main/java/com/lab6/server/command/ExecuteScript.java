package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.commandControler.CommandControl;
import com.lab6.server.commandControler.CommandExecutor;
import com.lab6.server.commandControler.ConsoleManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExecuteScript extends CollectionCommand implements Command {

    private String arg;

    private static ArrayList<String> scriptNames = new ArrayList<>();

    public ExecuteScript(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        File file = new File(arg);
        ExecuteScript.getScriptNames().add(arg);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            CommandControl commandControl = new CommandControl(this.getCollectionManager(), reader) {
                @Override
                public void go() {
                    try {
                        CommandExecutor commandExecutor = new CommandExecutor();
                        while (reader.ready()) {
                            String command = reader.readLine();
                            ConsoleManager.message(command);
                            if (command.equalsIgnoreCase("exit")) {
                                break;
                            } else if (getValidator().validate(command)) {
                                if (command.split(" ")[0].equalsIgnoreCase("execute_script")) {
                                    String path = command.split(" ")[1];
                                    if (ExecuteScript.getScriptNames().contains(path)) {
                                        ConsoleManager.message("Скрипт " + path + " нельзя вызывать, пока он запущен.");
                                    }
                                } else if (command.split(" ").length == 1) {
                                    commandExecutor.execute(getValidator().getCommands().get(command));
                                } else {
                                    commandExecutor.executeArc((ArgCommand) getValidator().getCommands().get(command.split(" ")[0]), command.split(" ")[1]);
                                }
                            }
                        }
                    } catch (Exception e) {
                        ConsoleManager.message("Файл не найден.");
                    }
                }
            };
            commandControl.go();
        } catch (Exception e) {
            System.out.println("Файл не найден.");
        }
        scriptNames.remove(arg);
        System.out.println("Введите команду:");
        return new Response("", null);
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    public static ArrayList<String> getScriptNames() {
        return scriptNames;
    }
}
