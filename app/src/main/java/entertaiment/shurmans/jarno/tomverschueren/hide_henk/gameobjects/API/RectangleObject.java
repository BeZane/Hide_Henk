package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class RectangleObject extends GameObject {

    protected int width, height;

    public RectangleObject(Bitmap bitmap) {
        super(bitmap);
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();

    }

    public boolean touches(Point point){
        if(getRectangle().contains(point.x,point.y)) {
            return true;
        }else {
            return false;
        }


    }

    public Rect getRectangle(){
        return new Rect(x,y,x+width,y+height);

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
