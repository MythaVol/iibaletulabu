package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;

public class Clear extends CollectionCommand {

    public Clear(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        getCollectionManager().clear();
        return new Response("Коллекция успешно очищена.", null);
    }

    @Override
    public void setArg(String arg) {

    }
}
