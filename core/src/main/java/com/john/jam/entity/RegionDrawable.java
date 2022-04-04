package com.john.jam.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class RegionDrawable extends Drawable {

    private TextureRegion region;

    public RegionDrawable(TextureRegion region) {
        this(0, 0, 0, 0, region);
    }

    public RegionDrawable(float x, float y, float width, float height, TextureRegion region) {
        super(x, y, width, height);
        this.region = region;
    }

    @Override
    public void update(ShapeDrawer shapeDrawer) {

    }

    @Override
    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        Rectangle b = bounds;

        batch.setColor(color);
        batch.draw(region, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        batch.setColor(Color.WHITE);
    }
}
