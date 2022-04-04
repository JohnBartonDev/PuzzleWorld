package com.john.jam.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.vabrant.actionsystem.actions.Colorable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class Drawable implements Colorable {

    protected Color color;
    protected Rectangle bounds;

    public Drawable(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        color = new Color(0xFFFFFFFF);
    }

    @Override
    public void setColor(Color color) {
        this.color.set(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void draw(Batch batch, ShapeDrawer shapeDrawer, float alpha) {}

    public abstract void update(ShapeDrawer shapeDrawer);
    public abstract void draw(Batch batch, ShapeDrawer shapeDrawer);


}
