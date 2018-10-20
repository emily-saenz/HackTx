package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import shapes1_5_5.physics.Body;
import shapes1_5_5.physics.Physics;
import shapes1_5_5.shapes.ShapeRendererExt;
import shapes1_5_5.timers.Delta;

import static com.quadx.wgame.state.GameState.p1;
import static com.quadx.wgame.state.GameState.world;
import static shapes1_5_5.timers.Time.SECOND;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class Monster {
    Body body = new Body();
    float a = 0;
    float h = 0;
    Delta dPlant = new Delta(1 * SECOND);

    public Monster() {
        body.setBoundingBox(new Vector2(0, 0), new Vector2(32, 32));
        body.setPos(Physics.getRadialVector(world.r, 180));
        dPlant.finish();
    }

    public void update(float dt) {
        move((p1.a %360) -(a %360) <1? -1 :1);
    }

    public void move(float ang) {
        a += ang;
        body.setPos(Physics.getRadialVector(world.r, a).add(world.body.pos()));
    }

    public void render(ShapeRendererExt sr) {
        sr.setColor(Color.RED);
        sr.rect(body.getBoundingBox(), a);
    }

}
