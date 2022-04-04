package com.john.jam.levels.level1;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.UndergroundGame;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.entity.door.Commands.UnlockDoorCommand;
import com.john.jam.entity.door.DefaultDoorLockedDrawable;
import com.john.jam.entity.door.Door;
import com.john.jam.levels.FloorSection;
import com.john.jam.levels.Level;
import com.john.jam.levels.commands.MultiCommand;
import com.john.jam.levels.commands.SwitchFloorSectionCommand;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.screens.gamescreen.LevelCompleteCallback;
import com.john.jam.utils.Constants;
import com.john.jam.utils.EventListener;

public class Level1 extends Level {

    private FloorSection floor1Section1;
    private FloorSection floor2Section1;
    private Door floor1Door1;
    private Door floor2Door1;

    public Level1(GameScreen gameScreen) {
        super(gameScreen);
    }

    @Override
    protected void createSections(UndergroundGame game) {
        TextureAtlas gameAtlas = game.getAssetManager().get(Constants.GAME_ATLAS);

        floor1Section1 = createSection(4, false, floor1);
        floor2Section1 = createSection(4, true, floor2);

        float x = 0;
        float y = 0;
        Rectangle b = null;

        //---------- Create Door1 on floor1 ----------//
        b = floor1Section1.getBounds();
        x = ((FloorSection.SEGMENT_WIDTH * 4) - Door.DOOR_WIDTH) * 0.5f;
        floor1Door1 = new Door(b.getX() + x, b.getY());
        floor1Door1.unlock();
        floor1Section1.addEntity(floor1Door1);

        //---------- Create Door1 on floor2 ----------//
        b = floor2Section1.getBounds();
        x = ((FloorSection.SEGMENT_WIDTH * 4) - Door.DOOR_WIDTH) * 0.5f;
        floor2Door1 = new Door(b.getX() + x, b.getY());
        floor2Door1.unlock();
//        floor2Section1.addEntity(floor2Door1);

        //---------- Create MasterDoor ----------//
        masterDoor.getBounds().setPosition(b.getX() + x, b.getY());
        masterDoor.setLockedDrawable(new DefaultDoorLockedDrawable(masterDoor, gameAtlas));
        masterDoor.setCustomDoorDrawable(new RegionDrawable(gameAtlas.findRegion("masterDoor2")));
        floor2Section1.addEntity(masterDoor);
    }

    @Override
    protected void createLogic() {
        floor1Door1.setOpenDoorCommand(new MultiCommand(
                new SwitchFloorSectionCommand(this, floor2Section1),
                new UnlockDoorCommand(masterDoor)));

    }
}
