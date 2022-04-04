package com.john.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.UndergroundGame;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.utils.Constants;
import com.john.jam.utils.CustomInput;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class MainMenuScreen extends Screen {

    private Rectangle playButtonBounds;
    private TextureRegion gameTitleRegion;
    private TextureRegion playButtonRegion;
    private Texture shapeBackgroundTexture;

    public MainMenuScreen(UndergroundGame game) {
        super(game);

        game.setClearColor(Constants.YELLOW);

        shapeBackgroundTexture = new Texture(Gdx.files.internal("shapeBackground.png"));

        TextureAtlas gameAtlas = game.getAssetManager().get(Constants.GAME_ATLAS);
        gameTitleRegion = gameAtlas.findRegion("gameTitle");
        playButtonRegion = gameAtlas.findRegion("playButton");

        float x = (Constants.GAME_WIDTH - playButtonRegion.getRegionWidth()) * 0.5f;
        playButtonBounds = new Rectangle(x, 200, playButtonRegion.getRegionWidth() , playButtonRegion.getRegionHeight());

        inputMultiplexer.addProcessor(new CustomInput() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (playButtonBounds.contains(screenX, screenY)) {
                    game.switchScreen(new GameScreen(game), true);
                    return true;
                }

                return super.touchDown(screenX, screenY, pointer, button);
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        shapeBackgroundTexture.dispose();
    }

    @Override
    public void render(Batch batch, ShapeDrawer shapeDrawer) {
        batch.draw(shapeBackgroundTexture, 0, 0);

        float x = (Constants.GAME_WIDTH - gameTitleRegion.getRegionWidth()) * 0.5f;

        batch.draw(gameTitleRegion, x, 500);
        batch.draw(playButtonRegion, playButtonBounds.getX(), playButtonBounds.getY());

    }
}
