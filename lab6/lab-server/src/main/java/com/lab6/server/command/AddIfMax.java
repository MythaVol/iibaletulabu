package com.lab6.server.command;


import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.product.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

public class AddIfMax extends CollectionCommand {

    private String arg;


    public AddIfMax(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute() {
        Add add = new Add(this.getCollectionManager());
        try {
            Product product = add.newProduct();
            Product max = Collections.max(this.getCollectionManager().getProducts());
            if (product.compareTo(max) > 0) {
                if (!add.checkCoordinates(product.getCoordinates(), this.getCollectionManager())) {
                    this.getCollectionManager().addProduct(product);
                    this.getCollectionManager().sort();
                    System.out.println("Продукт успешно добавлен.\nВведите команду:");
                }  else {
                    System.out.println("Продукт с такими координатами уже существует.\nВведите команду:");
                }
            } else {
                System.out.println("Продукт не добавлен.\nВведите команду:");
            }
            System.out.println("Продукт успешно добавлен.\nВведите команду:");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Некорректный ввод. Попробуйте еще раз.\nВведите команду:");
        }
        return new Response("", null);
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }
}
