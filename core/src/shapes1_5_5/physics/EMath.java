package shapes1_5_5.physics;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;


/**
 * v0.2
 * 9/13/2018
 *
 * methods added -
 *  float avg(float...)
 *  lerp(float, float, float)
 *
 * v0.1
 * Created by Chris Cavazos on 9/16/2016.
 */
@SuppressWarnings("WeakerAccess")
public class EMath {
    public static Random rn = new Random();

    public static float pathag(Vector2 a, Vector2 b) {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    public static float pathag(double x1, double y1, double x2, double y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    public static float pathag(double a, double b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
    public static float angle(Vector2 a, Vector2 b) {
        float angx = b.x - a.x;
        float angy = b.y - a.y;
        boolean cox, coy;
        float initdeg = (float) Math.toDegrees(Math.atan(angy / angx));
        cox = dx(b, a) >= 0;
        coy = dy(b, a) >= 0;

        if (!cox && coy) {
            initdeg += 180;
        }
        if (!cox && !coy) {
            initdeg += 180;
        }
        if (cox && !coy) {
            initdeg += 360;
        }
        return initdeg;
    }
    public static float angleRad(Vector2 a, Vector2 b) {
        return (float) Math.toRadians(angle(a, b));
    }
    public static float dx(Vector2 a, Vector2 b) {
        return dx(b.x, a.x);
    }
    public static float dx(float a, float b) {
        return b - a;
    }
    public static float dy(Vector2 a, Vector2 b) {
        return b.y - a.y;
    }
    public static float arcL(float theta, float r, float dl) {
        float arc = (float) (((2 * Math.PI * r) / 360) * dl);
        return arc;
    }
    public static float inRange(float a, float b) {
        float a1 = Math.min(a, b);
        float b1 = Math.max(a, b);
        float r = b1 - a1;
        return (r * rn.nextFloat()) + a1;
    }
    public static float lerp(float st, float end, float alpha) {
        return (1 - alpha) * st + alpha * end;
    }
    public static float avg(float... f) {
        float sum = 0;
        for (int i = 0; i < f.length; i++) {
            sum += f[i];
        }
        return sum / f.length;
    }
    public static float cos(double rad){
        return (float) Math.cos(rad);
    }
    public static float cosD(double deg){
        return cos(Math.toRadians(deg));
    }
    public static float sin(double rad){
        return (float) Math.sin(rad);
    }
    public static float sinD(double deg){
        return sin(Math.toRadians(deg));
    }

    public static double round(double d) {
        double rem = d - Math.floor(d);
        if (rem < .5f) {
            return Math.floor(d);
        } else {
            return Math.ceil(d);
        }
    }
    public static double randomGaussianAverage(double a, double b) {
        double mid = (a + b) / 2;
        double r = rn.nextGaussian();
        double max = 1, min = 1;
        if (a > b) {
            min = b / mid;
            max = a / mid;
        }
        if (b > a) {
            min = a / mid;
            max = b / mid;
        }
        for (int i = 0; i < 10 && !(r < min || r > max); i++)
            r = rn.nextGaussian();

        return (int) mid * r;
    }

    public static int roundToNearest45(int ang) {
        int[] arr = new int[]{0, 45, 90, 135, 180, 225, 270, 315, 360};
        int index = 0;
        int min = 10000;
        for (int i = 0; i < arr.length; i++) {
            double d = dx(ang, arr[i]);
            if (d <= min) {
                min = (int) d;
                index = i;
            }
        }
        return arr[index];
    }

    public static boolean isInRange(float a, float min, float max){
        return a>=min || a<=max;
    }

    public static Object getRand(ArrayList l) {
        return l.get(rn.nextInt(l.size()));
    }

    public static Vector2 round(Vector2 v) {
        Vector2 pos = new Vector2(v);
        double x = EMath.round(pos.x);
        double y = EMath.round(pos.y);
        return new Vector2((float) x, (float) y);
    }
    public static Vector2 avg(Vector2... v){
        Vector2 sum = new Vector2();
        for(Vector2 v1: v){
            sum.add(v1);
        }
        float n=v.length;
        return sum.scl(1f/n);
    }
    public static Vector2 avg(Vector2 c, Vector2 b) {
        return new Vector2((c.x+b.x)/2f ,((c.y+b.y)/2f ));
    }
    public static Vector2 randInt(Vector2 v) {
        return new Vector2(rn.nextInt((int) v.x), rn.nextInt((int) v.y));
    }
    public static Vector2 randInt(int i) {
        return randInt(new Vector2(i, i));
    }
    public static Vector2 randGauss() {
        return new Vector2((float) rn.nextGaussian(), (float) rn.nextGaussian());
    }
    public static Vector2 randSign(Vector2 add) {
        return add.scl(rn.nextBoolean() ? 1 : -1, rn.nextBoolean() ? 1 : -1);
    }

}
