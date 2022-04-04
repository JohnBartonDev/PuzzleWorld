package com.john.jam.puzzles;

import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.screens.gamescreen.View;
import com.john.jam.utils.Command;

public class ShowViewCommand implements Command {

    private View view;
    private GameScreen gameScreen;

    public ShowViewCommand(GameScreen gameScreen, View view) {
        this.gameScreen = gameScreen;
        this.view = view;
    }

    @Override
    public void execute() {
        gameScreen.showView(view);
    }
}
