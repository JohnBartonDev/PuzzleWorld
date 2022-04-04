package com.john.jam.levels.commands;

import com.john.jam.utils.Command;

public class HelloWorldCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Hello World");
    }
}
