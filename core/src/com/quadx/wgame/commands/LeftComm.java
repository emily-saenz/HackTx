package com.quadx.wgame.commands;

import com.badlogic.gdx.Input;
import shapes1_5_5.command.Command;
import shapes1_5_5.controllers.Xbox360Pad;

import static com.quadx.wgame.state.GameState.p1;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class LeftComm extends Command {

    public LeftComm(){
        name="Use";
        keyboard= Input.Keys.A;
        contB = Xbox360Pad.BUTTON_X;
    }
    @Override
    public void execute() {
        if(pressed()) {
            p1.move(2);
        }
    }
}
