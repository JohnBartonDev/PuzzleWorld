package com.john.jam.entity.door;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.john.jam.entity.RectangleDrawable;
import com.john.jam.utils.Touchable;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.Entity;
import com.john.jam.utils.Command;
import com.john.jam.utils.Constants;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Door extends Entity implements Touchable {

    public enum DoorState {
        LOCKED,
        UNLOCKED_AND_CLOSED,
        UNLOCKED_AND_OPEN
    }

    public static final float DOOR_WIDTH = 90;
    public static final float DOOR_HEIGHT = 200;

    private Color doorColor;
    private DoorState state = DoorState.LOCKED;
    private Command openDoorCommand;
    private Drawable lockedDrawable;
    private Drawable openDrawable;
    private Drawable defaultDoorDrawable;
    private Drawable customDoorDrawable;

    public Door(float x, float y) {
        super(x, y, DOOR_WIDTH, DOOR_HEIGHT);
        doorColor = new Color(Constants.PURPLE);
        defaultDoorDrawable = new RectangleDrawable(x, y, DOOR_WIDTH, DOOR_HEIGHT);
    }

    public void setCustomDoorDrawable(Drawable drawable) {
        customDoorDrawable = drawable;
    }

    public boolean isUnlocked() {
        return !state.equals(DoorState.LOCKED);
    }

    public boolean isOpen() {
        return state.equals(DoorState.UNLOCKED_AND_OPEN);
    }

    void setState(DoorState state) {
        this.state = state;
    }

    public DoorState state() {
        return state;
    }

    public void setOpenDoorCommand(Command command) {
        openDoorCommand = command;
    }

    public void setLockedDrawable(Drawable drawable) {
        this.lockedDrawable = drawable;
    }

    public void setOpenDrawable(Drawable drawable) {
        this.openDrawable = drawable;
    }

    public void setColor(Color color) {
        this.doorColor.set(color);
    }

    public void lock() {
        if (!isUnlocked()) return;
        setState(DoorState.LOCKED);
    }

    public void unlock() {
        if (isUnlocked()) return;
        setState(DoorState.UNLOCKED_AND_CLOSED);
    }

    public void open() {
        if (!isUnlocked() || isOpen()) return;
        setState(DoorState.UNLOCKED_AND_OPEN);
    }

    public void close() {
        if (!isOpen()) return;
        setState(DoorState.UNLOCKED_AND_CLOSED);
    }

    @Override
    public void touched() {
        if (!isUnlocked()) return;

        if (isOpen()) {
            if (openDoorCommand != null) openDoorCommand.execute();
        }
        else {
            open();
        }
    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(bounds.x - 4, bounds.y, bounds.width + 8, bounds.height + 4, Color.WHITE);

        switch (state) {
            case LOCKED:
                if (customDoorDrawable == null) {
                    defaultDoorDrawable.setColor(doorColor);
                    defaultDoorDrawable.getBounds().set(bounds);
                    defaultDoorDrawable.draw(batch, shapeDrawer);
                }
                else {
                    customDoorDrawable.getBounds().set(bounds);
                    customDoorDrawable.draw(batch, shapeDrawer);
                }
//                shapeDrawer.filledRectangle(bounds, doorColor);
                if (lockedDrawable != null) lockedDrawable.draw(batch, shapeDrawer);
                break;
            case UNLOCKED_AND_CLOSED:
                if (customDoorDrawable == null) {
                    defaultDoorDrawable.setColor(doorColor);
                    defaultDoorDrawable.getBounds().set(bounds);
                    defaultDoorDrawable.draw(batch, shapeDrawer);
                }
                else {
                    customDoorDrawable.getBounds().set(bounds);
                    customDoorDrawable.draw(batch, shapeDrawer);
                }
//                shapeDrawer.filledRectangle(bounds, doorColor);

                break;
            case UNLOCKED_AND_OPEN:
                shapeDrawer.filledRectangle(bounds, Color.BLACK);
                if (openDrawable != null) openDrawable.draw(batch, shapeDrawer);
                break;
        }
    }

    @Override
    public void debug(ShapeDrawer shapeDrawer) {

    }

}
