package com.john.jam.entity.switches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.Entity;
import com.john.jam.utils.Command;
import com.john.jam.utils.Touchable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ToggleSwitch extends Entity implements Touchable {

    public static final int ENTER_STATE_1_FLAG = 1 << 8;
    public static final int ENTER_STATE_2_FLAG = 1 << 9;

    private boolean isRestricted;
    private boolean inFirstState = true;
    private Command state1Command;
    private Command state2Command;
    private Drawable drawable;
    private Color debugColor;

    public ToggleSwitch(float x, float y, float width, float height) {
        super(x, y, width, height);
        debugColor = new Color(Color.GREEN);
        eventManager.createEvents(ENTER_STATE_1_FLAG, ENTER_STATE_2_FLAG);
    }

    public void setState1Command(Command command) {
        state1Command = command;
    }

    public void setState2Command(Command command) {
        state2Command = command;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setFirstState() {
        inFirstState = true;
        debugColor.set(Color.GREEN);
        eventManager.notify(ENTER_STATE_1_FLAG, this);
        if (state1Command != null) state1Command.execute();
    }

    public void setSecondState() {
        inFirstState = false;
        debugColor.set(Color.BLACK);
        eventManager.notify(ENTER_STATE_2_FLAG, this);
        if (state2Command != null) state2Command.execute();
    }

    public boolean isFirstState() {
        return inFirstState;
    }

    public void toggle() {
        if (inFirstState) {
            setSecondState();
        }
        else {
            setFirstState();
        }
    }

    @Override
    public void touched() {
        if (isRestricted) return;
        toggle();
    }

    @Override
    public void debug(ShapeDrawer shapeDrawer) {
        super.debug(shapeDrawer);
//        shapeDrawer.filledRectangle(bounds, debugColor);
    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        if (drawable != null) drawable.draw(batch, shapeDrawer);
    }
}
