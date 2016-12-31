package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Canvas;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class CircleObject extends GameObject {

    protected double radius;

    public CircleObject(double x, double y) {
        super(x,y);
        setShape(Shapes.CIRCLE);

    }


    public double getRadius() {
            return radius;
        }

    protected void calculateMass(){
        mass = density * radius * radius * Math.PI;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(picture,(int) (drawX) - picture.getWidth() / 2,(int) drawY - picture.getHeight() / 2, null);
    }

    @Override
    public boolean contains(float x, float y){
        int tdx = (int)Math.abs(x-this.x);
        int tdy = (int)Math.abs(y-this.y);
        if(tdx<=radius && tdy <=radius)
            return true;
        return false;
    }
}
