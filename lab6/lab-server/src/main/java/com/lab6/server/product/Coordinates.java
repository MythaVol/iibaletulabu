package com.lab6.server.product;


import java.io.IOException;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {

    private final static long X_MIN = -327;

    private final static long Y_MAX = 570;

    private Long x;

    private long y;

    public Coordinates() {
    }

    public Coordinates(Long x, long y) throws IOException {
        if (x == null || x <= X_MIN) {
            throw new IOException("Поле 'x' не может быть пустым или меньше или равно -327.");
        } else {
            this.x = x;
        }
        if (y > Y_MAX) {
            throw new IOException("Поле 'y' не может быть больше 570.");
        } else {
            this.y = y;
        }
    }

    public long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        if (this.y == coordinates.getY()) {
            return Long.compare(coordinates.getX(), this.x);
        }
        return Long.compare(this.y, coordinates.getY());
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
