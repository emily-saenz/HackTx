package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
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
    float ang = 0;
    Rectangle shade;
    int x= 2000;

    public Moon() {
        body = new Body();
        Vector2 pos = Physics.getRadialVector(world.r + 150, ang+180).add(world.body.pos());
        body.setBoundingBox(pos, new Vector2(40, 40));
        updateShade();
    }

    void updateShade(){
        Vector2 pos2 = Physics.getRadialVector(world.r + 500, ang+270).add(world.body.pos());
        shade= new Rectangle(pos2.x,pos2.y,x,x);
    }
    public void update(float dt){
        ang+=.1;
        Vector2 pos = Physics.getRadialVector(world.r + 150, ang+180).add(world.body.pos());
        body.setBoundingBox(pos, new Vector2(40, 40));
        updateShade();
    }

    public void render(ShapeRendererExt sr) {
        sr.setColor(new Color(.1f,.1f,.1f,.6f));
        sr.rect(shade, ang);
        sr.setColor(Color.GOLD);

        sr.rect(body.getBoundingBox(), ang);

    }

}
