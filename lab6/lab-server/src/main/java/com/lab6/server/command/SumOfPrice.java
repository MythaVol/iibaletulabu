package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;

public class SumOfPrice extends CollectionCommand {

    public SumOfPrice(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        long sum = 0;
        for (Product product : this.getCollectionManager().getProducts()) {
            sum += product.getPrice();
        }
        return new Response("Сумма цен: " + sum, null);
    }

    @Override
    public void setArg(String arg) {

    }
}
