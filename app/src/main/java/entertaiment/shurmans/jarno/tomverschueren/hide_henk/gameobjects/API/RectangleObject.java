package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class RectangleObject extends GameObject {

    protected double width, height;

    public RectangleObject(double x, double y) {
        super(x,y);
        setShape(Shapes.RECTANGLE);
    }

    public boolean touches(Point point){
        if(getRectangle().contains(point.x,point.y)) {
            return true;
        }else {
            return false;
        }

    }

    protected void calculateMass() {
        mass = density * height * width;
    }

    protected void calculateDimensions(Bitmap picture){
        width = picture.getWidth();
        height = picture.getHeight();
    }

    public Rect getRectangle(){
        return new Rect((int)x,(int)y,(int)(x+width),(int)(y+height));
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
