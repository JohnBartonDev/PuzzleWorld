package com.john.jam.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class RectangleDrawable extends Drawable {

    private Color color;

    public RectangleDrawable(float x, float y, float width, float height) {
        super(x, y, width, height);
        color = new Color(0xFFFFFFFF);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    @Override
    public void update(ShapeDrawer shapeDrawer) {

    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(bounds, color);
    }
}
