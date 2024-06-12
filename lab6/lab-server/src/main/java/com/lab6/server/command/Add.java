package com.lab6.server.command;


import com.lab6.common.Instruction;
import com.lab6.common.Response;
import com.lab6.server.collection.CollectionManager;
import com.lab6.server.commandControler.ConsoleManager;
import com.lab6.server.product.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class Add extends CollectionCommand {
    private String arg;

    public Add(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Product newProduct() throws IllegalArgumentException, IOException {
        return new Product();
    }

    @Override
    public Response execute() throws IllegalArgumentException {

        if(arg.isEmpty()){
            Instruction instruction = new Instruction(12);
            instruction.addArg("\\S\\S*"); instruction.addArgName("имя:");
            instruction.addArg("-?\\d+");instruction.addArgName("координату 'х':");
            instruction.addArg("-?\\d+");instruction.addArgName("координату 'у':");
            instruction.addArg("\\d+");instruction.addArgName("цену:");
            instruction.addArg("[мМ][еЕ][тТ][рР][ыЫ]?|[мМ]|[сС][аА][нН][тТ][иИ][мМ][еЕ][тТ][рР][ыЫ]?|[сС][мМ]|[шШ][тТ][уУ][кК][аиА]И|[ш][т]|[г][р][а][м][м][ы]?|[г][р]|[м][и][л][л][и][г][р][а][м][м][ы]?|[м][г]");instruction.addArgName("единицу измерения:");
            instruction.addArg("\\S\\S*");instruction.addArgName("имя владельца:");
            instruction.addArg("1[7-9][0-9][0-9]|20[0-2][0-4]");instruction.addArgName("год рождения:");
            instruction.addArg("[1-9]|1[0-2]");instruction.addArgName("месяц рождения:");
            instruction.addArg("[1-9]|[12][0-9]|3[01]");instruction.addArgName("день рождения:");
            instruction.addArg("[зЗ][еЕ][лЛ][еёЕЁ][нН][ыЫ][йЙ]|[жЖ][еёЕЁ][лЛ][тТ][ыЫ][йЙ]|[оО][рР][аА][нН][жЖ][еЕ][вВ][ыЫ][йЙ]|[кК][оО][рР][иИ][чЧ][нН][еЕ][вВ][ыЫ][йЙ]|[кК][рР][аА][сС][нН][ыЫ][йЙ]|[чЧ][еёЕЁ][рР][нН][ыЫ][йЙ]|[сС][иИ][нН][иИ][йЙ]");instruction.addArgName("цвет глаз:");
            instruction.addArg("[зЗ][еЕ][лЛ][еёЕЁ][нН][ыЫ][йЙ]|[жЖ][еёЕЁ][лЛ][тТ][ыЫ][йЙ]|[оО][рР][аА][нН][жЖ][еЕ][вВ][ыЫ][йЙ]|[кК][оО][рР][иИ][чЧ][нН][еЕ][вВ][ыЫ][йЙ]|[кК][рР][аА][сС][нН][ыЫ][йЙ]|[чЧ][еёЕЁ][рР][нН][ыЫ][йЙ]|[сС][иИ][нН][иИ][йЙ]");instruction.addArgName("цвет волос:");
            instruction.addArg("[сС][шШ][аА]|[фФ][рР][аА][нН][цЦ][иИ][яЯ]|[сС][еЕ][вВ][еЕ][рР][нН][аА][яЯ] [кК][оО][рР][еЕ][яЯ]");instruction.addArgName("страну:");
            return new Response("add", instruction);
        }else{
            String[] args = arg.split(" ");
            try {
                Product product = new Product(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11]);
                this.getCollectionManager().getProducts().add(product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new Response("Продукт успешно добавлен", null);
        }
//        try {
//            Product newProduct = newProduct();
//            if (!checkCoordinates(newProduct.getCoordinates(), this.getCollectionManager())) {
//                this.getCollectionManager().addProduct(newProduct);
//                this.getCollectionManager().sort();
//                System.out.println("Продукт успешно добавлен.\nВведите команду:");
//            }
//        } catch (IOException | IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Некорректный ввод. Попробуйте еще раз.\nВведите команду:");
//        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    public boolean checkCoordinates(Coordinates coordinates, CollectionManager collectionManager) {
        for (Product product : collectionManager.getProducts()) {
            if (coordinates.equals(product.getCoordinates())) {
                throw new IllegalArgumentException("Такие координаты уже используются. Попробуйте еще раз.\nВведите команду:");
            }
        }
        return false;
    }
}
