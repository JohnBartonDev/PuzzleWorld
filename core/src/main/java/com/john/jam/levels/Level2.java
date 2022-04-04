package com.john.jam.levels;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.UndergroundGame;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.entity.door.Commands.UnlockDoorCommand;
import com.john.jam.entity.door.DefaultDoorLockedDrawable;
import com.john.jam.entity.door.Door;
import com.john.jam.entity.switches.ButtonSwitch;
import com.john.jam.levels.commands.SwitchFloorSectionCommand;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.utils.Constants;
import com.john.jam.utils.EventListener;

public class Level2 extends Level {

    private FloorSection floor1Section1;
    private FloorSection floor2Section1;
    private ButtonSwitch floor1UnlockDoor1Switch;
    private Door floor1Door1;
    private Door floor2Door1;

    public Level2(GameScreen gameScreen) {
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
        Drawable drawable = null;

        y = (FloorSection.HEIGHT - ButtonSwitch.SIZE) * 0.5f;
        floor1UnlockDoor1Switch = new ButtonSwitch(150, floor1Section1.getBounds().getY() + 100);
        floor1Section1.addEntity(floor1UnlockDoor1Switch);

        b = floor1Section1.getBounds();
        x = (b.getWidth() - Door.DOOR_WIDTH) * 0.5f;
        floor1Door1 = new Door(b.getX() + x, b.getY());
        floor1Door1.setLockedDrawable(new DefaultDoorLockedDrawable(floor1Door1, gameAtlas));
        floor1Section1.addEntity(floor1Door1);

        b = floor2Section1.getBounds();
        x = (b.getWidth() - Door.DOOR_WIDTH) * 0.5f;
        masterDoor.getBounds().setPosition(b.getX() + x, b.getY());
        masterDoor.setLockedDrawable(new DefaultDoorLockedDrawable(masterDoor, gameAtlas));
        masterDoor.setCustomDoorDrawable(new RegionDrawable(gameAtlas.findRegion("masterDoor3")));
        floor2Section1.addEntity(masterDoor);
    }

    @Override
    protected void createLogic() {
        floor1UnlockDoor1Switch.setPressedCommand(new UnlockDoorCommand(floor1Door1));
        floor1Door1.setOpenDoorCommand(new SwitchFloorSectionCommand(this, floor2Section1));

        getEventManager().subscribe(Level.SWITCH_FLOOR_SECTION_EVENT, new EventListener<Level>() {
            @Override
            public void update(int type, Level level) {
                new UnlockDoorCommand(masterDoor).execute();
            }
        });
    }
}
