package com.lab6.server;

import com.lab6.common.Request;
import com.lab6.common.Response;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Server implements Runnable {

    private ServerSocketChannel serverSocketChannel;

    private boolean running = true;

    private final int PORT = 8189;

    @Override
    public void run() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (running) {
            try {
                System.out.println("Ожидание подключения");
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("Новое подключение " + socketChannel.getRemoteAddress());
                sendCommands(socketChannel);
                RequestHandler requestHandler = new RequestHandler();
                while (socketChannel.isConnected()) {
                    if(!running)
                        break;
                    try {
                        Request request = read(socketChannel);
                        Response response = requestHandler.handle(request);// обрабатываем заброс и даем ответ
                        sendResponse(socketChannel, response);// отправляем ответ
                    }catch (SocketException e){
                        requestHandler.save();
                        System.out.println("Клиент отключен");
                        socketChannel.close();
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        running = false;
    }

    private Request read(SocketChannel socketChannel) throws IOException, ClassNotFoundException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(socketChannel.socket().getReceiveBufferSize());
        socketChannel.read(buffer);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectOutputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectOutputStream.readObject();
        Thread.sleep(5000);
        objectOutputStream.close();
        byteArrayInputStream.close();
        return request;
    }
    private void sendCommands(SocketChannel socketChannel) throws IOException {
        ArrayList<String> commands = new ArrayList<>(List.of(
                "add",
                "add_if_max",
                "add_if_min",
                "clear",
                "execute_script \\S\\S*",
                "group_counting_by_unit_of_measure",
                "help",
                "info",
                "print_field_descending_unit_of_measure",
                "remove_by_id \\d+",
                "remove_greater",
                "save",
                "show",
                "sum_of_price",
                "update \\d+"
        ));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(commands);
        objectOutputStream.flush();
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        socketChannel.write(byteBuffer);
    }
    private void sendResponse(SocketChannel socketChannel, Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        socketChannel.write(byteBuffer);
    }
}
