package com.lab6.server.product;


import com.lab6.server.collection.CollectionManager;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class Product implements Comparable<Product> {

    CollectionManager collectionManager;

    private int id;

    private String name;

    private Coordinates coordinates;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    private long price;

    private UnitOfMeasure unitOfMeasure;

    private Person owner;

    public Product (CollectionManager collectionManager, String name, Coordinates coordinates, long price, UnitOfMeasure unitOfMeasure, Person owner) throws IOException {
        this.collectionManager = collectionManager;
        this.id = collectionManager.getIdMax();
        this.name = name;
        if (name == null || name.isEmpty()) {
            throw new IOException("Поле 'имя' не может быть пустым.");
        }
        this.coordinates = coordinates;
        if (coordinates == null) {
            throw new IOException("Поле 'координаты' не может быть пустым.");
        }
        this.creationDate = LocalDateTime.now();
        this.price = price;
        if (price <= 0) {
            throw new IOException("Поле 'цена' должно иметь значение больше нуля.");
        }
        this.unitOfMeasure = unitOfMeasure;
        if (unitOfMeasure == null) {
            throw  new IOException("Поле 'единицы измерения' не может быть пустым.");
        }
        this.owner = owner;
        if (owner == null) {
            throw  new IOException("Поле 'владелец' не может быть пустым.");
        }
    }

    public Product() {
    }

    public Product(String name,
                   String x,
                   String y,
                   String price,
                   String unitOfMeasure,
                   String ownerName,
                   String year,
                   String month,
                   String dey,
                   String eyeColor,
                   String hairColor,
                   String country) throws IOException {

        final int ZERO = 0;
        this.name = name;
        coordinates = new  Coordinates(Long.valueOf(x), Long.parseLong(y));
        this.price = Long.parseLong(price);
        this.unitOfMeasure = UnitOfMeasure.getUnitMeasure(unitOfMeasure);
        owner = new Person(ownerName, LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(dey), ZERO, ZERO, ZERO), Color.getColor(eyeColor), Color.getColor(hairColor), Country.getCountry(country));
    }

    @Override
    public int compareTo(Product product) {
        return product.getCoordinates().compareTo(this.getCoordinates());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + "\n," +
                "  name='" + name + '\'' + "\n," +
                "  coordinates=" + coordinates + "\n," +
                "  creationDate=" + creationDate + "\n," +
                "  price=" + price + "р.\n," +
                "  unitOfMeasure=" + unitOfMeasure + "\n," +
                "  owner=" + owner + "\n" +
                "}\n";
    }
}
