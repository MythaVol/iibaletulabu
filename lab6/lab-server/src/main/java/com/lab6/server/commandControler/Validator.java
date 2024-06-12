package com.lab6.server.commandControler;


import com.lab6.server.collection.CollectionManager;
import com.lab6.server.command.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Validator {


    private final HashMap<String, Command> commands;

    CollectionManager collectionManager;

    public Validator(CollectionManager collectionManager, BufferedReader reader) {
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>() {{
            put("add", new Add(collectionManager));
            put("add_if_max", new AddIfMax(collectionManager));
            put("add_if_min", new AddIfMin(collectionManager));
            put("clear", new Clear(collectionManager));
            put("execute_script", new ExecuteScript(collectionManager));
            put("group_counting_by_unit_of_measure", new GroupCountingByUnitOfMeasure(collectionManager));
            put("help", new Help());
            put("info", new Info(collectionManager));
            put("print_field_descending_unit_of_measure", new PrintFieldDescendingUnitOfMeasure(collectionManager));
            put("remove_by_id", new RemoveById(collectionManager));
            put("remove_greater", new RemoveGreater(collectionManager));
//            put("save", new Save(collectionManager));
            put("show", new Show(collectionManager));
            put("sum_of_price", new SumOfPrice(collectionManager));
            put("update", new Update(collectionManager));
        }};
    }

    public static boolean isNotEmpty(String str) {
        return !(str == null) && !str.isEmpty();
    }

    public static boolean isInteger(String str) {
        return str.matches("-?\\d+");
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public boolean validate(String command) {
        final int ONE_ORGANIZATION = 1;
        final int TWO_ORGANIZATIONS = 2;
        if (command.split(" ").length > TWO_ORGANIZATIONS) {
            try {
                throw new IOException("Неверное количество аргументов. Попробуйте еще раз.\nВведите команду:");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else if (command.split(" ").length == TWO_ORGANIZATIONS) {
            if (command.split(" ")[0].equalsIgnoreCase("update") ||
                    command.split(" ")[0].equalsIgnoreCase("remove_by_id")) {
                if (!isInteger(command.split(" ")[1])) {
                    try {
                        throw new IOException("Аргумент должен быть целым числом. Попробуйте еще раз.\nВведите команду:");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                } else if (!collectionManager.checkId(command.split(" ")[1])) {
                    try {
                        throw new IOException("Элемента с таким id в колекции нет. Попробуйте еще раз.\nВведите команду:");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                }
            } else if (command.split(" ")[0].equalsIgnoreCase("execute_script")) {
                if (!command.split(" ")[1].endsWith(".txt")) {
                    try {
                        throw new IOException("Неверное расширение. Попробуйте еще раз.\nВведите команду:");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                }
            } else {
                try {
                    throw new IOException("Неверное количество аргументов. Попробуйте еще раз.\nВведите команду:");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        } else if (command.split(" ").length == ONE_ORGANIZATION) {

            if (command.split(" ")[0].equalsIgnoreCase("update") ||
                    command.split(" ")[0].equalsIgnoreCase("remove_by_id") ||
                    command.split(" ")[0].equalsIgnoreCase("execute_script")) {
                try {
                    throw new IOException("Неверное количество аргументов. Попробуйте еще раз.\nВведите команду:");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            } else if (command.isEmpty()) {
                try {
                    throw new IOException("");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            } else if (!commands.containsKey(command.split(" ")[0].toLowerCase())) {
                try {
                    throw new IOException("Такой команды нет. Попробуйте еще раз.\nВведите команду:");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}
