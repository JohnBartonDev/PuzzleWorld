package com.john.jam.utils;

import com.badlogic.gdx.utils.Array;

public class CustomInputMultiplexer extends CustomInput {

    private Array<CustomInput> processors;

    public CustomInputMultiplexer() {
        processors = new Array<>();
    }

    public void addProcessor(CustomInput input) {
        processors.add(input);
    }

    public void removeProcessor(CustomInput input) {
        processors.removeValue(input, false);
    }

    @Override
    public boolean keyDown(int keycode) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.keyDown(keycode)) return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.keyUp(keycode)) return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.keyTyped(character)) return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.touchDown(screenX, screenY, pointer, button)) return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.touchUp(screenX, screenY, pointer, button)) return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.touchDragged(screenX, screenY, pointer)) return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (int i = 0, n = processors.size; i < n; i++) {
            CustomInput input = processors.get(i);
            if (input.isInputRestricted()) continue;
            if (input.mouseMoved(screenX, screenY)) return true;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        for (int i = 0, n = processors.size; i < n; i++)
            if (processors.get(i).scrolled(amountX, amountY)) return true;
        return false;
    }
}
