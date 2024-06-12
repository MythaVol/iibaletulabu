package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;

public class RemoveById extends CollectionCommand implements Command {

    private String arg;

    public RemoveById(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public Response execute() {
        int id = Integer.parseInt(arg);
        for (Product product : this.getCollectionManager().getProducts()) {
            if (product.getId() == id) {
                this.getCollectionManager().removeProduct(product);
                System.out.println("Продукт успешно удален.\nВведите команду:");
                return new Response("Продукт успешно удален.", null);
            }
        }
        return new Response("Объект с таким id не найден", null);
    }
}
