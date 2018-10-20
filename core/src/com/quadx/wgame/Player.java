package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import shapes1_5_5.physics.Body;
import shapes1_5_5.physics.Physics;
import shapes1_5_5.shapes.ShapeRendererExt;
import shapes1_5_5.timers.Delta;

import static com.quadx.wgame.state.GameState.world;
import static shapes1_5_5.timers.Time.SECOND;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class Player {
    Body body = new Body();
    float a = 0;
    float h = 0;
    Delta dPlant = new Delta(1 * SECOND);
    boolean jumping = false;
    Delta dJump = new Delta(.2f * SECOND);
    float jr=0;


    public Player() {
        body.setBoundingBox(new Vector2(0, 0), new Vector2(32, 32));
        body.setPos(Physics.getRadialVector(world.r, 0));
        dPlant.finish();
    }

    public void update(float dt) {
        dPlant.update(dt);
        if(jumping){
            dJump.update(dt);
            if(dJump.isDone()){
                jr-=world.acc*dt;
                if(jr<1) {
                    jumping = false;
                    jr=0;
                    dJump.reset();
                }
            }else
                jr+=world.acc*dt;
            body.setPos(Physics.getRadialVector(world.r+jr, a).add(world.body.pos()));

        }
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
        if (dPlant.isDone()) {
            if (world.addPlant(new Plant(a)))
                dPlant.reset();
        }
    }

    public void jump() {
        jumping=true;
    }
}
