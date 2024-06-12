package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingUnitOfMeasure extends CollectionCommand {

    public PrintFieldDescendingUnitOfMeasure(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        Comparator<Product> comparator= new Comparator<>(){

            @Override
            public int compare(Product o1, Product o2) {
                return o1.getUnitOfMeasure().compareTo(o2.getUnitOfMeasure());
            }
        };

        List<Product> list = this.getCollectionManager().getProducts().stream().sorted(comparator).toList();

        return new Response(list.toString(), null);
    }

    @Override
    public void setArg(String arg) {

    }
}
