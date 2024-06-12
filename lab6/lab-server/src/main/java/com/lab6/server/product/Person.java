package com.lab6.server.product;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class Person {

    private String name;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime birthday;

    private Color eyeColor;

    private Color hairColor;

    private Country nationality;

    public Person() {
    }

    public Person(String name, LocalDateTime birthday, Color eyeColor, Color hairColor, Country nationality) throws IOException {
        this.name = name;
        if (name == null  || name.isEmpty()) {
            throw new IOException("Поле 'имя' не может быть пустым.");
        }
        this.birthday = birthday;
        if (birthday == null) {
            throw new IOException("Поле 'день рождения' не может быть пустым.");
        }
        this.eyeColor = eyeColor;
        if (eyeColor == null) {
            throw new IOException("Поле 'цвет глаз' не может быть пустым.");
        }
        this.hairColor = hairColor;
        if (hairColor == null) {
            throw new IOException("Поле 'цвет волос' не может быть пустым.");
        }
        this.nationality = nationality;
        if (nationality == null) {
            throw new IOException("Поле 'национальность' не может быть пустым.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }
}
