package com.john.jam.screens.gamescreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.john.jam.*;
import com.john.jam.levels.*;
import com.john.jam.levels.level1.Level1;
import com.john.jam.screens.MainMenuScreen;
import com.john.jam.screens.Screen;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameScreen extends Screen {

    private boolean showStatusHud;
    private int levelIdx = -1;
    private Level currentLevel;
    private StatusHud statusHud;
    private View currentView;

    public GameScreen(UndergroundGame game) {
        super(game);

        game.setClearColor(Color.WHITE);

        statusHud = new StatusHud();
        showView(new NextLevelView(this, true));
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public StatusHud getStatusHud() {
        return statusHud;
    }

    public void showStatusHud() {
        showStatusHud = true;
    }

    public void levelComplete() {
        inputMultiplexer.removeProcessor(currentLevel);
        showView(new NextLevelView(this, false));
    }

    public void nextLevel() {
        levelIdx++;

//        levelIdx = 3;

        switch (levelIdx) {
            case 0:
                currentLevel = new Level1(this);
                break;
            case 1:
                currentLevel = new Level2(this);
                break;
            case 2:
                currentLevel = new Level3(this);
                break;
            case 3:
                currentLevel = new Level4(this);
                break;
            default:
                game.switchScreen(new MainMenuScreen(game), true);
                break;
        }

        inputMultiplexer.addProcessor(currentLevel);
        currentLevel.restrictInput(true);
    }

    public void showView(View view) {
        currentView = view;
        inputMultiplexer.addProcessor(view);
        currentView.show();
    }

    public void removeView() {
        if (currentView == null) return;

        inputMultiplexer.removeProcessor(currentView);
        currentView.hide();
        currentView = null;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (currentView != null) currentView.update(delta);
    }

    @Override
    public void render(Batch batch, ShapeDrawer shapeDrawer) {
        if (showStatusHud) statusHud.debug(shapeDrawer);

        if (currentLevel != null) {
            currentLevel.draw(batch, shapeDrawer);
            currentLevel.debug(shapeDrawer);
        }
        if (currentView != null) currentView.render(batch, shapeDrawer);
    }
}
