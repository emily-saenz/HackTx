package shapes1_5_5.physics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import shapes1_5_5.shapes.Rect;

/**
 * Created by Chris Cavazos on 4/28/2018.
 */
public class Body {
    private final Vector2 pos = new Vector2(0, 0);
    private final Vector2 vel = new Vector2(0,0);
    private Rectangle boundingBox = new Rectangle();
    private TextureRegion icon;


    public void update(float dt) {
        setPos(pos().add(vel));
    }

    public void setIcon(TextureRegion a) {
        icon = a;
    }

    public void setPos(Vector2 a) {
        pos.set(a);
        boundingBox.setPosition(pos);
    }

    public Vector2 pos() {
        return new Vector2(pos);
    }

    public void setVel(Vector2 vel) {
        this.vel.set(vel);
    }

    public Vector2 getSize(){
        return new Vector2(boundingBox.width, boundingBox.height);
    }

    public void setBoundingBox(Vector2 pos, Vector2 size) {
        setPos(pos);
        boundingBox = Rect.rect(pos,size);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setDim(Vector2 v) {
        boundingBox = Rect.rect(pos,v);

    }
}
