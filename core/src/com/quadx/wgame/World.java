package com.quadx.wgame;

import com.badlogic.gdx.graphics.Color;
import shapes1_5_5.physics.Body;
import shapes1_5_5.shapes.Circle;
import shapes1_5_5.shapes.ShapeRendererExt;

import java.util.ArrayList;

import static shapes1_5_5.physics.EMath.rn;
import static shapes1_5_5.states.State.scr;

/**
 * Created by Chris Cavazos on 10/20/2018.
 */
public class World {
    Circle shape;
    public float r = 300;
    Body body = new Body();
    public float acc = 300f;

    ArrayList<Plant> plants = new ArrayList<>();

    public World() {
        shape = new Circle(scr.size.cpy().scl(.5f), r);
        body.setPos(shape.center);
    }

    public boolean addPlant(Plant p) {
        boolean can = true;
        for (Plant pl : plants) {
            if (Math.abs(p.ang - pl.ang) < 5) {
                can = false;
            }
        }
        if (can)
            plants.add(p);
        return can;
    }

    public void update(float dt) {
        for (int i = plants.size() - 1; i >= 0; i--) {
            plants.get(i).update(dt);
        }

    }

    public void render(ShapeRendererExt sr) {
        sr.setColor(Color.GREEN);
        sr.circle(shape);
        plants.forEach(x -> x.render(sr));
    }

    public Plant getRandPlant(Monster m) {
        if (!plants.isEmpty()) {
            Plant p = plants.get(rn.nextInt(plants.size()));
                return p;
        }
        return null;
    }

    public void collision(Monster m, Player p1) {
        for (int i = plants.size() - 1; i >= 0; i--) {
            plants.get(i).collision(m,p1);
            if (plants.get(i).dead)
                plants.remove(i);
        }
    }
}
