package com.lab6.server.product;


import java.io.IOException;
import java.util.regex.Pattern;

public enum Color {

    GREEN("зелёный"),

    YELLOW("жёлтый"),

    ORANGE("оранжевый"),

    BROWN("коричневый"),

    RED("красный"),

    BLACK("чёрный"),

    BLUE("синий");

    private String name;

    Color(String name) {
        if (Pattern.matches("зел[её]ный", name.toLowerCase())) {
            this.name = "зелёный";
        } else if (Pattern.matches("ж[её]лтый", name.toLowerCase())) {
            this.name = "жёлтый";
        } else if (name.equalsIgnoreCase("оранжевый")) {
            this.name = "оранжевый";
        } else if (name.equalsIgnoreCase("коричневый")) {
            this.name = "коричневый";
        } else if (name.equalsIgnoreCase("красный")) {
            this.name = "красный";
        } else if (Pattern.matches("ч[её]рный", name.toLowerCase())) {
            this.name = "чёрный";
        } else if (name.equalsIgnoreCase("синий")) {
            this.name = "синий";
        } else {
            throw new IllegalArgumentException("Некорректное название цвета.");
        }
    }


    public static Color getColor(String name) throws IOException {
        if (Pattern.matches("зел[её]ный", name.toLowerCase())) {
            return GREEN;
        } else if (Pattern.matches("ж[её]лтый", name.toLowerCase())) {
            return YELLOW;
        } else if (name.equalsIgnoreCase("оранжевый")) {
            return ORANGE;
        } else if (name.equalsIgnoreCase("коричневый")) {
            return BROWN;
        } else if (name.equalsIgnoreCase("красный")) {
            return RED;
        } else if (Pattern.matches("ч[её]рный", name.toLowerCase())) {
            return BLACK;
        } else if (name.equalsIgnoreCase("синий")) {
            return BLUE;
        }  else {
            throw new IOException("Некорректное название цвета.");
        }
    }
}
