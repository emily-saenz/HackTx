package shapes1_5_5.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chris Cavazos on 10/18/2018.
 */
public class Screen {
    public static Vector2 size = new Vector2();
    public Screen(){
        size.set(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    public static void setSize(Vector2 vector2) {
        size.set(vector2);
        Gdx.graphics.setWindowedMode((int)vector2.x,(int)vector2.y);
    }
}
