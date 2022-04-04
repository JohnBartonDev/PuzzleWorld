package com.john.jam.levels;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.john.jam.utils.Constants;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Floor {

    public static final float ELEVATOR_WIDTH = 40;
    public static final float WALL_THICKNESS = 20;
    public static final int MAX_SEGMENTS = 4;

    private final float x;
    private final float y;
    private boolean drawDisabledOverlay;
    private Array<FloorSection> sections;
    private Array<Rectangle> walls;

    public Floor(float x, float y) {
        this.x = x;
        this.y = y;
        sections = new Array<>(4);
        walls = new Array<>();
    }

    public void addSection(FloorSection section) {
        sections.add(section);
    }

    public Array<FloorSection> getSections() {
        return sections;
    }

    public void showDisabledOverlay(boolean drawDisabledOverlay) {
        this.drawDisabledOverlay = drawDisabledOverlay;
    }

    public void createWall(float x) {
        walls.add(new Rectangle(x, y + WALL_THICKNESS, WALL_THICKNESS, FloorSection.HEIGHT));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSegments() {
        int sum = 0;
        for (FloorSection s : sections) {
            sum += s.getSegments();
        }
        return sum;
    }

    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(x, y + WALL_THICKNESS, ELEVATOR_WIDTH, FloorSection.HEIGHT, Constants.PURPLE);
        shapeDrawer.filledRectangle(x, y, Constants.GAME_WIDTH, WALL_THICKNESS, Constants.DARK_PURPLE);

        for (FloorSection s : sections) {
            s.draw(batch, shapeDrawer);
        }

        for (Rectangle w : walls) {
            shapeDrawer.filledRectangle(w, Constants.DARK_PURPLE);
        }

        if (drawDisabledOverlay) {
            for (FloorSection s : sections) {
                if (s.isDisabled()) {
                    shapeDrawer.filledRectangle(s.getBounds(), Constants.BLACK_90_PERCENT);
                }
            }
        }
    }

    public void debug(ShapeDrawer shapeDrawer) {
        for (FloorSection s : sections) {
            s.debug(shapeDrawer);
        }
    }
}
