package com.john.jam.entity.door.Commands;

import com.john.jam.entity.Drawable;
import com.john.jam.entity.door.Door;
import com.john.jam.utils.Command;

public class ChangeDoorDrawableEntityCommand implements Command {

    private Drawable drawable;
    private Door door;

    private ChangeDoorDrawableEntityCommand(Door door, Drawable drawable) {
        this.door = door;
        this.drawable = drawable;
    }

    @Override
    public void execute() {
        door.setOpenDrawable(drawable);
    }
}
