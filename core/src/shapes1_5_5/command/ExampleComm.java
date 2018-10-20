package shapes1_5_5.command;

import com.badlogic.gdx.Input;
import shapes1_5_5.controllers.Xbox360Pad;

/**
 * Created by Chris Cavazos on 8/8/2016.
 */
public class ExampleComm extends Command{
    public ExampleComm(){
        name="Use";
        keyboard= Input.Keys.C;
        contB = Xbox360Pad.BUTTON_X;
    }
    @Override
    public void execute() {
        if(pressed()) {

        }
    }
}
