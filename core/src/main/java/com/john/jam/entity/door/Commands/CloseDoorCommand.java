package com.john.jam.entity.door.Commands;

import com.john.jam.utils.Command;
import com.john.jam.entity.door.Door;

public class CloseDoorCommand implements Command {

    private Door door;

    public CloseDoorCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }
}
