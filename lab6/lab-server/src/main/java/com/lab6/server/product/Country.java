package com.lab6.server.product;


import java.io.IOException;

public enum Country {

    USA("США"),

    FRANCE("Франция"),

    NORTH_KOREA("Северная Корея");

    private String name;

    Country(String name) {
        if (name.equalsIgnoreCase("США") ||
                name.equalsIgnoreCase("Франция") ||
                name.equalsIgnoreCase("Северная Корея")) {
            this.name = name;
        } else {
            try {
                throw new IOException("Такой страны нет в списке.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Country getCountry(String name) throws IOException {

        if (name.equalsIgnoreCase("США")) {
            return USA;
        } else if (name.equalsIgnoreCase("Франция")) {
            return FRANCE;
        } else if (name.equalsIgnoreCase("Северная Корея")) {
            return NORTH_KOREA;
        } else {
            throw new IOException("Такой страны нет в списке.");
        }
    }
}
