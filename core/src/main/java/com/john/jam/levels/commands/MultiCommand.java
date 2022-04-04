package com.john.jam.levels.commands;

import com.john.jam.utils.Command;

public class MultiCommand implements Command {

    private Command[] commands;

    public MultiCommand(Command... commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command c : commands) {
            c.execute();
        }
    }
}
