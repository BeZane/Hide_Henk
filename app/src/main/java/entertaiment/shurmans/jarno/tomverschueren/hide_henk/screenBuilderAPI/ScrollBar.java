package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.BuildingObjectManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.CircleObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;

/**
 * Created by TomVerschueren on 4/12/2016.
 */



public class ScrollBar {


    //Resized objects to fit in the scrollbar is the first, second is unresized.
    private ArrayList<GameObject> scrollingObjects = new ArrayList<>();
    private final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
    private final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    private int WIDTH;
    private int HEIGHT;
    private int shownAmount;
    private ScrollBarType type;
    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;
    private int dy=0;
    private int x=0,y=0;
    //The count is how far the map is scrolled. This only used to track which items should be represented on the bar.
    private int count =0;
    private int offset =0;

    private BuildingObjectManager objectManager;

    private Bitmap resizeBitMap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap,WIDTH,(int)(bitmap.getHeight()/((float)bitmap.getWidth()/WIDTH)),false);

    }

    public ScrollBar(BuildingObjectManager objectManager){
        this.objectManager = objectManager;
    }

    public ScrollBarType getType() {
        return type;
    }

    public void setType(ScrollBarType type) {
        this.type = type;
    }

    public int getShownAmount() {
        return shownAmount;
    }

    /**
     * How much items will be visible in the scrollbar?
     * @param shownAmount Amount of items that will be visible in the scrollbar.
     */


    public void setShownAmount(int shownAmount) {
        this.shownAmount = shownAmount;
    }

    public void removeObject(GameObject gameObject){
        //TODO: Resize object before removing
        scrollingObjects.remove(gameObject);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void addObject(GameObject gameObject){
        gameObject.setPicture(resizeBitMap(gameObject.getBitmap()));
        scrollingObjects.add(gameObject);
    }

    public void draw(Canvas canvas){
        int number=0;
        for(int i =0;i<scrollingObjects.size();i++){

            Point point = getPoint(number);

            scrollingObjects.get(number).setX((point.x)/GamePanel.X_SCALE);
            scrollingObjects.get(number).setY((point.y-offset)/GamePanel.Y_SCALE);
            scrollingObjects.get(number).update();
            scrollingObjects.get(number).setSolid(true);
            scrollingObjects.get(number).draw(canvas);

            number++;
            count++;
        }
        count =0;
        number=0;

    }

    public boolean isIn(int x, int y){
        //TODO: Add other enums
        switch (ScrollBarType.RIGHT_SIDE){
            case RIGHT_SIDE:
                if(x>SCREEN_WIDTH-WIDTH-10)

                    return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        long clickDuration;
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startClickTime = Calendar.getInstance().getTimeInMillis();
                if(isIn((int) x, (int)y)){
                    this.y = (int)y;
                    GameObject gameObject = getSelectedObject((int)y);
                    gameObject.setSolid(true);
                    objectManager.addObject(gameObject);
                    return true;
                }else{


                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                 clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(isIn((int)x,(int)y) &&clickDuration > MAX_CLICK_DURATION){

                    offset = offset + (this.y-(int)y) ;
                    this.y =(int)y;
                }
                break;
            case MotionEvent.ACTION_UP: {
                 clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    //click event has occurred
                    for(GameObject gameObject:scrollingObjects){
                        if(gameObject.checkCollision(new Henk(x,y))) {
                            //TODO: Draw it on the screen
                            //TODO: Change Henk to a point class
                    }
                    }
                }
            }
            default:
        }
        return true;
    }

    /**
     * Get position of the image that is numbered "number" according to the count.
     * @param number The number of the element visible
     * @return returns a point with X and Y filled in.
     */
    private Point getPoint(int number){
        Point point = new Point();
        //TODO: Add the other enums
        switch (type){
            case RIGHT_SIDE:
                point.x = SCREEN_WIDTH-WIDTH;
                if(scrollingObjects.get(number).getShape() == GameObject.Shapes.CIRCLE){
                    point.x = SCREEN_WIDTH-WIDTH/2;
                }

                point.y = SCREEN_HEIGHT/(2*shownAmount)+HEIGHT/shownAmount*count;
                break;

        }
        return point;
    }

    /**
     *
     * @param y
     * @return
     */
    public GameObject getSelectedObject(int y){
        int cy =Math.abs(getPoint(0).y-y);
        GameObject gameObject = scrollingObjects.get(0);
        for(int i=0;scrollingObjects.size()>i;i++){
            if(cy> Math.abs(getPoint(i).y-y)){
                gameObject = scrollingObjects.get(i);
            }

        }
    return gameObject;
    }




    public enum ScrollBarType{
        LEFT_SIDE,
        RIGHT_SIDE,
        TOP,
        BOTTOM;
    }



}

