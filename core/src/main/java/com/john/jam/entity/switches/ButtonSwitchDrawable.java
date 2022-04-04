package com.john.jam.entity.switches;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.john.jam.entity.Drawable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ButtonSwitchDrawable extends Drawable {

    private Drawable labelDrawable;
    private Drawable buttonSwitchDrawable;

    public ButtonSwitchDrawable(Drawable buttonSwitchDrawable, Drawable labelDrawable) {
        super(0, 0, 0, 0);
        this.buttonSwitchDrawable = buttonSwitchDrawable;
        this.labelDrawable = labelDrawable;
    }

    @Override
    public void update(ShapeDrawer shapeDrawer) {

    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        if (buttonSwitchDrawable != null) buttonSwitchDrawable.draw(batch, shapeDrawer);
        if (labelDrawable != null) labelDrawable.draw(batch, shapeDrawer);
    }
}
