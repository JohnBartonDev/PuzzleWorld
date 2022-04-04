package com.john.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.john.jam.UndergroundGame;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.utils.Constants;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class SplashScreen extends Screen {

    private boolean isMinimumShowDurationOver;
    private boolean areAssetsLoaded;
    private float timer;
    private final float minimumShowDuration = 3f;
    private Texture loadingTextTexture;

    public SplashScreen(UndergroundGame game) {
        super(game);
        game.setClearColor(Constants.YELLOW);
        loadingTextTexture = new Texture(Gdx.files.internal("loadingText.png"));
    }

    @Override
    public void show() {
        super.show();

        AssetManager manager = game.getAssetManager();
        manager.load(Constants.GAME_ATLAS, TextureAtlas.class);
    }

    @Override
    public void dispose() {
        super.dispose();
        loadingTextTexture.dispose();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!isMinimumShowDurationOver && (timer += delta) > minimumShowDuration) {
            isMinimumShowDurationOver = true;
        }

        if (!areAssetsLoaded && game.getAssetManager().update()) {
            areAssetsLoaded = true;
            game.createShapeDrawer(game.getAssetManager().get(Constants.GAME_ATLAS, TextureAtlas.class).findRegion("pixel"));
        }

        if (isMinimumShowDurationOver && areAssetsLoaded) {
            game.switchScreen(new MainMenuScreen(game), true);
        }
    }

    @Override
    public void render(Batch batch, ShapeDrawer shapeDrawer) {
        super.render(batch, shapeDrawer);

        float width = loadingTextTexture.getWidth() * 0.5f;
        float height = loadingTextTexture.getHeight() * 0.5f;

        float x = (Constants.GAME_WIDTH - width) * 0.5f;
        float y = (Constants.GAME_HEIGHT - height) * 0.5f;
        batch.draw(loadingTextTexture, x, y, width, height);

    }
}
