package com.john.jam.levels.commands;

import com.john.jam.utils.Command;

public class PrintCommand implements Command {

    private String str;

    public PrintCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute() {
        if (str != null) System.out.println(str);
    }
}
