package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

/**
 * Created by Admin on 2/12/2016.
 */

public class CircleObject extends GameObject {

    protected double radius;

    public CircleObject(double x, double y, double radius){
        super(x,y);
        this.radius = radius;
        shape = collisionShape.CIRCLE;
    }

    public double getRadius(){
        return radius;
    }


}
