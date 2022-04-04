package com.john.jam.entity.switches;

import com.john.jam.utils.Command;

public class ToggleToggleSwitchCommand implements Command {

    private ToggleSwitch toggleSwitch;

    public ToggleToggleSwitchCommand(ToggleSwitch toggleSwitch) {
        this.toggleSwitch = toggleSwitch;
    }

    @Override
    public void execute() {
        if (toggleSwitch != null) toggleSwitch.toggle();
    }
}
