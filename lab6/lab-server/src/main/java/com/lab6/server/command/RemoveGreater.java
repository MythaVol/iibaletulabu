package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;

import java.io.BufferedReader;
import java.io.IOException;

public class RemoveGreater extends CollectionCommand {


    public RemoveGreater(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
            if (this.getCollectionManager().getProducts().isEmpty()) {
                return new Response("Коллекция пуста. Нечего удалять. Попробуйте еще раз.", null);
            } else {
                Product product = this.getCollectionManager().getProducts().stream().max(Product::compareTo).get();
                this.getCollectionManager().getProducts().remove(product);
                return new Response("Успешно удалены элементы больше заданного, если такие есть.", null);
            }
    }

    @Override
    public void setArg(String arg) {

    }
}
