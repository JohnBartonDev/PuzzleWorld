package com.john.jam.levels.commands;

import com.john.jam.levels.FloorSection;
import com.john.jam.levels.Level;
import com.john.jam.utils.Command;

public class SwitchFloorSectionCommand implements Command {

    private Level level;
    private FloorSection section;

    public SwitchFloorSectionCommand(Level level, FloorSection section) {
        this.level = level;
        this.section = section;
    }

    @Override
    public void execute() {
        level.switchFloorSection(section);
    }
}
