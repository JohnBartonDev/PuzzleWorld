package com.john.jam.screens.gamescreen;

import com.john.jam.levels.Level;
import com.john.jam.utils.CallbackEvent;

public class LevelCompleteCallback implements CallbackEvent<Level> {

    private GameScreen gameScreen;

    public LevelCompleteCallback(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void onCallback(Level o) {
        gameScreen.levelComplete();
    }
}
