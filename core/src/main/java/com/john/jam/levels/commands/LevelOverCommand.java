package com.john.jam.levels.commands;

import com.john.jam.levels.Level;
import com.john.jam.utils.Command;

public class LevelOverCommand implements Command {

    private Level level;

    public LevelOverCommand(Level level) {
        this.level = level;
    }

    @Override
    public void execute() {
        level.levelComplete();
    }
}
