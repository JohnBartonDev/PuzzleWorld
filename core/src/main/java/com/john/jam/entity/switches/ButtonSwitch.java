package com.john.jam.entity.switches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.utils.Touchable;
import com.john.jam.entity.Drawable;
import com.john.jam.utils.Command;
import com.john.jam.entity.Entity;
import com.john.jam.utils.Constants;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ButtonSwitch extends Entity implements Touchable {

    public static final float SIZE = 50;

    private boolean isRestricted;
    private Color color;
    private Command pressedCommand;
    private Drawable drawable;

    public ButtonSwitch(float x, float y) {
        this(x, y, SIZE, SIZE);
    }

    public ButtonSwitch(float x, float y, float width, float height) {
        super(x, y, width, height);
        color = new Color(Constants.PURPLE);
    }

    public void restrict(boolean isRestricted) {
        this.isRestricted = isRestricted;
    }

    public void setPressedCommand(Command command) {
        pressedCommand = command;
    }

    public void setDrawableEntity(Drawable entity) {
        this.drawable = entity;
    }

    @Override
    public void touched() {
        if (isRestricted) return;
        if (pressedCommand != null) pressedCommand.execute();
    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        if (drawable != null) {
            drawable.draw(batch, shapeDrawer);
        }
        else {
            shapeDrawer.filledRectangle(bounds, color);
        }
    }
}
