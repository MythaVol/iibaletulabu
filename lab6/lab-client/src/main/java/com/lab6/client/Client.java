package com.lab6.client;

import com.lab6.common.Request;
import com.lab6.common.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Client {
    private ArrayList<String> commands;
    private SocketChannel socketChannel;
    private final int PORT = 8189;
    private final String HOSTNAME = "localhost";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        try {
            client.connect();
        client.readCommands(client.socketChannel);
        RequestReader requestReader = new RequestReader(client);
        ResponseHandler responseHandler = new ResponseHandler(client);
        while (client.socketChannel.isConnected()){
            try {

                Request request = requestReader.read();
                client.write(request);
                Response response = client.read();
                responseHandler.handle(response);// обработка ответа
            }catch (IOException e){
                System.out.println("Соединение потеряно");
            }
        }
        }catch (Exception e){
            System.out.println("Невозможно подключится к серверу");
        }

    }

    public void connect() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(HOSTNAME, PORT));
    }

    public void write(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        socketChannel.write(byteBuffer);
    }
    public void readCommands(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(socketChannel.socket().getReceiveBufferSize());
        socketChannel.read(buffer);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream= new ObjectInputStream(byteArrayInputStream);
        ArrayList<String> commands = (ArrayList<String>) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        this.commands = commands;
    }
    public Response read() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(socketChannel.socket().getReceiveBufferSize());
        socketChannel.read(buffer);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream= new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return response;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }
}
