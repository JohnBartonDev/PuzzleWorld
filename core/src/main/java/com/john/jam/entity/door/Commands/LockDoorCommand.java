package com.john.jam.entity.door.Commands;

import com.john.jam.entity.door.Door;
import com.john.jam.utils.Command;

public class LockDoorCommand implements Command {

    private Door door;

    public LockDoorCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.lock();
    }
}
