package com.lab6.common;

import java.io.Serializable;
import java.util.ArrayList;

public class Instruction implements Serializable {
    private int countOfArgs;
    private ArrayList<String> args;
    private ArrayList<String> argNames;

    public Instruction(int countOfArgs){
        this.countOfArgs = countOfArgs;
        args = new ArrayList<>();
        argNames = new ArrayList<>();
    }

    public void addArg(String arg){
        if(args.size()< countOfArgs){
            args.add(arg);
        }
    }
    public void addArgName(String arg){
        if(argNames.size()< countOfArgs){
            argNames.add(arg);
        }
    }

    public int getCountOfArgs() {
        return countOfArgs;
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    public ArrayList<String> getArgNames() {
        return argNames;
    }
}
