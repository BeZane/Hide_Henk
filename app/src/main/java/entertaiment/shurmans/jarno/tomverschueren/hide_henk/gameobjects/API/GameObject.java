package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.icu.text.DisplayContext;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class GameObject {

    //position and movement
    protected double x;
    protected double y;
    protected double dx,dy;
    protected final static double GRAVITY = 0.3;
    protected int maxFallSpeed = 15;
    protected double rotation; //expressed in degrees to determine the rotation
    protected double drotation; //rotation speed
    protected double arotation = 0.004; //rotation acceleration
    protected double maxRotationSpeed = 0.05;

    protected boolean solid; //solid objects are not affected by gravity, nor can be moved by impact
    protected boolean prepareSolid;

    //drawing
    protected Bitmap picture;   //Bitmap already scaled to the correct screen width and height.
    protected Bitmap rotatedPicture;
    protected Matrix rotationMatrix;
    protected double drawX;     //Make sure that you retrieve dimensions of objects from the original bitmap before scaling it!
    protected double drawY;

    //kinematics
    protected double bouncingFactor = 0.5;  //value between 0 and 1. 0 does not bounce at all; 1 bounces 100%
    protected double restitution = 0.8;
    protected double mass;
    protected double density;


    //collision detection
    public enum Shapes {RECTANGLE, CIRCLE};
    protected Shapes shape;
    public enum Types{HENK, PLANK, WATERDROP, TIRE};
    protected Types type;


    public GameObject(double x, double y){
        this.x = x;
        this.y = y;

        rotationMatrix = new Matrix();
    }

    /**
     * Used to save the solid state of an object to build the level
     * @param b
     */
    public void setPrepareSolid(boolean b){
        prepareSolid = b;
    }

    public boolean isPrepareSolid(){
        return prepareSolid;
    }




    //getters and setters
    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }
    public void setDx(double xSpeed){ dx = xSpeed;}
    public void setDy(double ySpeed){ dy = ySpeed;}
    public void setRotation(double rot){ rotation = rot;}
    public void setSolid(Boolean b){ solid = b;}
    public Bitmap getBitmap(){
        return picture;
    }
    public void setPicture(Bitmap picture){
        this.picture = picture;
    }
    public void setX(double x){
        this.x =x;
    }
    public void setY(double y){
        this.y =y;
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public void stop(){
        dx = 0;
        dy = 0;
    }

    public void setShape(Shapes shape){
        this.shape = shape;
    }
    public Shapes getShape(){
        return shape;
    }
    public Types getType(){return type;}



    protected double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    //checks collision between circle - circle, circle - rect, circle - point, rect - rect, rect - point
    public boolean checkCollision(GameObject o1){
        boolean collision = false;
        //check if we will have collision if we keep going in our current direction
        x += dx;
        y += dy;

        //store current dx and dy to restore later on
        double tempDx = dx;
        double tempDy = dy;

        //circle - circle collision
        if(o1.getShape() == Shapes.CIRCLE && shape == Shapes.CIRCLE){
            CircleObject c1 = (CircleObject)o1;
            CircleObject c2 = (CircleObject)this;
            //if the sum of the radiuses is bigger than the distance between the centers there is collision
            if(c1.getRadius() + c2.getRadius() > distance(c1.x, c1.y, c2.x, c2.y)){
                collision = true;
                calculateNewVector(c1.x, c1.y, c1);
                //Henk gets cleaned if he gets into collision with water
                if((type == Types.WATERDROP && c1.type == Types.HENK)){
                    Henk henk = (Henk)c1;
                    henk.cleanHenk();
                }
                if((c1.type == Types.WATERDROP && type == Types.HENK)){
                    Henk henk = (Henk)this;
                    henk.cleanHenk();
                }
            }

        }

        //circle - rectangle detection and rectangle - circle collision
        if((o1.getShape() == Shapes.RECTANGLE && shape == Shapes.CIRCLE) ||
                (o1.getShape() == Shapes.CIRCLE && shape == Shapes.RECTANGLE)){
            CircleObject c1;
            RectangleObject r1;
            if(shape == Shapes.CIRCLE) {
                c1 = (CircleObject) this;
                r1 = (RectangleObject) o1;
            }
            else{
                c1 = (CircleObject)o1;
                r1 = (RectangleObject)this;
            }
            //find the point which is part of the rectangle and is closest to the center of the circle with coordinates (x,y)
            double x;
            double y;
            if(c1.x < r1.x){ x = r1.x;}
            else if(c1.x > r1.x + r1.getWidth()){ x = r1.x + r1.getWidth();}
            else{x=c1.x;}
            if(c1.y < r1.y){ y = r1.y;}
            else if(c1.y > r1.y + r1.getHeight()) {y = r1.y + r1.getHeight();}
            else{y = c1.y;}
            //basically circle point detection from now on.
            if(c1.getRadius() > distance(c1.x, c1.y, x, y)){
                collision = true;
                if(shape == Shapes.CIRCLE){
                    calculateNewVector(x, y, r1);
                }
                if(shape == Shapes.RECTANGLE){
                    calculateNewVector(x, y, c1);
                }

            }

        }

        //rectangle - rectangle detection
        if(o1.getShape() == Shapes.RECTANGLE && shape == Shapes.RECTANGLE){
            RectangleObject r1 = (RectangleObject)o1;
            RectangleObject r2 = (RectangleObject)this;
            boolean xOverlap = false;
            boolean yOverlap = false;
            if(r1.x - r2.x >= 0 && r1.x - r2.x < r2.width){
                xOverlap = true;
            }
            else if(r1.x - r2.x < 0 && r2.x - r1.x < r1.width){
                xOverlap = true;
            }
            if(r1.y - r2.y >= 0 && r1.y - r2.y < r2.height){
                yOverlap = true;
            }
            else if(r1.y - r2.y < 0 && r2.y - r1.y < r1.height){
                yOverlap = true;
            }
            if(xOverlap && yOverlap){
                collision = true;
            }
        }

        //we don't actually want to move in our current direction just yet
        x -= tempDx;
        y -= tempDy;

        if(collision){
        }

        return collision;

    }
    /*
        @param x1, y1: the point where there is collision
        @param o1: the gameobject we have collision with
     */
    protected void calculateNewVector(double x1, double y1, GameObject o1){
        if(solid){
            return;
        }
        double x = this.x;
        double y = this.y;
        if(shape == Shapes.RECTANGLE){
            RectangleObject r = (RectangleObject)this;
            x += r.getWidth() / 2;
            y += r.getHeight() / 2;
        }
        double speed = Math.sqrt(dx*dx + dy*dy);
        double o1Speed = Math.sqrt(o1.dx * o1.dx + o1.dy * o1.dy);
        double xVector = x - x1;
        double yVector = y - y1;
        double alpha = Math.atan((-1 *yVector) /xVector);  //the direction of the force caused by o1
        if(xVector < 0){
            alpha += Math.PI;
        }
        while(alpha < 0){
            alpha += 2*Math.PI;
        }
        //Rolling of another object
        if(speed < 1 && Math.abs(Math.cos(alpha)) > 0.001 && Math.sin(alpha) > 0){
            dx += Math.cos(alpha) * GRAVITY;
            dy = 0;
            //System.out.println(type + " rolling off " + o1.type);
            //System.out.println("direction of " + type + ": " + alpha + "direction of what we hit : "  +betha);
        }
        //Bouncing off a solid object
        else if(o1.solid){   //the direction of the our object after collision
            System.out.println(alpha*360/2/Math.PI);
            if(alpha *180 / Math.PI % 90 == 0 ) {
                System.out.println("op een vlak");
                dy += -Math.sin(alpha) * dy * 2;
                dx += Math.cos(alpha) * dx * 2;
                dy = dy * bouncingFactor;
                dx = dx * bouncingFactor;
            }
            else{
                System.out.println("op de punt");
                dy = -Math.sin(alpha) * bouncingFactor * dy;
                dx += Math.cos(alpha) * bouncingFactor * speed;
            }
        }
        //Collision between two objects that can move

        else{
            System.out.println("alpha = " + alpha*180/Math.PI);
            double newDx = (mass - o1.mass)/(mass + o1.mass)*dx + (2*o1.mass)/(mass + o1.mass)*o1.dx; //the newDx in case of 1D movement and no loss kinetic energy
            double F = mass * Math.abs(newDx - dx) / Math.abs(Math.cos(alpha));
            System.out.println("Force = " + F);
            dx += F * Math.cos(alpha) / mass;
            dy -= F * Math.sin(alpha) / mass;
            o1.dx -= F * Math.cos(alpha) / o1.mass;
            o1.dy += F * Math.sin(alpha) / o1.mass;
            System.out.println(type + " has a new speed of (" + dx + "," + dy +")");
            System.out.println(o1.type + " has a new speed of (" + o1.dx + "," + o1.dy +")");
        }

    }

    public void update(){
        x = x + dx;
        y = y + dy;
        if(!solid){
            dy += GRAVITY;
        }
        if(dy > maxFallSpeed){
            dy = maxFallSpeed ;
        }
        rotation += drotation;
        //rotationMatrix.postRotate((float)rotation);
        drawUpdate();
    };

    public boolean isSolid(){
        return solid;
    }

    public void drawUpdate(){
        drawX = x * GamePanel.X_SCALE;
        drawY = y * GamePanel.Y_SCALE;
    }

    //drawing stuff
    protected void scalePicture(Bitmap picture){
        int destWidth = (int)(picture.getWidth() * GamePanel.X_SCALE);
        int destHeight = (int) (picture.getHeight() * GamePanel.Y_SCALE);
        this.picture = Bitmap.createScaledBitmap(picture, destWidth, destHeight, false);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(picture, (int)drawX, (int)drawY, null);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void rescaleObject(int newWidth, int newHeight);
    public abstract boolean contains(float x, float y);
}
