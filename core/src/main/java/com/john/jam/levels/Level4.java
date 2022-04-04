package com.john.jam.levels;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.john.jam.UndergroundGame;
import com.john.jam.entity.Drawable;
import com.john.jam.entity.RegionDrawable;
import com.john.jam.entity.door.Commands.CloseDoorCommand;
import com.john.jam.entity.door.Commands.LockDoorCommand;
import com.john.jam.entity.door.Commands.UnlockDoorCommand;
import com.john.jam.entity.door.DefaultDoorLockedDrawable;
import com.john.jam.entity.door.Door;
import com.john.jam.entity.switches.ButtonSwitch;
import com.john.jam.entity.switches.ToggleSwitch;
import com.john.jam.entity.switches.ToggleSwitchDrawable;
import com.john.jam.entity.switches.ToggleToggleSwitchCommand;
import com.john.jam.levels.commands.CommandSelectorCommand;
import com.john.jam.levels.commands.MultiCommand;
import com.john.jam.levels.commands.PrintCommand;
import com.john.jam.levels.commands.SwitchFloorSectionCommand;
import com.john.jam.puzzles.PasswordView;
import com.john.jam.puzzles.ShowViewCommand;
import com.john.jam.screens.gamescreen.GameScreen;
import com.john.jam.screens.gamescreen.View;
import com.john.jam.utils.Command;
import com.john.jam.utils.Constants;
import com.john.jam.utils.EventListener;

public class Level4 extends Level {

    private final int buttonSwitchSize = 30;
    private final float padding = 16;

    private FloorSection floor1_section1;
    private FloorSection floor1_section2;
    private FloorSection floor1_section3;
    private FloorSection floor2_section1;
    private FloorSection floor2_section2;
    private FloorSection floor2_section3;

    private Door floor1_section1_door1;
    private Door floor1_section1_door2;
    private Door floor1_section2_door1;
    private Door floor1_section3_door1;
    private Door floor2_section1_door1;
    private Door floor2_section2_door1;
    private Door floor2_section3_door1;

    private ToggleSwitch floor2_section1_power_toggle;
    private ToggleSwitch floor1_section3_power_toggle;
//    private ToggleSwitch floor1_section2_power_toggle;

    private ButtonSwitch floor1_section2_invert_power_button;

    private CommandSelectorCommand floor1_section1_door2_command_selector_command;
    private CommandSelectorCommand floor1_section2_door1_command_selector_command;
    private CommandSelectorCommand floor2_section2_door1_command_selector_command;

    public Level4(GameScreen gameScreen) {
        super(gameScreen);
    }

    @Override
    protected void createSections(UndergroundGame game) {
        TextureAtlas gameAtlas = game.getAssetManager().get(Constants.GAME_ATLAS);

        floor1_section1 = createSection(2, false, floor1);
        floor1_section2 = createSection(1, true, floor1);
        floor1_section3 = createSection(1, true, floor1);

        floor2_section1 = createSection(1, true, floor2);
        floor2_section2 = createSection(2, true, floor2);
        floor2_section3 = createSection(1, true, floor2);

        floor1_section1_door2_command_selector_command = new CommandSelectorCommand();
        floor1_section2_door1_command_selector_command = new CommandSelectorCommand();
        floor2_section2_door1_command_selector_command = new CommandSelectorCommand();

        create_floor1_section1_entities(gameAtlas);
        create_floor1_section2_entities(gameAtlas);
        create_floor1_section3_entities(gameAtlas);
        create_floor2_section1_entities(gameAtlas);
        create_floor2_section2_entities();
        create_floor2_section3_entities(gameAtlas);
    }

    @Override
    protected void createLogic() {
        create_floor1_section1_logic();
        create_floor1_section2_logic();
        create_floor1_section3_logic();
        create_floor2_section1_logic();
        create_floor2_section2_logic();
    }

    private void create_floor1_section1_entities(TextureAtlas gameAtlas) {
        Rectangle b = floor1_section1.getBounds();

        floor1_section1_door1 = new Door(b.getX() + padding, b.getY());
        floor1_section1_door1.unlock();
        floor1_section1.addEntity(floor1_section1_door1);

        floor1_section1_door2 = new Door(b.getX() + b.getWidth() - Door.DOOR_WIDTH - padding, b.getY());
        floor1_section1_door2.unlock();
        floor1_section1_door2.setLockedDrawable(new DefaultDoorLockedDrawable(floor1_section1_door2, gameAtlas));
        floor1_section1.addEntity(floor1_section1_door2);
    }

    private void create_floor1_section2_entities(TextureAtlas gameAtlas) {
        Rectangle b = floor1_section2.getBounds();

        floor1_section2_door1 = new Door(b.getX() + padding, b.getY());
        floor1_section2_door1.unlock();
        floor1_section2.addEntity(floor1_section2_door1);

//        float width = 30;
        float height = 60;
        float y = (b.getHeight() - buttonSwitchSize) * 0.5f;
        floor1_section2_invert_power_button = new ButtonSwitch(b.getX() + 200, b.getY() + y, buttonSwitchSize, buttonSwitchSize);
//        floor1_section2_power_toggle = new ToggleSwitch(b.getX() + 200, b.getY() + y, width, height);
        floor1_section2.addEntity(floor1_section2_invert_power_button);

        Drawable d = new RegionDrawable(0, 0, 0, 0, gameAtlas.findRegion("pinkButtonSwitch"));
        d.getBounds().set(floor1_section2_invert_power_button.getBounds());
        floor1_section2_invert_power_button.setDrawableEntity(d);
    }

    private void create_floor1_section3_entities(TextureAtlas gameAtlas) {
        Rectangle b = floor1_section3.getBounds();

        floor1_section3_door1 = new Door(b.getX() + b.getWidth() - Door.DOOR_WIDTH - padding, b.getY());
        floor1_section3_door1.unlock();
        floor1_section3.addEntity(floor1_section3_door1);

        float width = 30;
        float height = 60;
        float y = (b.getHeight() - height) * 0.5f;
        floor1_section3_power_toggle = new ToggleSwitch(b.getX() + padding + 50, b.getY() + y, width, height);
        floor1_section3.addEntity(floor1_section3_power_toggle);


        Drawable s0Drawable = new RegionDrawable(0, 0, 0, 0, gameAtlas.findRegion("toggle1"));
        Drawable s1Drawable = new RegionDrawable(0, 0, 0, 0, gameAtlas.findRegion("toggle0"));

        s0Drawable.getBounds().set(floor1_section3_power_toggle.getBounds());
        s1Drawable.getBounds().set(floor1_section3_power_toggle.getBounds());

        Drawable d = new ToggleSwitchDrawable(floor1_section3_power_toggle, s0Drawable, s1Drawable, null);

        floor1_section3_power_toggle.setDrawable(d);
    }

    private void create_floor2_section1_entities(TextureAtlas gameAtlas) {
        Rectangle b = floor2_section1.getBounds();

        floor2_section1_door1 = new Door(b.getX() + padding, b.getY());
        floor2_section1_door1.unlock();
        floor2_section1.addEntity(floor2_section1_door1);

        float width = 30;
        float height = 60;
        float xOff = b.getWidth() - Door.DOOR_WIDTH - padding - 8;
        xOff = (xOff - width) * 0.5f;
        float y = (b.getHeight() - height) * 0.5f;
        floor2_section1_power_toggle = new ToggleSwitch(b.getX() + padding + Door.DOOR_WIDTH + 8 + xOff, b.getY() + y, width, height);
        floor2_section1.addEntity(floor2_section1_power_toggle);

        Drawable s0Drawable = new RegionDrawable(0, 0, 0, 0, gameAtlas.findRegion("toggle1"));
        Drawable s1Drawable = new RegionDrawable(0, 0, 0, 0, gameAtlas.findRegion("toggle0"));

        s0Drawable.getBounds().set(floor2_section1_power_toggle.getBounds());
        s1Drawable.getBounds().set(floor2_section1_power_toggle.getBounds());

//        Drawable labelDrawable = new RegionDrawable(0, 0, 0, 0, "powerLabel");

        Drawable d = new ToggleSwitchDrawable(floor2_section1_power_toggle, s0Drawable, s1Drawable, null);
        floor2_section1_power_toggle.setDrawable(d);
    }

    private void create_floor2_section2_entities() {
        Rectangle b = floor2_section2.getBounds();

        float x = (b.getWidth() - Door.DOOR_WIDTH) * 0.5f;
        floor2_section2_door1 = new Door(b.getX() + x, b.getY());
        floor2_section2_door1.unlock();
        floor2_section2.addEntity(floor2_section2_door1);
    }

    private void create_floor2_section3_entities(TextureAtlas gameAtlas) {
        Rectangle b = floor2_section3.getBounds();

        float x = (b.getWidth() -  Door.DOOR_WIDTH) * 0.5f;
        masterDoor.getBounds().setPosition(b.getX() + x, b.getY());
        masterDoor.setCustomDoorDrawable(new RegionDrawable(gameAtlas.findRegion("masterDoor4")));
        floor2_section3.addEntity(masterDoor);
    }

    private void create_floor1_section1_logic() {
        floor1_section1_door1.setOpenDoorCommand(new MultiCommand(
                new CloseDoorCommand(floor1_section1_door1),
                new SwitchFloorSectionCommand(this, floor2_section1)));

        floor1_section1_door2_command_selector_command.addCommand("goto_floor2_section1", new MultiCommand(
                new CloseDoorCommand(floor1_section1_door2),
                new SwitchFloorSectionCommand(this, floor2_section1)));

        floor1_section1_door2_command_selector_command.addCommand("goto_floor1_section2", new MultiCommand(
                new CloseDoorCommand(floor1_section1_door2),
                new SwitchFloorSectionCommand(this, floor1_section2)));

        floor1_section1_door2.setOpenDoorCommand(floor1_section1_door2_command_selector_command);
    }

    private void create_floor2_section1_logic() {
//        floor1_section1_door2_command_selector_command.addCommand("secret room", new MultiCommand(
//                new CloseDoorCommand(floor1_section1_door2),
//                new SwitchFloorSectionCommand(this, floor1_section2)));

        Command goto_floor1_section1_command = new MultiCommand(
                new CloseDoorCommand(floor2_section1_door1),
                new SwitchFloorSectionCommand(this, floor1_section1));

        floor2_section1_door1.setOpenDoorCommand(goto_floor1_section1_command);

        Level level = this;
        floor2_section1_power_toggle.getEventManager().subscribe(ToggleSwitch.ENTER_STATE_1_FLAG, new EventListener() {
            @Override
            public void update(int type, Object o) {
                new UnlockDoorCommand(floor1_section1_door2).execute();

                if (floor1_section3_power_toggle.isFirstState()) {
                    floor1_section1_door2_command_selector_command.setCommand("goto_floor2_section1");
                }
                else {
                    floor1_section1_door2_command_selector_command.setCommand("goto_floor1_section2");
                }

                floor2_section1_door1.setOpenDoorCommand(goto_floor1_section1_command);
                floor1_section2_door1_command_selector_command.setCommand("goto_floor1_section1");
            }
        });

        floor2_section1_power_toggle.getEventManager().subscribe(ToggleSwitch.ENTER_STATE_2_FLAG, new EventListener() {
            @Override
            public void update(int type, Object o) {
                new LockDoorCommand(floor1_section1_door2).execute();

                if (floor1_section3_power_toggle.isFirstState()) {
                    floor1_section2_door1_command_selector_command.setCommand("goto_floor2_section2");
                    System.out.println("here");
                }
                else {
                    floor1_section2_door1_command_selector_command.setCommand("goto_floor1_section1");
                }

                floor2_section1_door1.setOpenDoorCommand(new MultiCommand(
                        new CloseDoorCommand(floor2_section1_door1),
//                        new LockDoorCommand(floor1_section1_door2),
                        new SwitchFloorSectionCommand(level, floor1_section3)));
            }
        });
    }

    private void create_floor1_section3_logic() {
        Command goto_floor2_section1_command = new MultiCommand(
                new CloseDoorCommand(floor1_section3_door1),
                new SwitchFloorSectionCommand(this, floor2_section1));

        floor1_section3_door1.setOpenDoorCommand(goto_floor2_section1_command);

        Level level = this;
        floor1_section3_power_toggle.getEventManager().subscribe(ToggleSwitch.ENTER_STATE_1_FLAG, new EventListener() {
            @Override
            public void update(int type, Object o) {
                floor1_section3_door1.setOpenDoorCommand(goto_floor2_section1_command);
            }
        });

        floor1_section3_power_toggle.getEventManager().subscribe(ToggleSwitch.ENTER_STATE_2_FLAG, new EventListener() {
            @Override
            public void update(int type, Object o) {
                floor1_section3_door1.setOpenDoorCommand(new MultiCommand(
                        new CloseDoorCommand(floor1_section3_door1),
                        new SwitchFloorSectionCommand(level, floor1_section1)));
            }
        });

    }

    private void create_floor1_section2_logic() {
        Command goto_floor1_section1_command = new MultiCommand(
                new CloseDoorCommand(floor1_section2_door1),
                new SwitchFloorSectionCommand(this, floor1_section1));

        Command goto_floor2_section2_command = new MultiCommand(
                new CloseDoorCommand(floor1_section2_door1),
                new SwitchFloorSectionCommand(this, floor2_section2));

        floor1_section2_door1_command_selector_command.addCommand("goto_floor1_section1", goto_floor1_section1_command);
        floor1_section2_door1_command_selector_command.addCommand("goto_floor2_section2", goto_floor2_section2_command);

        floor1_section2_door1.setOpenDoorCommand(floor1_section2_door1_command_selector_command);

        floor1_section2_invert_power_button.setPressedCommand(new MultiCommand(
                new ToggleToggleSwitchCommand(floor1_section3_power_toggle),
                new ToggleToggleSwitchCommand(floor2_section1_power_toggle)));
    }

    private void create_floor2_section2_logic() {
//        floor2_section2_door1.setOpenDoorCommand(new MultiCommand(
//                new CloseDoorCommand(floor2_section2_door1),
//                new SwitchFloorSectionCommand(this, floor2_section1)));
//
//        floor2_section2_door1_command_selector_command.addCommand("goto_floor2_section1", new MultiCommand(
//                new CloseDoorCommand(floor2_section2_door1),
//                new SwitchFloorSectionCommand(this, floor2_section1)));
//
//        floor2_section2_door1_command_selector_command.addCommand("solvePasswordView", new PrintCommand("Solve puzzle"));
        floor2_section2_door1.setOpenDoorCommand(new MultiCommand(
                new CloseDoorCommand(floor2_section2_door1),
                new UnlockDoorCommand(masterDoor),
                new SwitchFloorSectionCommand(this, floor2_section3)));
    }

}
