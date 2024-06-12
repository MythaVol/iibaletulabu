package com.lab6.server.product;


import com.lab6.server.commandControler.ConsoleManager;
import com.lab6.server.commandControler.Validator;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class ProductVariablesCreator {

    public String createName(ConsoleManager consoleManager) throws IOException {
        String name = consoleManager.input("Введите имя продукта: ");
        checkExit(name);
        while (!Validator.isNotEmpty(name) || checkSpace(name)) {
            if (!Validator.isNotEmpty(name)) {
                name = consoleManager.input("Введите не пустое имя продукта: ");
            } else {
                name = consoleManager.input("Осторожней с пробелами. Введите имя продукта: ");
            }
            checkExit(name);
        }
        return name;
    }

    private Long createX(ConsoleManager consoleManager) {
        final long X_MIN = -327;
        Long x = null;
        while (x == null || x <= X_MIN) {
            try {
                String variable = consoleManager.input("Введите координату 'x':  ");
                checkExit(variable);
                x = Long.parseLong(variable);
            } catch (NumberFormatException | IOException e) {
                System.out.println("Координата 'x' должна быть целым числом больше чем -327.");
            }
        }
        return x;
    }

    private long createY(ConsoleManager consoleManager) {
        final long Y_MAX = 570;
        Long y = null;
        while (y == null || y > Y_MAX) {
            try {
                String variable = consoleManager.input("Введите координату 'y':  ");
                checkExit(variable);
                y = Long.parseLong(variable);
            } catch (NumberFormatException | IOException e) {
                System.out.println("Координата 'y' должна быть целым числом меньше или равной 570-ти.");
            }
        }
        return y;
    }

    public Coordinates createCoordinate(ConsoleManager consoleManager) {
        Coordinates coordinate = null;
        while (coordinate == null) {
            try {
                Long x = createX(consoleManager);
                long y = createY(consoleManager);
                coordinate = new  Coordinates(x, y);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return coordinate;
    }

    public long createPrice(ConsoleManager consoleManager) {
        final long PRICE_MIN = 0;
        Long price = null;
        while (price == null || price <= PRICE_MIN) {
            try {
                String variable = consoleManager.input("Введите цену:  ");
                checkExit(variable);
                price = Long.parseLong(variable);
            } catch (NumberFormatException | IOException e) {
                System.out.println("Цена должна быть целым числом больше нуля.");
            }
        }
        return price;
    }

    public UnitOfMeasure createUnitOfMeasure(ConsoleManager consoleManager) {
        UnitOfMeasure unitOfMeasure = null;
        while (unitOfMeasure == null) {
            try {
                String variable = consoleManager.input("Введите единицу измерения: ");
                checkExit(variable);
                unitOfMeasure = UnitOfMeasure.getUnitMeasure(variable);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return unitOfMeasure;
    }

    private String createOwnerName(ConsoleManager consoleManager) throws IOException {
        String owner = consoleManager.input("Введите имя владельца: ");
        checkExit(owner);
        while (!Validator.isNotEmpty(owner) || checkSpace(owner)) {
            if (!Validator.isNotEmpty(owner)) {
                owner = consoleManager.input("Введите не пустое имя владельца: ");
            } else {
                owner = consoleManager.input("Осторожней с пробелами. Введите имя владельца: ");
            }
            checkExit(owner);
        }
        return owner;
    }

    private int createYear(ConsoleManager consoleManager) {
        final int YEAR_MIN = 1600;
        LocalDateTime now = LocalDateTime.now();
        Integer year = null;
        while (year == null || year < YEAR_MIN || year > now.getYear()) {
            try {
                String variable = consoleManager.input("Введите год рождения владельца: ");
                checkExit(variable);
                year = Integer.parseInt(variable);
                if (year < YEAR_MIN || year > now.getYear()) {
                    System.out.println("Год должен быть целым числом, большим или равным 1600 и меньшим " + now.getYear() + ".");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Год должен быть целым числом, большим или равным 1600 и меньшим " + now.getYear() + ".");
            }
        }
        return year;
    }

    private int createMonth(ConsoleManager consoleManager) {
        final int MONTH_MIN = 1;
        final int MONTH_MAX = 12;
        Integer month = null;
        while (month == null || month < MONTH_MIN || month > MONTH_MAX) {
            try {
                String variable = consoleManager.input("Введите месяц рождения владельца: ");
                checkExit(variable);
                month = Integer.parseInt(variable);
                if (month < MONTH_MIN || month > MONTH_MAX) {
                    System.out.println("Номер месяца должен быть целым числом от 1-го до 12-ти");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Номер месяца должен быть целым числом от 1-го до 12-ти");
            }
        }
        return month;
    }

    private int createDey(ConsoleManager consoleManager) {
        final int DAY_MIN = 1;
        final int DAY_MAX = 31;
        Integer day = null;
        while (day == null || day < DAY_MIN || day > DAY_MAX) {
            try {
                String variable = consoleManager.input("Введите день рождения владельца: ");
                checkExit(variable);
                day = Integer.parseInt(variable);
                if (day < DAY_MIN || day > DAY_MAX) {
                    System.out.println("День должен быть целым числом от 1 до 31");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("День должен быть целым числом от 1 до 31");
            }
        }
        return day;
    }

    private LocalDateTime createDate(ConsoleManager consoleManager) {
        final int ZERO = 0;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birthday = null;
        while (birthday == null || birthday.isAfter(now)) {
            try {
                int year = createYear(consoleManager);
                int month = createMonth(consoleManager);
                int day = createDey(consoleManager);
                birthday = LocalDateTime.of(year, month, day, ZERO, ZERO, ZERO);
            } catch (DateTimeException e) {
                System.out.println("Такой даты не существует.");
            }
        }
        return birthday;
    }

    private Color createEyeColor(ConsoleManager consoleManager) {
        Color eyeColor = null;
        while (eyeColor == null) {
            try {
                String variable = consoleManager.input("Введите цвет глаз владельца: ");
                checkExit(variable);
                eyeColor = Color.getColor(variable);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return eyeColor;
    }

    private Color createHairColor(ConsoleManager consoleManager) {
        Color hairColor = null;
        while (hairColor == null) {
            try {
                String variable = consoleManager.input("Введите цвет волос владельца: ");
                checkExit(variable);
                hairColor = Color.getColor(variable);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return hairColor;
    }

    private Country createCountry(ConsoleManager consoleManager) {
        Country country = null;
        while (country == null) {
            try {
                String variable = consoleManager.input("Введите страну владельца: ");
                checkExit(variable);
                country = Country.getCountry(variable);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return country;
    }

    public Person createPerson(ConsoleManager consoleManager) {
        Person person = null;
        while (person == null) {
            try {
                String owner = createOwnerName(consoleManager);
                LocalDateTime birthday = createDate(consoleManager);
                Color eyeColor = createEyeColor(consoleManager);
                Color hairColor = createHairColor(consoleManager);
                Country country = createCountry(consoleManager);
                person = new Person(owner, birthday, eyeColor, hairColor, country);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return person;
    }

    private boolean checkSpace(String str) {
        return Pattern.matches("^\\s.*", str) || Pattern.matches(".*\\s$", str) || Pattern.matches(".*\\s\\s.*", str);
    }

    private void checkExit(String variable) {
        if (variable.equalsIgnoreCase("exit")) {
            throw new InputMismatchException("");
        }
    }
}
