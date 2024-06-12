package com.lab6.common;

import java.io.Serializable;
import java.util.Optional;

public class Response implements Serializable {
    private String text;// текст ответа
    private Instruction instruction;// доп инструкции для клиента, чтобы получить ещё информации
    public Response(String text, Instruction instruction){
        this.text = text;
        this.instruction = instruction;
    }

    public String getText() {
        return text;
    }

    public Instruction getInstruction() {
        return instruction;
    }
}
