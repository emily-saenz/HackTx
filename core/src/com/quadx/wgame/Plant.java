package com.quadx.wgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.quadx.wgame.state.GameState;
import shapes1_5_5.physics.Body;
import shapes1_5_5.physics.Physics;
import shapes1_5_5.shapes.Rect;
import shapes1_5_5.shapes.ShapeRendererExt;
import shapes1_5_5.timers.Delta;

import static com.quadx.wgame.state.GameState.*;
import static shapes1_5_5.physics.EMath.rn;
import static shapes1_5_5.timers.Time.SECOND;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class Plant {
     Body body;
     float ang = 0;
     int size = 1;
     private Delta dGrow = new Delta(2 * SECOND);
     private Delta dEat = new Delta(1 * SECOND);
     private Delta dChop = new Delta(2 * SECOND);

     boolean dead = false;
     boolean shaded = false;
     boolean attacked= false;
     boolean chopping = false;
     int max= 4;
    public Plant(float ang) {
        body = new Body();
        body.setBoundingBox(Physics.getRadialVector(world.r - 2, ang).add(world.body.pos())
                , new Vector2(15, 5));
        this.ang = ang%360;

    }

    void render(ShapeRendererExt sr) {
        if(!shaded)
            sr.setColor(Color.GREEN);
        else
            sr.setColor(Color.RED);

        if(chopping && size == max)
            sr.rect(Rect.rect(Physics.getRadialVector(world.r+100, ang).add(world.body.pos()),
                    new Vector2(30*(1-dChop.percent()),2)), ang+90);
        sr.rect(body.getBoundingBox(), ang);
    }

    public void collision(Monster m, Player p1){
        if (body.getBoundingBox().overlaps(m.body.getBoundingBox()) &&m.target!=null
                &&  m.target.equals(this)) {
            getEaten(Gdx.graphics.getDeltaTime());
            attacked=true;
        }else
            attacked=false;

        if(GameState.isInBound(ang,p1.a+2,3)){
            chopping=true;
        }else{
            chopping=false;
        }
    }

    public void update(float dt) {
        float angd = (ang - sun.ang + 180 + 360) % 360 - 180;
        shaded= angd<90 && angd>-90;

        if(!shaded)
            dGrow.update(dt);
        if (!attacked && dGrow.isDone() && size < max) {
            dGrow.reset();
            size++;
        }

        if(chopping && size>max){
            dChop.update(dt);
            if(dChop.isDone()){
                dead=true;
                p1.addSeed(rn.nextInt(2)+1);
            }
        }
        fixSize();
        System.out.println(size);

    }

    private void fixSize() {
        body.setDim(new Vector2(size(), 5));

    }

    public void getEaten(float dt) {
        dEat.update(dt);
        if (dEat.isDone()) {
            dEat.reset();
            dGrow.reset();
            size--;
            System.out.println("%%%%"+size);
        }
        if (size < 1) {
            dead = true;
        }
    }

    float size() {
        return (15 * (size));

    }
}
