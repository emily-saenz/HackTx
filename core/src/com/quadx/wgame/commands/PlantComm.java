package com.quadx.wgame.commands;

import com.badlogic.gdx.Input;
import shapes1_5_5.command.Command;
import shapes1_5_5.controllers.Xbox360Pad;

import static com.quadx.wgame.state.GameState.p1;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class PlantComm extends Command {
    public PlantComm(){
        name="Use";
        keyboard= Input.Keys.CONTROL_RIGHT;
        contB = Xbox360Pad.BUTTON_X;
    }
    @Override
    public void execute() {
        if(pressed()) {
            p1.plant();

        }
    }
}
