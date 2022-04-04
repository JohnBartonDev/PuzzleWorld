package com.john.jam.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenInputMultiplexer extends CustomInputMultiplexer {

    private Vector2 touch;
    private Viewport viewport;
//    private Array<CustomInput> processors;

    public ScreenInputMultiplexer(Viewport viewport) {
        this.viewport = viewport;
        touch = new Vector2();
//        processors = new Array<>();
    }

//    public void addProcessor(CustomInput input) {
//        processors.add(input);
//    }

//    @Override
//    public boolean keyDown(int keycode) {
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.keyDown(keycode)) return true;
//        }
//        return false;
//    }

//    @Override
//    public boolean keyUp(int keycode) {
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.keyUp(keycode)) return true;
//        }
//        return false;
//    }

//    @Override
//    public boolean keyTyped(char character) {
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.keyTyped(character)) return true;
//        }
//        return false;
//    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch = viewport.unproject(touch.set(screenX, screenY));
        return super.touchDown((int) touch.x, (int) touch.y, pointer, button);
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.touchDown((int) touch.x, (int) touch.y, pointer, button)) return true;
//        }
//        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch = viewport.unproject(touch.set(screenX, screenY));
        return super.touchUp((int) touch.x, (int) touch.y, pointer, button);
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.touchUp((int) touch.x, (int) touch.y, pointer, button)) return true;
//        }
//        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch = viewport.unproject(touch.set(screenX, screenY));
        return super.touchDragged((int) touch.x, (int) touch.y, pointer);
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.touchDragged((int) touch.x, (int) touch.y, pointer)) return true;
//        }
//        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        touch = viewport.unproject(touch.set(screenX, screenY));
        return super.mouseMoved((int) touch.x, (int) touch.y);
//        for (int i = 0, n = processors.size; i < n; i++) {
//            CustomInput input = processors.get(i);
//            if (input.isInputRestricted()) continue;
//            if (input.mouseMoved((int) touch.x, (int) touch.y)) return true;
//        }
//        return false;
    }

//    @Override
//    public boolean scrolled(float amountX, float amountY) {
//        for (int i = 0, n = processors.size; i < n; i++)
//            if (processors.get(i).scrolled(amountX, amountY)) return true;
//        return false;
//    }
}
