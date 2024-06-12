package com.lab6.server.product;


import java.io.IOException;
import java.util.regex.Pattern;

public enum UnitOfMeasure {

    METERS("м"),

    CENTIMETERS("см"),

    PCS("шт"),

    GRAMS("гр"),

    MILLIGRAMS("мг");

    private final String name;

    UnitOfMeasure(String name) {
        if (name.equalsIgnoreCase("м") || Pattern.matches("метр[ы]?", name.toLowerCase())) {
            this.name = "м";
        } else if (name.equalsIgnoreCase("см") || Pattern.matches("сантиметр[ы]?", name.toLowerCase())) {
            this.name = "см";
        } else if (name.equalsIgnoreCase("шт") || Pattern.matches("штук[аи]", name.toLowerCase())) {
            this.name = "шт";
        } else if (name.equalsIgnoreCase("гр") || Pattern.matches("грамм[ы]?", name.toLowerCase())) {
            this.name = "гр";
        } else if (name.equalsIgnoreCase("мг") || Pattern.matches("миллиграмм[ы]?", name.toLowerCase())) {
            this.name = "мг";
        } else {
            throw new IllegalArgumentException("Такой единицы измерения нет в списке.");
        }
    }

    @Override
    public String toString() {
        return name + ".";
    }


    public static UnitOfMeasure getUnitMeasure(String name) throws IOException {
        if (name.equalsIgnoreCase("м") || Pattern.matches("метр[ы]?", name.toLowerCase())) {
            return METERS;
        } else if (name.equalsIgnoreCase("см") || Pattern.matches("сантиметр[ы]?", name.toLowerCase())) {
            return CENTIMETERS;
        } else if (name.equalsIgnoreCase("шт") || Pattern.matches("штук[аи]", name.toLowerCase())) {
            return PCS;
        } else if (name.equalsIgnoreCase("гр") || Pattern.matches("грамм[ы]?", name.toLowerCase())) {
            return GRAMS;
        } else if (name.equalsIgnoreCase("мг") || Pattern.matches("миллиграмм[ы]?", name.toLowerCase())) {
            return MILLIGRAMS;
        } else {
            throw new IOException("Такой единицы измерения нет в списке.");
        }
    }
}
