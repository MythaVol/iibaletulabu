package com.lab6.client;

import com.lab6.common.Instruction;
import com.lab6.common.Request;
import com.lab6.common.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.SocketChannel;
import java.util.regex.Pattern;

public class ResponseHandler {
    private Client client;
    private BufferedReader bufferedReader;
    public ResponseHandler(Client client){
        this.client = client;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
    public void handle(Response response) throws IOException, ClassNotFoundException {
        if(response.getInstruction()==null){
            System.out.println(response.getText());
        }else{
            Instruction instruction = response.getInstruction();
            String args = "";
            for (int i = 0; i < instruction.getCountOfArgs(); i++) {
                System.out.println("Введите " + instruction.getArgNames().get(i));
                String arg = bufferedReader.readLine();
                if(Pattern.matches(instruction.getArgs().get(i), arg)){
                    args += arg + " ";
                }else{
                    System.out.println("Некорректный ввод");
                    i = i-1;
                }
            }
            Request request = new Request(response.getText(), args);
            client.write(request);
            Response response1 = client.read();
            handle(response1);
        }
    }
}
