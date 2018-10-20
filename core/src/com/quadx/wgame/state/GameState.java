package com.quadx.wgame.state;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.quadx.wgame.Monster;
import com.quadx.wgame.Plant;
import com.quadx.wgame.Player;
import com.quadx.wgame.World;
import com.quadx.wgame.commands.LeftComm;
import com.quadx.wgame.commands.PlantComm;
import com.quadx.wgame.commands.RightComm;
import shapes1_5_5.command.Command;
import shapes1_5_5.shapes.ShapeRendererExt;
import shapes1_5_5.states.GameStateManager;
import shapes1_5_5.states.State;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class GameState extends State {
    ShapeRendererExt sr ;
    public static World world;
    public static Player p1;
    Monster mon;

    public GameState(GameStateManager gsm) {
        super(gsm);
        sr = new ShapeRendererExt();

        sr.setAutoShapeType(true);

        Command.add(new LeftComm());
        Command.add(new RightComm());
        Command.add(new PlantComm());

        world  = new World();
        world.addPlant(new Plant(30));

        p1= new Player();
        mon=  new Monster();
    }

    @Override
    public void update(float dt) {
        Command.runList();
        world.collidePlant(dt,mon);
        world.update(dt);

        p1.update(dt);
        mon.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        world.render(sr);
        p1.render(sr);
        mon.render(sr);
        sr.end();
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
