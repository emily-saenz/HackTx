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
public class Plant {
    Body body ;
    float ang = 0;
    int size = 0;
    Delta dGrow = new Delta(2f*SECOND);
    Delta dEat = new Delta(1*SECOND);
    boolean dead = false;
    boolean coll = false;
    public Plant(float ang){
        body = new Body();
        body.setBoundingBox(Physics.getRadialVector(world.r-2,ang).add(world.body.pos())
                , new Vector2(15,5));
        this.ang = ang;

    }

    void render(ShapeRendererExt sr){
        sr.setColor(Color.GREEN);
        sr.rect(body.getBoundingBox(),ang);
    }

    public void update(float dt) {
        dGrow.update(dt);

        if(!coll && dGrow.isDone() && size <4){
            float w= body.getBoundingBox().width+(10*(size+1));
            body.setDim(new Vector2(w,5));
            dGrow.reset();
            size++;
        }
    }

    public void getEaten(float dt) {
        coll = true;
        dEat.update(dt);
        System.out.println("@#");
        if(dEat.isDone()){
            dEat.reset();
            dGrow.reset();
            size--;
            float w= body.getBoundingBox().width+(10*(size+1));
            body.setDim(new Vector2(w,5));
        }
        if(size<0){
            dead =true;
        }
    }
}
