package com.john.jam.entity.switches;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.entity.Drawable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ToggleSwitchDrawable extends Drawable {

    private Drawable labelDrawable;
    private Drawable state0Drawable;
    private Drawable state1Drawable;
    private ToggleSwitch toggleSwitch;

    public ToggleSwitchDrawable(ToggleSwitch toggleSwitch, Drawable state0Drawable, Drawable state1Drawable, Drawable labelDrawable) {
        super(0, 0, 0, 0);
        this.toggleSwitch = toggleSwitch;
        this.state0Drawable = state0Drawable;
        this.state1Drawable = state1Drawable;
        this.labelDrawable = labelDrawable;
    }

    @Override
    public void update(ShapeDrawer shapeDrawer) {

    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        if (labelDrawable != null) labelDrawable.draw(batch, shapeDrawer);

        if (toggleSwitch.isFirstState()) {
            if (state0Drawable != null) state0Drawable.draw(batch, shapeDrawer);
        }
        else {
            if (state1Drawable != null) state1Drawable.draw(batch, shapeDrawer);
        }

    }
}
