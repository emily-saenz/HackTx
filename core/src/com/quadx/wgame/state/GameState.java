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
import shapes1_5_5.timers.Delta;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.gl20;
import static shapes1_5_5.physics.EMath.rn;
import static shapes1_5_5.timers.Time.SECOND;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class GameState extends State {
    ShapeRendererExt sr;
    public static World world;
    public static Player p1;
    FPSModule fps = new FPSModule();
    Moon moon;
    Cloud cloud;
    public static Sun sun;
    ArrayList<Monster> monsters = new ArrayList<>();
    Delta dSpawn  = new Delta(5*SECOND);
    public GameState(GameStateManager gsm) {
        super(gsm);
        sr = new ShapeRendererExt();

        sr.setAutoShapeType(true);
        Fonts.initFonts();
        Command.add(new LeftComm());
        Command.add(new RightComm());
        Command.add(new PlantComm());
        Command.add(new JumpComm());

        world = new World();
        world.addPlant(new Plant(30));
        moon = new Moon();
        sun = new Sun();
        cloud = new Cloud();
        p1 = new Player();
        fps.setEnabled(true);
        for(int i=0;i<10;i++){
            monsters.add(new Monster());
        }
    }

    @Override
    public void update(float dt) {
        fps.update(dt);
        dSpawn.update(dt);
        Command.runList();
        moon.update(dt);
        sun.update(dt);
        cloud.update(dt);
        if(dSpawn.isDone() && monsters.size()<10 && sun.day>0 ){
            monsters.add(new Monster(moon.ang));
            monsters.add(new Monster(moon.ang+10));

            dSpawn = new Delta((rn.nextInt(5)+2)*SECOND);
        }
        for(int i=monsters.size()-1; i>=0;i--) {
            Monster m = monsters.get(i);
            p1.collision(m);
            world.collision(m,p1);
            if(m.dead)
                monsters.remove(i);

        }
        world.update(dt);

        p1.update(dt);
        monsters.forEach(x->x.update(dt));
    }

    public static boolean isInBound(float angA, float angB, float spread){
        float angd = (angA - angB + 180 + 360) % 360 - 180;
        return angd<spread && angd > -spread;
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glEnable(gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(gl20.GL_SRC_ALPHA, gl20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        world.render(sr);
        p1.render(sr);
        monsters.forEach(x->x.render(sr));
        moon.render(sr);
        sun.render(sr);
        cloud.render(sr);
        sr.end();
        sb.begin();
        p1.renderSB(sb);
        Fonts.setFontSize(5);
        Fonts.getFont().draw(sb,"ENEMIES: "+monsters.size(),10,scr.size.y-30);
        Fonts.getFont().draw(sb,"DAYS: " + sun.day,10,scr.size.y-50);

        sb.end();
        Fonts.getFont().setColor(Color.WHITE);
        fps.render(sb, sr, new Vector2(20, 20));
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
