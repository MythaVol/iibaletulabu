package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;

import java.io.BufferedReader;

public class Update extends CollectionCommand implements Command {

    private String arg;

    public Update(CollectionManager collectionManager) {
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
                try {
                    Add add = new Add(this.getCollectionManager());
                    Product newProduct = add.newProduct();
                    newProduct.setId(id);
                    if (!add.checkCoordinates(product.getCoordinates(), this.getCollectionManager()) || product.getCoordinates().equals(newProduct.getCoordinates())) {
                        this.getCollectionManager().removeProduct(product);
                        this.getCollectionManager().addProduct(newProduct);
                        this.getCollectionManager().sort();
                        System.out.println("Продукт успешно добавлен.\nВведите команду:");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Некорректный ввод. Попробуйте еще раз.\nВведите команду:");
                }
            }
        }
        System.out.println("Элемента с таким id в коллекции нет.\nВведите команду:");
        return new Response("", null);
    }
}
