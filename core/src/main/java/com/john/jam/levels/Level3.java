package com.john.jam.levels;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.UndergroundGame;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.entity.door.Commands.CloseDoorCommand;
import com.john.jam.entity.door.Commands.UnlockDoorCommand;
import com.john.jam.entity.door.DefaultDoorLockedDrawable;
import com.john.jam.entity.door.Door;
import com.john.jam.entity.switches.ButtonSwitch;
import com.john.jam.entity.switches.UnrestrictButtonSwitchCommand;
import com.john.jam.levels.commands.MultiCommand;
import com.john.jam.levels.commands.SwitchFloorSectionCommand;
import com.john.jam.puzzles.PuzzleView;
import com.john.jam.puzzles.ShowViewCommand;
import com.john.jam.puzzles.wordsearch.WordSearchPuzzleView;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.utils.Constants;

public class Level3 extends Level {

    private FloorSection floor1Section1;
    private FloorSection floor2Section1;
    private FloorSection floor2Section2;
    private Door floor1Section1DoorToFloor2Section1;
    private Door floor1Section1DoorToFloor2Section2;
    private Door floor2Section1DoorToFloor1Section1;
    private Door floor2Section2DoorToFloor1Section1;
    private ButtonSwitch floor1Section1PuzzleSwitch;
    private ButtonSwitch floor2Section1UnlockPuzzleSwitch;
    private PuzzleView puzzleView;

    public Level3(GameScreen gameScreen) {
        super(gameScreen);
    }

    @Override
    protected void createSections(UndergroundGame game) {
        TextureAtlas gameAtlas = game.getAssetManager().get(Constants.GAME_ATLAS);

        floor1Section1 = createSection(4, false, floor1);
        floor2Section1 = createSection(3, true, floor2);
        floor2Section2 = createSection(1, true, floor2);

        float x = 0;
        float y = 0;
        Rectangle b = null;
        Drawable d = null;

        b = floor1Section1.getBounds();
        x = (b.getWidth() * 0.25f);
        x = (x - Door.DOOR_WIDTH) * 0.5f;
        floor1Section1DoorToFloor2Section1 = new Door(x, b.getY());
        floor1Section1.addEntity(floor1Section1DoorToFloor2Section1);

        x = (b.getWidth() * 0.25f);
        x = b.getWidth() - ((x - Door.DOOR_WIDTH) * 0.5f);
        floor1Section1DoorToFloor2Section2 = new Door(x, b.getY());
        floor1Section1.addEntity(floor1Section1DoorToFloor2Section2);

        x = (b.getWidth() - ButtonSwitch.SIZE) * 0.5f;
        y = (b.getHeight() - ButtonSwitch.SIZE) * 0.5f;
        floor1Section1PuzzleSwitch = new ButtonSwitch(b.getX() + x, b.getY() + y);
        floor1Section1PuzzleSwitch.restrict(true);
        floor1Section1.addEntity(floor1Section1PuzzleSwitch);

        b = floor2Section1.getBounds();
        floor2Section1DoorToFloor1Section1 = new Door(floor1Section1DoorToFloor2Section1.getBounds().getX(), b.getY());
        floor2Section1.addEntity(floor2Section1DoorToFloor1Section1);

        x = (b.getWidth() - ButtonSwitch.SIZE) * 0.5f;
        y = (b.getHeight() - ButtonSwitch.SIZE) * 0.5f;
        floor2Section1UnlockPuzzleSwitch = new ButtonSwitch(b.getX() + x, b.getY() + y);
        floor2Section1.addEntity(floor2Section1UnlockPuzzleSwitch);

        b = floor2Section2.getBounds();
        x = (b.getWidth() * 0.5f);
        x = (x - Door.DOOR_WIDTH) * 0.5f;
        floor2Section2DoorToFloor1Section1 = new Door(b.getX() + x, b.getY());
        floor2Section2.addEntity(floor2Section2DoorToFloor1Section1);

        x = (b.getWidth() * 0.5f);
        x = b.getWidth() - ((x - Door.DOOR_WIDTH) * 0.5f) - Door.DOOR_WIDTH;
        masterDoor.getBounds().setPosition(b.getX() + x, b.getY());
        masterDoor.setLockedDrawable(new DefaultDoorLockedDrawable(masterDoor, gameAtlas));
        masterDoor.setCustomDoorDrawable(new RegionDrawable(gameAtlas.findRegion("masterDoor4")));
        floor2Section2.addEntity(masterDoor);

        puzzleView = new WordSearchPuzzleView(gameScreen);
    }

    @Override
    protected void createLogic() {
        floor1Section1DoorToFloor2Section1.unlock();
        floor1Section1DoorToFloor2Section2.unlock();
        floor2Section1DoorToFloor1Section1.unlock();
        floor2Section2DoorToFloor1Section1.unlock();

        floor1Section1DoorToFloor2Section1.setOpenDoorCommand(new MultiCommand(
                new SwitchFloorSectionCommand(this, floor2Section1),
                new CloseDoorCommand(floor1Section1DoorToFloor2Section1)));
        floor1Section1DoorToFloor2Section2.setOpenDoorCommand(new MultiCommand(
                new SwitchFloorSectionCommand(this, floor2Section2),
                new CloseDoorCommand(floor1Section1DoorToFloor2Section2)));
        floor2Section1DoorToFloor1Section1.setOpenDoorCommand(new MultiCommand(
                new SwitchFloorSectionCommand(this, floor1Section1),
                new CloseDoorCommand(floor2Section1DoorToFloor1Section1)));
        floor2Section2DoorToFloor1Section1.setOpenDoorCommand(new MultiCommand(
                new SwitchFloorSectionCommand(this, floor1Section1),
                new CloseDoorCommand(floor2Section2DoorToFloor1Section1)));

        floor2Section1UnlockPuzzleSwitch.setPressedCommand(new UnrestrictButtonSwitchCommand(floor1Section1PuzzleSwitch));
        floor1Section1PuzzleSwitch.setPressedCommand(new ShowViewCommand(gameScreen, puzzleView));
        puzzleView.setCompleteCommand(new UnlockDoorCommand(masterDoor));
    }
}
