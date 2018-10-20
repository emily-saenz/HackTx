package shapes1_5_5.states;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import shapes1_5_5.gui.Screen;

/**
 * Created by Brent on 6/26/2015.
 */
@SuppressWarnings("ALL")
public abstract class State implements ControllerListener {

    public static Screen scr = new Screen();
    public static OrthographicCamera cam;
    public static Controller controller;
    private Vector3 mouse;
    public static GameStateManager gsm;
    public static float viewX;
    public static float viewY;
    protected static Vector2 view = new Vector2();


    protected State(GameStateManager gsm){
        State.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(true);
        mouse = new Vector3();
    }

    public static Vector2 getView() {
        return view;
    }

    void handleInput(){

    }
    public static void setView(Vector2 v){
        view.set(v);
        viewX=v.x;
        viewY=v.y;
    }
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public static float scrVx(float percent){
        return view.x+(scr.size.x*percent);
    }
    public static float scrVy(float percent){
        return view.y+(scr.size.y*percent);
    }
    public static Vector2 scrV(float px, float py){
        return new Vector2(scrVx(px),scrVy(py));
    }

}
