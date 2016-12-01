package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class CircleObject extends GameObject {

    private double radius;

    public CircleObject(Bitmap bitmap) {
        super(bitmap);
    }

    public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public double getArea(){
            return Math.PI * radius * radius;
        }

        public double getCircumference(){
            return 2 * Math.PI * radius;
        }

}
