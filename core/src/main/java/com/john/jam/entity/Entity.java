package com.john.jam.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.utils.CustomInput;
import com.john.jam.utils.EventManager;
import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class Entity extends CustomInput {

    private boolean isHidden;
    protected Rectangle bounds;
    protected EventManager eventManager;

    public Entity() {
        this(0, 0, 0, 0);
    }

    public Entity(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        eventManager = new EventManager();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void debugBounds(ShapeDrawer shapeDrawer) {
        shapeDrawer.rectangle(bounds, Color.GREEN);
    }

    public void update(float delta) {}

    public void draw(Batch batch, ShapeDrawer shapeDrawer) {}

    public void debug(ShapeDrawer shapeDrawer) {}
}
