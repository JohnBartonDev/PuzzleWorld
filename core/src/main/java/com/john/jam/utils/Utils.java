package com.john.jam.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.john.jam.entity.Entity;

public class Utils {

    public static Touchable getTouchedEntity(float x, float y, Array<Entity> entities) {
        for (Entity e : entities) {
            if (!(e instanceof Touchable)) continue;
            if (e.getBounds().contains(x, y)) {
                return (Touchable)e;
            }
        }

        return null;
    }

    public static Color createColor(float r, float g, float b, float a) {
        return new Color(r / 255f, g / 255f, b / 255f, a);
    }

    public static Color createColor(int hex) {
        return new Color(hex);
    }

    public static void centerEntityHorizontallyToEntity(Rectangle src, Rectangle dst) {
        float x = (dst.getWidth() - src.getWidth()) * 0.5f;
        src.setX(dst.getX() + x);
    }

    public static void centerEntityVerticallyToEntity(Rectangle src, Rectangle dst) {
        float y = (dst.getHeight() - src.getHeight()) * 0.5f;
        src.setY(dst.getY() + y);
    }

    public static void centerEntityToEntity(Rectangle src, Rectangle dst) {
        centerEntityHorizontallyToEntity(src, dst);
        centerEntityVerticallyToEntity(src, dst);
    }

    public static float convert(float value, float size) {
        return ((value + 1) * size) - size;
    }

}
