package com.lab6.server;

import com.lab6.common.Request;
import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.command.*;
import com.lab6.server.jsonManager.JsonManager;

import java.io.File;
import java.util.HashMap;

public class RequestHandler {
    private HashMap<String, Command> commands;
    private CollectionManager collectionManager;

    public RequestHandler(){
        collectionManager = new CollectionManager();
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
            //put("save", new Save(collectionManager));
            put("show", new Show(collectionManager));
            put("sum_of_price", new SumOfPrice(collectionManager));
            put("update", new Update(collectionManager));
        }};
    }

    public Response handle(Request request){
        String command = request.getCommand();
        String arg = request.getArgument();
        commands.get(command).setArg(arg);
        Response response = commands.get(command).execute();
        return response;
    }

    public void save(){
        JsonManager jsonManager = new JsonManager();
        collectionManager.sort();
        jsonManager.write(collectionManager.getProducts(), new File(System.getenv("DATALABA")));
    }
}
