package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.quadx.wgame.state.GameState;
import shapes1_5_5.gui.Fonts;
import shapes1_5_5.physics.Body;
import shapes1_5_5.physics.Physics;
import shapes1_5_5.shapes.ShapeRendererExt;
import shapes1_5_5.timers.Delta;

import static com.quadx.wgame.state.GameState.world;
import static shapes1_5_5.states.State.scr;
import static shapes1_5_5.timers.Time.SECOND;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class Player {
    Body body = new Body();
    float a = 0;
    float h = 0;
    Delta dPlant = new Delta(.1f * SECOND);
    boolean jumping = false;
    Delta dJump = new Delta(.2f * SECOND);
    float jr=0;
    int seed= 20;
    boolean falling= false;


    public Player() {
        body.setBoundingBox(new Vector2(0, 0), new Vector2(32, 32));
        body.setPos(Physics.getRadialVector(world.r, 0).add(world.body.pos()));
        dPlant.finish();
    }

    public void collision(Monster m){
        if(GameState.isInBound(a,m.a,5) && jumping && falling){
                m.dead=true;
        }
    }

    public void update(float dt) {
        dPlant.update(dt);
        if(jumping){
            dJump.update(dt);
            if(dJump.isDone()){
                jr-=world.acc*dt;
                falling=true;
                if(jr<2) {
                    jumping = false;
                    falling=false;

                    jr=0;
                    dJump.reset();
                }
            }else
                jr+=world.acc*dt;
            body.setPos(Physics.getRadialVector(world.r+jr, a).add(world.body.pos()));

        }
        a%=360;

    }

    public void move(float ang) {
        a += ang;
        body.setPos(Physics.getRadialVector(world.r, a).add(world.body.pos()));
    }

    public void render(ShapeRendererExt sr) {
        sr.setColor(Color.BLUE);
        sr.rect(body.getBoundingBox(), a);
    }

    public void plant() {
        if (dPlant.isDone() && seed>0) {
            if (world.addPlant(new Plant(a))) {
                dPlant.reset();
                seed--;
            }
        }
    }

    public void jump() {
        jumping=true;
    }

    public void addSeed(int i) {
        seed+=i;

    }

    public void renderSB(SpriteBatch sb) {
        Fonts.setFontSize(5);
        Fonts.getFont().draw(sb,"SEEDS: "+seed,10,scr.size.y-10);
    }
}
