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

    public boolean contains(float x, float y){
        if((this.x-20)<x && x<this.x+getPicture().getWidth()+20
                && (this.y-20)<y && y<(this.y+getPicture().getHeight()+20))
            return true;
        return false;
    }



    protected void calculateMass() {
        mass = density * height * width;
    }

    protected void calculateDimensions(Bitmap picture){
        width = picture.getWidth();
        height = picture.getHeight();
    }

    public double getHeight() {
        return height;
    }


    public double getWidth() {
        return width;
    }

}
