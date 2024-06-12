package com.lab6.server.collection;

import com.lab6.server.jsonManager.JsonManager;
import com.lab6.server.product.Product;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public class CollectionManager  {

    private int idMax = 1;

    public int getIdMax() {
        return idMax++;
    }

    private final JsonManager jsonManager = new JsonManager();

    private final LinkedHashSet<Product> products = new LinkedHashSet<>();

    private final LocalDateTime creationDate;

    public CollectionManager() {
        creationDate = LocalDateTime.now();
        try {
            products.addAll(jsonManager.read(new File(System.getenv("DATALABA"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void sort() {
        ArrayList<Product> arrayList = new ArrayList<>(products);
        Collections.sort(arrayList);
        products.clear();
        products.addAll(arrayList);
    }

    public void shuffle() {
        ArrayList<Product> arrayList = new ArrayList<>(products);
        Collections.shuffle(arrayList);
        products.clear();
        products.addAll(arrayList);
    }

    public void clear() {
        products.clear();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LinkedHashSet<Product> getProducts() {
        return products;
    }

    public int searchById(String id) {
        int intId = Integer.parseInt(id);
        for (Product product : products) {
            if (product.getId() == intId) {
                return intId;
            }
        }
        return -1;
    }

    public boolean checkId(String id) {
        int intId = Integer.parseInt(id);
        for (Product product : products) {
            if (product.getId() == intId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "CollectionManager{" +
                ", creationDate=" + creationDate +
                '}';
    }
}
