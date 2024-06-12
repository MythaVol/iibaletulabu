package com.lab6.server.command;

import com.lab6.server.collection.CollectionManager;

public abstract class CollectionCommand implements Command {

    private CollectionManager collectionManager;

    public CollectionCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
