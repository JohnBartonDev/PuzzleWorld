package com.john.jam.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.john.jam.entity.Entity;
import com.john.jam.utils.Constants;
import com.john.jam.utils.CustomInputMultiplexer;
import com.john.jam.utils.EventManager;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class FloorSection extends CustomInputMultiplexer {

    public static final int ENABLED_EVENT_FLAG = 1 << 1;
    public static final int DISABLED_EVENT_FLAG = 1 << 2;
    public static final int HEIGHT = 300;
    public static final int SEGMENT_WIDTH = 310;

    private boolean isDisabled = true;
    private final int segments;
    private Color backgroundColor;
    private Rectangle bounds;
    private EventManager<FloorSection> eventManager;
    private Array<Entity> entities;

    public FloorSection(float x, float y, float width, int segments, boolean isDisabled) {
        bounds = new Rectangle(x, y, width, HEIGHT);
        backgroundColor = new Color(Constants.YELLOW);
        this.segments = segments;
        this.isDisabled = isDisabled;
        eventManager = new EventManager<>(ENABLED_EVENT_FLAG, DISABLED_EVENT_FLAG);
        entities = new Array<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        addProcessor(entity);
    }

    public EventManager getEventManger() {
        return eventManager;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;

        if (isDisabled) {
            eventManager.notify(DISABLED_EVENT_FLAG, this);
        }
        else {
            eventManager.notify(ENABLED_EVENT_FLAG, this);
        }
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public int getSegments() {
        return segments;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!bounds.contains(screenX, screenY)) return false;


        return super.touchDown(screenX, screenY, pointer, button);
    }

    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(bounds, backgroundColor);

        for (Entity e : entities) {
            e.draw(batch, shapeDrawer);
        }
    }

    public void debug(ShapeDrawer shapeDrawer) {
        for (Entity e : entities) {
            e.debug(shapeDrawer);
        }
    }
}
