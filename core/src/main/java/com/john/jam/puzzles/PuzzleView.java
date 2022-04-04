package com.john.jam.puzzles;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.StatusHud;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RectangleDrawable;
import com.john.jam.levels.Floor;
import com.john.jam.levels.FloorSection;
import com.john.jam.levels.Level;
import com.john.jam.puzzles.wordsearch.Grid;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.screens.gamescreen.View;
import com.john.jam.utils.Command;
import com.john.jam.utils.Constants;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class PuzzleView extends View {

    protected Drawable titleRectangleDrawable;
    protected Drawable titleTextDrawable;
    protected Rectangle exitBounds;
    public Rectangle bounds;
    public GameScreen gameScreen;
    protected Command completeCommand;

    public PuzzleView(GameScreen gameScreen) {
        super(gameScreen);
        this.gameScreen = gameScreen;

        float height = (FloorSection.HEIGHT * 2) + (Floor.WALL_THICKNESS * 2);
        bounds = new Rectangle(0, StatusHud.HEIGHT, FloorSection.SEGMENT_WIDTH * Floor.MAX_SEGMENTS + Floor.ELEVATOR_WIDTH, height);
        exitBounds = new Rectangle(bounds.width - 50 - 10, StatusHud.HEIGHT + height - 50 - 10, 50, 50);

        titleRectangleDrawable = new RectangleDrawable((bounds.width - 350) * 0.5f, bounds.getY() + bounds.height - 100 - 20, 350, 100);
    }

    public void setCompleteCommand(Command command) {
        completeCommand = command;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void show() {
        super.show();
        Level level = gameScreen.getCurrentLevel();
        level.showDisabledOverlay(false);
        level.restrictInput(true);
    }

    @Override
    public void hide() {
        super.hide();
        Level level = gameScreen.getCurrentLevel();
        level.showDisabledOverlay(true);
        level.restrictInput(false);
    }

    public void exit() {
        gameScreen.removeView();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (exitBounds.contains(screenX, screenY)) {
            exit();
            return true;
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            exit();
            return true;
        }
        return super.keyDown(keycode);
    }

    @Override
    public void render(Batch batch, ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(bounds, Constants.BLACK_90_PERCENT);
        shapeDrawer.filledRectangle(exitBounds, Color.WHITE);

        if (titleRectangleDrawable != null) titleRectangleDrawable.draw(batch, shapeDrawer);
    }
}
