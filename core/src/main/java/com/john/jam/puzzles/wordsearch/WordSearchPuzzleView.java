package com.john.jam.puzzles.wordsearch;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.puzzles.PuzzleView;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.utils.Command;
import com.john.jam.utils.Constants;
import com.john.jam.utils.Utils;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class WordSearchPuzzleView extends PuzzleView {

    private static final int LEFT = 1 << 1;
    private static final int RIGHT = 1 << 2;
    private static final int UP = 1 << 3;
    private static final int DOWN = 1 << 4;
    private static final int LEFT_UP_DIAGONAL = 1 << 5;
    private static final int RIGHT_UP_DIAGONAL = 1 << 6;
    private static final int LEFT_DOWN_DIAGONAL = 1 << 7;
    private static final int RIGHT_DOWN_DIAGONAL = 1 << 8;
    private static final int BOTTOM_LEFT_CORNER = 1 << 9;
    private static final int BOTTOM_RIGHT_CORNER = 1 << 10;
    private static final int TOP_RIGHT_CORNER = 1 << 11;
    private static final int TOP_LEFT_CORNER = 1 << 12;
    private static final int HORIZONTAL = 1 << 12;
    private static final int VERTICAL = 1 << 13;
    private static final int DIAGONAL = 1 << 14;

    private String[] words = {"love", "card", "java"};

    private boolean isComplete;
    private String wordToFind;
    private final int gapSize = 6;
    private static final int GRID_SIZE = 4;
    private static final int TILE_SIZE = 50;
    private Array<Tile> wordToFindTiles;
    private int[] lettersIndex;
    private Array<Tile> searchTiles;
    private Rectangle tmpBounds;
    private Array<RegionDrawable> letters;
    private Rectangle checkBounds;

    public WordSearchPuzzleView(GameScreen gameScreen) {
        super(gameScreen);

        TextureAtlas gameAtlas = gameScreen.getGame().getAssetManager().get(Constants.GAME_ATLAS);

        lettersIndex = new int[4];
        wordToFind = words[MathUtils.random(0, words.length - 1)];
        wordToFindTiles = new Array<>();

        findPathForWord();

        searchTiles = new Array<>(GRID_SIZE * GRID_SIZE + 4);
        tmpBounds = new Rectangle(0, 0, TILE_SIZE, TILE_SIZE);

        Rectangle puzzleBounds = bounds;
        float maxWidth = (GRID_SIZE * TILE_SIZE) + ((GRID_SIZE - 1) * gapSize);
        float startX = puzzleBounds.getX() + (puzzleBounds.getWidth() - maxWidth) * 0.5f;
        float y = puzzleBounds.getHeight() - 200;

        checkBounds = new Rectangle(startX, puzzleBounds.getY() + 200 - 100, 212, 80);

        for (int i = 0; i < 4; i++) {
            Tile t = new Tile(startX + (TILE_SIZE + gapSize) * i, puzzleBounds.getY() + y, TILE_SIZE, TILE_SIZE, i);
            wordToFindTiles.add(t);
            setLetter(gameAtlas, t, Character.toString(wordToFind.charAt(i)));
        }

        y = puzzleBounds.getY() + 200;
        for (int r = 0, index = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                Tile t = new Tile(startX + (TILE_SIZE + gapSize) * c, y, TILE_SIZE, TILE_SIZE, index);
                searchTiles.add(t);

                if (index == lettersIndex[0]) {
                    setLetter(gameAtlas, searchTiles.get(lettersIndex[0]), Character.toString(wordToFind.charAt(0)));
                } else if (index == lettersIndex[1]) {
                    setLetter(gameAtlas, searchTiles.get(lettersIndex[1]), Character.toString(wordToFind.charAt(1)));
                } else if (index == lettersIndex[2]) {
                    setLetter(gameAtlas, searchTiles.get(lettersIndex[2]), Character.toString(wordToFind.charAt(2)));
                } else if (index == lettersIndex[3]) {
                    setLetter(gameAtlas, searchTiles.get(lettersIndex[3]), Character.toString(wordToFind.charAt(3)));
                } else {
                    setLetter(gameAtlas, t, Input.Keys.toString(MathUtils.random(29, 54)).toLowerCase());
                }

                index++;
            }

            y += TILE_SIZE + gapSize;
        }


    }

    private void setLetter(TextureAtlas gameAtlas, Tile t, String letter) {
        TextureRegion r = gameAtlas.findRegion(letter);
        Drawable d = new RegionDrawable(0, 0, r.getRegionWidth() * 0.5f, r.getRegionHeight() * 0.5f, r);
        d.setColor(Constants.PURPLE);
        Utils.centerEntityToEntity(d.getBounds(), t.bounds);
        t.setLetterDrawable(d);
    }

    private void findPathForWord() {
        int x = 0;
        int y = 0;
        int idx = 0;
        int options = 0;

        switch (0) {
            case 0:
                //Bottom row
                x = MathUtils.random(0, 3);
                y = 0;

                //Debug
                x = 0;
                y = 0;
                idx = getTileIndex(x, y);

                options = checkForCorner(idx, options);

                if (isCorner(options)) {
//                    MathUtils.random(0, 2)
                    switch (0) {
                        case 0:
                            options |= HORIZONTAL;
                            break;
                        case 1:
                            options |= VERTICAL;
                            break;
                        case 2:
                            options |= DIAGONAL;
                            break;
                    }

                    if ((options & BOTTOM_LEFT_CORNER) > 0) {
                        options |= RIGHT | UP | RIGHT_UP_DIAGONAL;
                    } else {
                        options |= LEFT | UP | LEFT_UP_DIAGONAL;
                    }
                } else {
                    options |= UP | VERTICAL;
                }

                buildWord(idx, options);
                break;
            case 1:
                //Right column
                x = GRID_SIZE - 1;
                y = MathUtils.random(0, 3);
                break;
            case 2:
                //Top row
                x = MathUtils.random(0, 3);
                y = GRID_SIZE - 1;
                break;
            case 3:
                //Left column
                x = 0;
                y = MathUtils.random(0, 3);
                break;
        }

    }

    public void buildWord(int firstTileIdx, int options) {
        lettersIndex[0] = firstTileIdx;

        System.out.println("here");
        for (int i = 1, idx = firstTileIdx; i <= 3; i++) {
            if ((options & HORIZONTAL) > 0) {
                idx = (options & LEFT) > 0 ? idx - 1 : idx + 1;
            } else if ((options & VERTICAL) > 0) {
                idx = (options & UP) > 0 ? idx + GRID_SIZE : idx - GRID_SIZE;
            } else {

            }

            lettersIndex[i] = idx;
        }
    }

    public int checkForCorner(int idx, int options) {
        if (idx == getTileIndex(0, 0)) {
            options |= BOTTOM_LEFT_CORNER;
        } else if (idx == getTileIndex(GRID_SIZE - 1, 0)) {
            options |= BOTTOM_RIGHT_CORNER;
        } else if (idx == getTileIndex(GRID_SIZE - 1, GRID_SIZE - 1)) {
            options |= TOP_RIGHT_CORNER;
        } else if (idx == getTileIndex(0, GRID_SIZE - 1)) {
            options |= TOP_LEFT_CORNER;
        }

        return options;
    }

    private boolean isCorner(int options) {
        if ((options & BOTTOM_LEFT_CORNER) > 0 ||
                (options & BOTTOM_RIGHT_CORNER) > 0 ||
                (options & TOP_RIGHT_CORNER) > 0 ||
                (options & TOP_LEFT_CORNER) > 0) {
            return true;
        }
        return false;
    }

    private int getTileIndex(int x, int y) {
        return y * GRID_SIZE + x;
    }

    public Tile getTileAtPosition(float x, float y) {
        for (int i = 0; i < searchTiles.size; i++) {
            Tile t = searchTiles.get(i);
            if (t.bounds.contains(x, y)) return t;
        }
        return null;
    }

    private void complete() {
        isComplete = true;

        for (Tile t : searchTiles) {

            if (t.index == lettersIndex[0] ||
                    t.index == lettersIndex[1] ||
                    t.index == lettersIndex[2] ||
                    t.index == lettersIndex[3]) {
                t.letterDrawable.setColor(Constants.GREEN);
            }
            else {
                t.letterDrawable.setColor(Constants.PURPLE);
            }
        }

        if (completeCommand != null) completeCommand.execute();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isComplete) {
            Tile tile = getTileAtPosition(screenX, screenY);

            if (tile != null) {
                tile.select();
                return true;
            }

            if (checkBounds.contains(screenX, screenY)) {
                complete();
            }
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void render(Batch batch, ShapeDrawer shapeDrawer) {
        super.render(batch, shapeDrawer);

        for (Tile t : searchTiles) {
            t.draw(batch, shapeDrawer);
        }

        for (Tile t : wordToFindTiles) {
            t.draw(batch, shapeDrawer);
        }

        shapeDrawer.filledRectangle(checkBounds, Color.WHITE);
    }

    public static class Tile {

        boolean isSelected;
        int index;
        Rectangle bounds;
        Drawable letterDrawable;
        Color color = new Color(Color.WHITE);

        public Tile(float x, float y, float width, float height, int index) {
            bounds = new Rectangle(x, y, width, height);
            this.index = index;
        }

        private void select() {
            if (isSelected) {
                isSelected = false;
                letterDrawable.getColor().set(Constants.PURPLE);
            } else {
                isSelected = true;
                letterDrawable.getColor().set(Constants.RED);
            }
        }

        public void setLetterDrawable(Drawable letterDrawable) {
            this.letterDrawable = letterDrawable;
        }

        public void draw(Batch batch, ShapeDrawer shapeDrawer) {
            shapeDrawer.filledRectangle(bounds, color);
            if (letterDrawable != null) letterDrawable.draw(batch, shapeDrawer);
        }
    }
}
