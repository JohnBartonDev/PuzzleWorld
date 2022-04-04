package com.john.jam.levels.commands;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.john.jam.utils.Command;

public class CommandSelectorCommand implements Command {

    private Command activeCommand;
    private ObjectMap<String, Command> commands;

    public CommandSelectorCommand() {
        commands = new ObjectMap<>();
    }

    public void addCommand(String name, Command command) {
        if (activeCommand == null) activeCommand = command;
        commands.put(name, command);
    }

    public void setCommand(String name) {
        activeCommand = commands.get(name);
    }

    @Override
    public void execute() {
        if (activeCommand != null) activeCommand.execute();
    }
}
