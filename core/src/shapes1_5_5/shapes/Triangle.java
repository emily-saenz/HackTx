package shapes1_5_5.shapes;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import shapes1_5_5.physics.EMath;

import java.util.ArrayList;

/**
 * #####################################################################
 * #####################################################################
 * v0.3
 * 9/13/2018
 * =================================================
 * constructors added -
 * vec2, vec2
 * vec2, vec2, float
 * vec2, double, float
 * <p>
 * =================================================
 * methods added -
 * rotateDeg
 * setCenter
 * translate
 * <p>
 * =================================================
 * deprecated methods -
 * create
 * <p>
 * #####################################################################
 * #####################################################################
 * V0.1
 * Created by Chris Cavazos on 1/23/2017.
 */
@SuppressWarnings("WeakerAccess")
public class Triangle extends Shape {
    private float[] points;
    Vector2 center = new Vector2();
    Vector2 a = new Vector2();
    Vector2 b = new Vector2();
    Vector2 c = new Vector2();
    Vector3 rads = new Vector3();//arad ->rads.x  |  brad ->rads.y  |  crad ->rads.z


    public Triangle() {
        this(new float[]{0, 0, 0, 0, 0, 0});
    }

    public Triangle(float[] p) {
        points = p;
    }

    public Triangle(Vector2 a, Vector2 b, Vector2 c) {
        this(new float[]{a.x, a.y, b.x, b.y, c.x, c.y});
    }

    @Deprecated
    public Triangle(Vector2 pos, float angle, float r) {
        points = new float[]{
                (float) (pos.x + r * Math.cos(Math.toRadians(90 + angle))), (float) (pos.y + r * Math.sin(Math.toRadians(90 + angle))),
                (float) (pos.x + r * Math.cos(Math.toRadians(210 + angle))), (float) (pos.y + r * Math.sin(Math.toRadians(210 + angle))),
                (float) (pos.x + r * Math.cos(Math.toRadians(330 + angle))), (float) (pos.y + r * Math.sin(Math.toRadians(330 + angle)))};
    }


    /*
    CONSTRUCTOR
    t   -> original triangle
    creates a COPY of input triangle
*/
    public Triangle(Triangle t) {
        int a = t.getPoints().length;
        float[] f = new float[a];
        for (int i = 0; i < a; i++) {
            f[i] = t.getPoints()[i];
        }
        points = f;
        setPoints(points);
        setInitRads();

    }

    /*
    CONSTRUCTOR
    pos   -> center point
    rads     -> radius
    ang   -> angle in DEGREES
    creates an EQUILATERAL triangle around a center point
 */
    public Triangle(Vector2 pos, double r, float ang) {
        float q = (float) r;
        Vector2 o = new Vector2();
        Vector2 d = new Vector2(q, 0);
        a.set(Point.rotDeg(d, ang + 90).add(pos));
        b.set(Point.rotDeg(d, ang + 210).add(pos));
        c.set(Point.rotDeg(d, ang + 330).add(pos));
        setPoints(a, b, c);
        setInitRads();

    }

    /*
    CONSTRUCTOR
    pos   -> center point
    dim.x -> width
    dim.y -> height
    a     -> angle
    creates a ROTATED BASE-HEIGHT triangle around a center point
 */
    public Triangle(Vector2 pos, Vector2 dim, float a) {
        this(pos, dim);
        rotateDeg(a);
        setInitRads();

    }

    /*
    CONSTRUCTOR
    pos -> center point
    dim.x -> width
    dim.y -> height

    creates a BASE-HEIGHT triangle around a center point
 */
    public Triangle(Vector2 pos, Vector2 dim) {
        dim.scl(.5f);
        Vector2 add = new Vector2(pos).add(dim);
        Vector2 sub = new Vector2(pos).add(new Vector2(dim).scl(-1));

        setPoints(pos.x, add.y, sub.x, sub.y, add.x, sub.y);
        setInitRads();
    }
    //============================================================================================

    public void rotateDeg(float ang) {
        a.set(Point.rotDeg(a.cpy(), center.cpy(), ang));
        b.set(Point.rotDeg(b.cpy(), center.cpy(), ang));
        c.set(Point.rotDeg(c.cpy(), center.cpy(), ang));
        setPoints(a, b, c);
    }


    public void setCenter(Vector2 c) {
        Vector2 delta = c.add(center.cpy().scl(-1));

    }

    @Deprecated
    public static Triangle create(Vector2 pos, float hight, float width) {
        /*
            replaced by Triangle(vector2, vector2) constructor
         */
        return new Triangle(new float[]{pos.x, (pos.y + (hight / 2)), (pos.x - (width / 2)), (pos.y - (hight / 2)), (pos.x + (width / 2)), (pos.y - (hight / 2))});
    }

    //==================================
    public void setPoints(float... points) {
        a.set(points[0], points[1]);
        b.set(points[2], points[3]);
        c.set(points[4], points[5]);
        this.points = points;
        center.set(getCenter());
    }

    public void setPoints(Vector2... p) {
        setPoints(p[0].x, p[0].y, p[1].x, p[1].y, p[2].x, p[2].y);
    }

    public static Triangle updatePoints(Vector2 pos, float angle, float r) {
        float[] points = new float[]{pos.x, pos.y,
                (float) (pos.x + r * Math.cos(Math.toRadians(200 + angle))), (float) (pos.y + r * Math.sin(Math.toRadians(200 + angle))),
                (float) (pos.x + r * Math.cos(Math.toRadians(160 + angle))), (float) (pos.y + r * Math.sin(Math.toRadians(160 + angle)))};
        return new Triangle(points);
    }

    public float[] getPoints() {
        return points;
    }

    private void setInitRads() {
        rads.set(a.dst(center), b.dst(center), c.dst(center));
    }

    @Deprecated//probably doesnt work
    public void scl(Vector2 srScl) {
        points[0] *= srScl.x;
        points[1] *= srScl.y;
        points[2] *= srScl.x;
        points[3] *= srScl.y;
        points[4] *= srScl.x;
        points[5] *= srScl.y;
    }

/*    //sets a temporary scale
    public void scl(float per) {
        Vector3 rad=rads.cpy().scl(per);
        Vector2[] pts=pointScalar(rad);
        setPoints(pts);
    }*/

    //sets permanent scale
    public void setScl(float s) {
        rads.set(rads.scl(s));
    }

/*
    //returns triangle to init scale
    public void resetScl() {
        scl(1);
    }
*/

/*
    //actually does the scaling
    public Vector2[] pointScalar(Vector3 sc) {
        Vector2 cn = new Vector2(center);
        Vector3 angs = new Vector3(a.angle(cn), b.angle(cn), c.angle(cn));
        System.out.println("##"+angs.toString()+"  @@"+cn.toString());
        Vector2 a1 = Body.vec(sc.x, angs.x).add(cn);
        Vector2 b1 = Body.vec(sc.y, angs.y).add(cn);
        Vector2 c1 = Body.vec(sc.z, angs.z).add(cn);

        return new Vector2[]{a1, b1, c1};
    }
*/

    //=================================
    public Vector2 a() {
        return new Vector2(points[0], points[1]);
    }

    public Vector2 b() {
        return new Vector2(points[2], points[3]);
    }

    public Vector2 c() {
        return new Vector2(points[4], points[5]);
    }

    public boolean overlaps(Rectangle r) {
        return overlaps(Line.asLines(r));

    }

    boolean overlaps(ArrayList<Line> lines) {
        ArrayList<Line> triLines = Line.asLines(this);
        boolean ov = false;
        for (int i = 0; i < triLines.size(); i++) {
            Line l1 = triLines.get(i);
            for (int j = 0; j < lines.size(); j++) {
                Line l2 = lines.get(j);
                ov = ov || l1.intersects(l2);
                System.out.println("");
            }
        }
        return ov;
    }

    public boolean overlaps(Triangle t) {
        return overlaps(Line.asLines(t));
    }

    public float vectorCross(Vector2 a, Vector2 b) {
        return (a.x * b.y) - (a.y * b.x);
    }

    private boolean sameSide(Vector2 p1, Vector2 p2, Vector2 a, Vector2 b) {
        float cp1 = vectorCross(new Vector2(b.x - a.x, b.y - a.y), new Vector2(p1.x - a.x, p1.y - a.y));
        float cp2 = vectorCross(new Vector2(b.x - a.x, b.y - a.y), new Vector2(p2.x - a.x, p2.y - a.y));
        float r = Vector2.dot(0, cp1, 0, cp2);
        return r >= 0;
    }

    public boolean containsPoint2(Vector2 p) {
        float[] f = points.clone();

        Vector2 a = new Vector2(f[0], f[1]);
        Vector2 b = new Vector2(f[2], f[3]);
        Vector2 c = new Vector2(f[4], f[5]);
        return sameSide(p, a, b, c) && sameSide(p, b, a, c) && sameSide(p, c, a, b);
    }

    public boolean containsPoint(Vector2 p) {
        float[] f = points.clone();
        Vector2 a = new Vector2(f[0], f[1]);
        Vector2 b = new Vector2(f[2], f[3]);
        Vector2 c = new Vector2(f[4], f[5]);

        Vector2 v0 = new Vector2(c.x - a.x, c.y - a.y);
        Vector2 v1 = new Vector2(b.x - a.x, b.y - a.y);
        Vector2 v2 = new Vector2(p.x - a.x, p.y - a.y);

        float[] dots = new float[5];
        dots[0] = Vector2.dot(v0.x, v0.y, v0.x, v0.y);
        dots[1] = Vector2.dot(v0.x, v0.y, v1.x, v1.y);
        dots[2] = Vector2.dot(v0.x, v0.y, v2.x, v2.y);
        dots[3] = Vector2.dot(v1.x, v1.y, v1.x, v1.y);
        dots[4] = Vector2.dot(v1.x, v1.y, v1.x, v2.y);
        float invd = 1 / (dots[0] * dots[3] - dots[1] * dots[1]);
        float u = (dots[3] * dots[2] - dots[1] * dots[4]) * invd;
        float v = (dots[0] * dots[4] - dots[1] * dots[2]) * invd;
        return (u >= 0) && (v >= 0) && (u + v < 1);
    }

    public static ArrayList<Triangle> triangulate(Ngon o) {
        ArrayList<Triangle> output = new ArrayList<>();
        float[] f = o.getVerticies();
        float cx = o.getCenter().x;
        float cy = o.getCenter().y;
        Vector2 c = new Vector2(cx, cy);

        int count = 0;
        int n = o.getN();
        for (int i = 0; i < n; i++) {
            Vector2 a = new Vector2(f[count % (n * 2)], f[(count + 1) % (n * 2)]);
            Vector2 b = new Vector2(f[(count + 2) % (n * 2)], f[(count + 3) % (n * 2)]);
            output.add(new Triangle(a, b, c));
            count += 2;
        }
        return output;
    }

    public void setPos(Vector2 pos) {
        Vector2 delta = new Vector2();
        delta.x = pos.x - getCenter().x;
        delta.y = pos.y - getCenter().y;
        points[0] += delta.x;
        points[1] += delta.y;
        points[2] += delta.x;
        points[3] += delta.y;
        points[4] += delta.x;
        points[5] += delta.y;
    }

    public Vector2 getCenter() {
        return EMath.avg(a, b, c);
    }


    public void translate(float x, float y) {
        a.add(x,y);
        b.add(x,y);
        c.add(x,y);
        setPoints(a,b,c);
    }
}
