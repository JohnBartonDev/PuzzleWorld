package com.john.jam.entity.switches;

import com.john.jam.utils.Command;

public class UnrestrictButtonSwitchCommand implements Command {

    private ButtonSwitch buttonSwitch;

    public UnrestrictButtonSwitchCommand(ButtonSwitch buttonSwitch) {
        this.buttonSwitch = buttonSwitch;
    }

    @Override
    public void execute() {
        buttonSwitch.restrict(false);
    }
}
