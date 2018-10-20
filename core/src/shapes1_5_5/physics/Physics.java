package shapes1_5_5.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chris Cavazos on 1/11/2017.
 */
public class Physics {
    public static Vector2 getVector(float vel, Vector2 start, Vector2 end){
        return end.cpy().sub(start).nor().scl(vel);
    }
    public static Vector2 getRadialVector(float radius, float angDeg){
        return new Vector2(1,0).rotate(angDeg).scl(radius);
    }
}
