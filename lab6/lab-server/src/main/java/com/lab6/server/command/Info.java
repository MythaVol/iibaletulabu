package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;

public class Info extends CollectionCommand {

    public Info(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        return new Response(this.getCollectionManager().toString(), null);
    }

    @Override
    public void setArg(String arg) {

    }
}
