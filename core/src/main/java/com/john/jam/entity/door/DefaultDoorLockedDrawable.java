package com.john.jam.entity.door;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.utils.Utils;

public class DefaultDoorLockedDrawable extends RegionDrawable {

    private static final float LOCK_WIDTH = 30;
    private static final float LOCK_HEIGHT = 43;

    public DefaultDoorLockedDrawable(Door door, TextureAtlas gameAtlas) {
        super(0, 0, LOCK_WIDTH, LOCK_HEIGHT, gameAtlas.findRegion("lock"));
        Utils.centerEntityToEntity(bounds, door.getBounds());
    }
}
