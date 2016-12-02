package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

/**
 * This class is the superclass of all the objects used (Henk, the rain, the building blocks, ...)
 */

public abstract class GameObject {

    public static final int GRAVITY = 5;

    //position stuff, used to calculate relative position (before rescaling to the desired screen size)
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    //collision
    public enum collisionShape{CIRCLE, RECTANGLE, POINT};
    protected collisionShape shape;

    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
    }


    //some getters
    public collisionShape getShape(){return shape;}
    public double getX(){return x;}
    public double getY(){return y;}


    protected double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /*
    //checks collision between circle - circle, circle - rect, circle - point, rect - rect, rect - point
    public boolean checkCollision(GameObject o1, GameObject o2){
        //circle - circle collision
        if(o1.getShape() == collisionShape.CIRCLE && o2.getShape() == collisionShape.CIRCLE){
            //if the sum of the radiuses is bigger than the distance between the centers there is collision
            if()
        }
    }
    */

}
