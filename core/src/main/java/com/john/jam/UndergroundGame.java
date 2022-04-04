package com.john.jam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.john.jam.screens.Screen;
import com.john.jam.screens.SplashScreen;
import space.earlygrey.shapedrawer.ShapeDrawer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class UndergroundGame extends ApplicationAdapter {

	private boolean disposeCurrentScreen;
	private boolean switchScreens;
	private Color clearColor;
	private Batch batch;
	private AssetManager assetManager;
	private ShapeDrawer shapeDrawer;
	private Screen currentScreen;
	private Screen nextScreen;

	@Override
	public void create() {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		clearColor = new Color(0xFFFFFFFF);
		currentScreen = new SplashScreen(this);
		currentScreen.show();
	}

	public void setClearColor(float r, float g, float b, float a) {
		clearColor.set(r, g, b, a);
	}

	public void setClearColor(Color color) {
		clearColor.set(color);
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public ShapeDrawer getShapeDrawer() {
		return shapeDrawer;
	}

	public Batch getBatch() {
		return batch;
	}

	public void createShapeDrawer(TextureRegion pixelRegion) {
		if (shapeDrawer == null) shapeDrawer = new ShapeDrawer(batch, pixelRegion);
	}

	@Override
	public void dispose() {
		currentScreen.dispose();
		assetManager.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	public void switchScreen(Screen screen, boolean disposeCurrentScreen) {
		switchScreens = true;
		nextScreen = screen;
		this.disposeCurrentScreen = disposeCurrentScreen;
	}

	private void switchScreens() {
		switchScreens = false;

		currentScreen.hide();
		if (disposeCurrentScreen) currentScreen.dispose();

		currentScreen = nextScreen;
		nextScreen = null;
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		currentScreen.show();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float delta = Gdx.graphics.getDeltaTime();
		currentScreen.update(delta);

		batch.setProjectionMatrix(currentScreen.getViewport().getCamera().combined);
		batch.begin();
		currentScreen.update(delta);
		currentScreen.render(batch, shapeDrawer);
		batch.end();

		if (switchScreens) switchScreens();
	}


}