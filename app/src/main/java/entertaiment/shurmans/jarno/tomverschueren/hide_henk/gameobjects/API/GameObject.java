package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int dx,dy;



    protected Bitmap bitmap;


    public GameObject(Bitmap bitmap){
        this.bitmap = bitmap;

    }



    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public abstract void update();
    public abstract void draw(Canvas canvas);




}
