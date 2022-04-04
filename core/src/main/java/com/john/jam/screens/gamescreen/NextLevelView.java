package com.john.jam.screens.gamescreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.UndergroundGame;
import com.john.jam.utils.Constants;
import com.vabrant.actionsystem.actions.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class NextLevelView extends View implements Colorable {

    private boolean init;
    private Color color;
    private GameScreen gameScreen;

    public NextLevelView(GameScreen gameScreen, boolean init) {
        super(gameScreen);
        this.gameScreen = gameScreen;
        this.init = init;
        color = new Color(0xFFFFFF00);
    }

    @Override
    public void setColor(Color color) {
        this.color.set(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void show() {
        super.show();

        GroupAction inGroup = GroupAction.obtain()
                .sequence()
                .add(DelayAction.delay(1))
                .add(ColorAction.changeAlpha(this, 1, 1f, null))
                .add(RunnableAction.runnable(new Runnable() {
                    @Override
                    public void run() {
                        gameScreen.nextLevel();
                        if (init) gameScreen.showStatusHud();
                    }
                }));

        GroupAction outGroup = GroupAction.obtain()
                .sequence()
                .add(DelayAction.delay(2))
                .add(ColorAction.changeAlpha(this, 0, 0.8f, null))
                .add(RunnableAction.runnable(new Runnable() {
                    @Override
                    public void run() {
                        gameScreen.removeView();
                        gameScreen.getCurrentLevel().restrictInput(false);
                        gameScreen.getCurrentLevel().showDisabledOverlay(true);
                    }
                }));

        actionManager.addAction(GroupAction.sequence(inGroup, outGroup));
    }

    @Override
    public void render(Batch batch, ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT, color);
    }

}
