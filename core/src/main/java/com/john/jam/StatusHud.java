package com.john.jam;

import com.badlogic.gdx.graphics.Color;
import com.john.jam.utils.Constants;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class StatusHud {

    public static final int HEIGHT = 80;

    public void debug(ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(0, 0, Constants.GAME_WIDTH, HEIGHT, Color.BLACK);

        float padding = (HEIGHT - 50) * 0.5f;
        shapeDrawer.filledRectangle(padding, padding, 50, 50, Constants.YELLOW);
    }
}
