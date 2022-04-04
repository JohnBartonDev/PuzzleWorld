package com.john.jam.entity.door.Commands;

import com.badlogic.gdx.graphics.Color;
import com.john.jam.utils.Command;
import com.john.jam.entity.door.Door;

public class ChangeDoorColorCommand implements Command {

    private int idx;
    private Door door;
    private Color[] colors;

    public ChangeDoorColorCommand(Door door, Color... colors) {
        this.door = door;
        this.colors = colors;
    }

    @Override
    public void execute() {
        idx %= colors.length;
        door.setColor(colors[idx++]);
    }
}
