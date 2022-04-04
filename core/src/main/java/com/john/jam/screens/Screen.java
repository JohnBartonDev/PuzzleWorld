package com.john.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.john.jam.UndergroundGame;
import com.john.jam.utils.Constants;
import com.john.jam.utils.ScreenInputMultiplexer;
import com.vabrant.actionsystem.actions.ActionManager;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Screen {
    protected Viewport viewport;
    protected UndergroundGame game;
    protected ScreenInputMultiplexer inputMultiplexer;
    protected ActionManager actionManager;

    public Screen(UndergroundGame game) {
        this.game = game;
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        inputMultiplexer = new ScreenInputMultiplexer(viewport);
        actionManager = new ActionManager(30);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public ScreenInputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public UndergroundGame getGame() {
        return game;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void update(float delta) {
        actionManager.update(delta);
    }

    public void dispose() {
        actionManager.freeAll();
    }

    public void render(Batch batch, ShapeDrawer shapeDrawer) {}

    public void resume() {}

    public void pause() {}
}
