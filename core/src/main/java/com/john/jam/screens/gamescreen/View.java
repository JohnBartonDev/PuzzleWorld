package com.john.jam.screens.gamescreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.UndergroundGame;
import com.john.jam.utils.CallbackEvent;
import com.john.jam.utils.CustomInputMultiplexer;
import com.vabrant.actionsystem.actions.ActionManager;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class View extends CustomInputMultiplexer {

    protected GameScreen gameScreen;
    protected ActionManager actionManager;

    public View(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        actionManager = new ActionManager();
    }

//    public UndergroundGame getGame() {
//        return game;
//    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void hide() {
        restrictInput(true);
    }

    public void show() {
        restrictInput(false);
    }

    public void update(float delta) {
        actionManager.update(delta);
    }

    public void dispose() {
        actionManager.freeAll();
    }

    public void render(Batch batch, ShapeDrawer shapeDrawer) {}
}
