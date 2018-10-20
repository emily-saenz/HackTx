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
    Body body;
    float ang = 0;
    int size = 1;
    Delta dGrow = new Delta(2f * SECOND);
    Delta dEat = new Delta(1 * SECOND);
    boolean dead = false;

    public Plant(float ang) {
        body = new Body();
        body.setBoundingBox(Physics.getRadialVector(world.r - 2, ang).add(world.body.pos())
                , new Vector2(15, 5));
        this.ang = ang;

    }

    void render(ShapeRendererExt sr) {
        sr.setColor(Color.GREEN);
        sr.rect(body.getBoundingBox(), ang);
    }

    public void update(float dt, Monster mon) {
        dGrow.update(dt);
        if (body.getBoundingBox().overlaps(mon.body.getBoundingBox())) {
            getEaten(dt);
        } else {
            if (dGrow.isDone() && size < 4) {
                dGrow.reset();
                size++;
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
