//package com.lab6.server.command;
//
//
//import com.lab6.server.collection.CollectionManager;
//import com.lab6.server.jsonManager.JsonManager;
//
//import java.io.File;
//
//public class Save extends CollectionCommand {
//
//    JsonManager jsonManager = new JsonManager();
//
//    public Save(CollectionManager collectionManager) {
//        super(collectionManager);
//    }
//
//    @Override
//    public void execute() {
//        this.getCollectionManager().sort();
//        jsonManager.write(this.getCollectionManager().getProducts(), new File(System.getenv("DATALABA")));
//        System.out.println("Коллекция успешно сохранена.\nВведите команду:");
//    }
//}
