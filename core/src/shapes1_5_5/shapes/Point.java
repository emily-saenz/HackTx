package shapes1_5_5.shapes;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chris Cavazos on 9/13/2018.
 */
public class Point {
    /*
    rotates point p around origin by angleD in DEGREES
     */
    static Vector2 rotDeg(Vector2 p,float angleD){
        double sin = Math.sin(Math.toRadians(angleD));
        double cos = Math.cos(Math.toRadians(angleD));



        // rotDeg point
        float xnew = (float) (p.x * cos - p.y * sin);
        float ynew = (float) (p.x * sin + p.y * cos);

        return new Vector2(xnew,ynew);

    }
    static Vector2 rotDeg(Vector2 ini, Vector2 ref, float angleD){
        double s =Math.sin( Math.toRadians(angleD));
        double c =Math.cos( Math.toRadians(angleD));
        ini.set(ini.sub(ref));
        double newX = ref.x + (ini.x*c) - (ini.y*s);
        double newY = ref.y + (ini.x*s) + (ini.y*c);

        /*Vector2 shift= new Vector2(ini.x-ref.x,ini.y-ref.y);
        return rotDeg(shift,angleD);*/
        return new Vector2((float) newX,(float)newY);

    }

 /*   public static Vector2 scl(Vector2 ini, Vector2 ref, float per) {
        float r=ini.dst(ref)*per;
        if(r>.5 || r<-.5) {
            float a = ini.angleRad(ref);
            return Body.vec(r, ini, ref).add(ref);
        }
        return ini;
    }*/
}
