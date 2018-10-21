package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import shapes1_5_5.physics.Body;
import shapes1_5_5.physics.Physics;
import shapes1_5_5.shapes.ShapeRendererExt;

import static com.quadx.wgame.state.GameState.world;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class Moon {
    Body body;
    public float ang = 0;

    public Moon() {
        body = new Body();
        body.setBoundingBox(Physics.getRadialVector(world.r + 150, ang).add(world.body.pos())
                , new Vector2(40, 40));
    }

    public void update(float dt){
        ang+=.3;
        body.setBoundingBox(Physics.getRadialVector(world.r + 150, ang).add(world.body.pos())
                , new Vector2(40, 40));
    }

    public void render(ShapeRendererExt sr) {
        sr.setColor(Color.LIGHT_GRAY);
        sr.rect(body.getBoundingBox(), ang);
    }

}
