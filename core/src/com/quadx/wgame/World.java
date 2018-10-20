package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import shapes1_5_5.physics.Body;
import shapes1_5_5.shapes.Circle;
import shapes1_5_5.shapes.ShapeRendererExt;

import java.util.ArrayList;

import static shapes1_5_5.states.State.scr;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class World {
    Circle shape ;
    public float r = 150;
    Body body = new Body();

    ArrayList<Plant> plants = new ArrayList<>();

    public World(){
        shape= new Circle(scr.size.cpy().scl(.5f),r);
        body.setPos(shape.center);
    }

    public boolean addPlant(Plant p){
        boolean can = true;
        for(Plant pl: plants){
            if(Math.abs(p.ang-pl.ang)<5){
                can=false;
            }
        }
        if(can)
            plants.add(p);
        return can;
    }

    public void update(float dt){
        plants.forEach(x->x.update(dt));
    }

    public void render(ShapeRendererExt sr){
        sr.setColor(Color.GREEN);
        sr.circle(shape);
        plants.forEach(x->x.render(sr));
    }

    public void collidePlant(float dt, Monster mon) {
        for(Plant p : plants){
            if(p.body.getBoundingBox().overlaps(mon.body.getBoundingBox())){
                p.getEaten(dt);
            }else{
                p.coll=false;
            }
        }
    }
}
