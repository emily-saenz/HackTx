package com.quadx.wgame.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.quadx.wgame.*;
import com.quadx.wgame.commands.JumpComm;
import com.quadx.wgame.commands.LeftComm;
import com.quadx.wgame.commands.PlantComm;
import com.quadx.wgame.commands.RightComm;
import shapes1_5_5.command.Command;
import shapes1_5_5.gui.FPSModule;
import shapes1_5_5.gui.Fonts;
import shapes1_5_5.shapes.ShapeRendererExt;
import shapes1_5_5.states.GameStateManager;
import shapes1_5_5.states.State;

import static com.badlogic.gdx.Gdx.gl20;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class GameState extends State {
    ShapeRendererExt sr ;
    public static World world;
    public static Player p1;
    Monster mon;
    FPSModule fps = new FPSModule();
    Sun sun;
    Moon moon;

    public GameState(GameStateManager gsm) {
        super(gsm);
        sr = new ShapeRendererExt();

        sr.setAutoShapeType(true);
        Fonts.initFonts();
        Command.add(new LeftComm());
        Command.add(new RightComm());
        Command.add(new PlantComm());
        Command.add(new JumpComm());

        world  = new World();
        world.addPlant(new Plant(30));
        sun = new Sun();
        moon = new Moon();
        p1= new Player();
        mon=  new Monster();
        fps.setEnabled(true);
    }

    @Override
    public void update(float dt) {
        fps.update(dt);
        Command.runList();
        sun.update(dt);
        moon.update(dt);
        world.update(dt,mon);

        p1.update(dt);
        mon.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glEnable(gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(gl20.GL_SRC_ALPHA, gl20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        world.render(sr);
        p1.render(sr);
        mon.render(sr);
        sun.render(sr);
        moon.render(sr);
        sr.end();
        Fonts.getFont().setColor(Color.WHITE);
        fps.render(sb,sr,new Vector2(20,20));
    }

    @Override
    public void dispose() {

    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
