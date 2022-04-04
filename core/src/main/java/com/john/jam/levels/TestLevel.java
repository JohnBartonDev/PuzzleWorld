package com.john.jam.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.UndergroundGame;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.entity.door.Commands.UnlockDoorCommand;
import com.john.jam.entity.door.Door;
import com.john.jam.entity.door.DefaultDoorLockedDrawable;
import com.john.jam.levels.commands.HelloWorldCommand;
import com.john.jam.levels.commands.SwitchFloorSectionCommand;
import com.john.jam.puzzles.PuzzleView;
import com.john.jam.puzzles.ShowViewCommand;
import com.john.jam.puzzles.wordsearch.WordSearchPuzzleView;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.utils.*;
import com.john.jam.entity.switches.ButtonSwitch;

public class TestLevel extends Level {

    private FloorSection floor1Section1;
    private FloorSection floor2Section1;
    private ButtonSwitch floor1Section1ButtonSwitch1;
    private Door floor1Door1;
    private Door floor2Door1;
    private PuzzleView puzzleView;

    public TestLevel(GameScreen gameScreen) {
        super(gameScreen);
    }

    protected void createSections(UndergroundGame game) {
        TextureAtlas gameAtlas = game.getAssetManager().get(Constants.GAME_ATLAS);

        floor1Section1 = createSection(4, false, floor1);
        floor2Section1 = createSection(4, true, floor2);

        float x = 0;
        float y = 0;
        Rectangle b = null;
        Drawable drawable = null;

        //Create ButtonSwitch1 on floor 1
        y = (FloorSection.HEIGHT - ButtonSwitch.SIZE) * 0.5f;
        floor1Section1ButtonSwitch1 = new ButtonSwitch(150, floor1Section1.getBounds().getY() + 100);
        floor1Section1.addEntity(floor1Section1ButtonSwitch1);

        //Create DrawableEntity for ButtonSwitch1
        b = floor1Section1ButtonSwitch1.getBounds();
        drawable = new RegionDrawable(0, b.getY() + b.getHeight() + 6, 93, 25, gameAtlas.findRegion("unlockText"));
        Utils.centerEntityHorizontallyToEntity(drawable.getBounds(), floor1Section1ButtonSwitch1.getBounds());
        floor1Section1ButtonSwitch1.setDrawableEntity(drawable);

        //Create Door1 on floor1
        b = floor1Section1.getBounds();
        floor1Door1 = new Door(b.getX() + 250, b.getY());
        floor1Door1.setLockedDrawable(new DefaultDoorLockedDrawable(floor1Door1, gameAtlas));
        floor1Section1.addEntity(floor1Door1);

        //Create Door1 on floor2
        b = floor2Section1.getBounds();
        floor2Door1 = new Door(b.getX() + 500, b.getY());
        floor2Door1.unlock();
        floor2Door1.setLockedDrawable(new DefaultDoorLockedDrawable(floor2Door1, gameAtlas));
        floor2Section1.addEntity(floor2Door1);

        //Master door
        b = floor2Section1.getBounds();
//        Door masterDoor = createMasterDoor();
        masterDoor.setCustomDoorDrawable(new RegionDrawable(0, 0, 0, 0, gameAtlas.findRegion("masterDoor1")));
        masterDoor.setColor(Color.BLUE);
        masterDoor.getBounds().setPosition(b.getX() + 1000, b.getY());
        masterDoor.setLockedDrawable(new DefaultDoorLockedDrawable(masterDoor, gameAtlas));
        floor2Section1.addEntity(masterDoor);

        puzzleView = new WordSearchPuzzleView(gameScreen);
    }

    @Override
    protected void createLogic() {
        floor1Section1ButtonSwitch1.setPressedCommand(new UnlockDoorCommand(floor1Door1));
        floor1Door1.setOpenDoorCommand(new SwitchFloorSectionCommand(this, floor2Section1));
        puzzleView.setCompleteCommand(new HelloWorldCommand());
        floor2Door1.setOpenDoorCommand(new ShowViewCommand(gameScreen, puzzleView));
//        floor2Door1.setOpenDoorCommand(new MultiCommand(
//                new SwitchFloorSectionCommand(this, floor1Section1),
//                new CloseDoorCommand(floor2Door1)));

        getEventManager().subscribe(Level.SWITCH_FLOOR_SECTION_EVENT, new EventListener<Level>() {
            @Override
            public void update(int type, Level level) {
                if (type == Level.SWITCH_FLOOR_SECTION_EVENT) {
                    if (!masterDoor.isUnlocked()) {
//                        new UnlockDoorCommand(masterDoor).execute();
                    }
                }
            }
        });
    }

}
