package com.john.jam.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.UndergroundGame;
import com.john.jam.entity.door.Door;
import com.john.jam.levels.commands.LevelOverCommand;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.screens.gamescreen.LevelCompleteCallback;
import com.john.jam.utils.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class Level extends CustomInputMultiplexer {

    public static final int INIT_EVENT = 1 << 1;
    public static final int SWITCH_FLOOR_SECTION_EVENT = 1 << 2;

    public static final int LEVEL_HEIGHT = 320;

    protected Floor floor1;
    protected Floor floor2;
    protected FloorSection currentFloorSection;
    protected Door masterDoor;
    protected EventManager<Level> eventManager;
//    private UndergroundGame game;
    private LevelCompleteCallback levelCompleteCallback;
    protected GameScreen gameScreen;

    public Level(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
//        this.levelCompleteCallback = levelCompleteCallback;
        levelCompleteCallback = new LevelCompleteCallback(gameScreen);
        floor1 = new Floor(0, Constants.STATUS_HUD_HEIGHT);
        floor2 = new Floor(0, floor1.getY() + LEVEL_HEIGHT);
        eventManager = new EventManager(INIT_EVENT, SWITCH_FLOOR_SECTION_EVENT);

        masterDoor = new Door(0, 0);

        createSections(gameScreen.getGame());
        createLogic();
        init();
    }

    public void init() {
        currentFloorSection = floor1.getSections().first();
        eventManager.notify(INIT_EVENT, this);
        masterDoor.setOpenDoorCommand(new LevelOverCommand(this));
    }

    public void showDisabledOverlay(boolean drawDisabledOverlay) {
        floor1.showDisabledOverlay(drawDisabledOverlay);
        floor2.showDisabledOverlay(drawDisabledOverlay);
    }

    public void levelComplete() {
        levelCompleteCallback.onCallback(this);
    }

    public EventManager getEventManager() {
        return eventManager;
    }

//    protected Door createMasterDoor() {
//        masterDoor = new Door(0, 0);
//        masterDoor.setColor(Color.RED);
//        return masterDoor;
//    }

    public void draw(Batch batch, ShapeDrawer shapeDrawer) {
        floor1.draw(batch, shapeDrawer);
        floor2.draw(batch, shapeDrawer);
    }

    public void debug(ShapeDrawer shapeDrawer) {
        floor1.debug(shapeDrawer);
        floor2.debug(shapeDrawer);
    }

    public void switchFloorSection(FloorSection section) {
        if (currentFloorSection.equals(section)) return;

        currentFloorSection.setDisabled(true);
        currentFloorSection = section;
        currentFloorSection.setDisabled(false);

        eventManager.notify(SWITCH_FLOOR_SECTION_EVENT, this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Touchable e = Utils.getTouchedEntity(screenX, screenY, currentFloorSection.getEntities());

        if (e != null) {
            e.touched();
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    protected FloorSection createSection(int segments, boolean disabled, Floor floor) {
        if (floor.getSegments() == 4) throw new RuntimeException("You can't have more than 4 segments. You know this.");

        FloorSection lastSection = floor.getSections().size > 0 ? floor.getSections().peek() : null;

        float x = floor.getSegments() * FloorSection.SEGMENT_WIDTH + Floor.ELEVATOR_WIDTH;
        float y = floor.getY() + Floor.WALL_THICKNESS;
        float width = FloorSection.SEGMENT_WIDTH * segments - Floor.WALL_THICKNESS;

        FloorSection section = new FloorSection(x, y, width, segments, disabled);
        floor.addSection(section);
        floor.createWall(x + width);
        addProcessor(section);

        return section;
    }

    protected abstract void createSections(UndergroundGame game);
    protected abstract void createLogic();
}
