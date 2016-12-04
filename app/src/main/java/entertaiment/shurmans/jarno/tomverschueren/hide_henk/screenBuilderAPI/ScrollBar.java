package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;

/**
 * Created by TomVerschueren on 4/12/2016.
 */

public class ScrollBar {


    //Resized objects to fit in the scrollbar is the first, second is unresized.
    private HashMap<GameObject,GameObject> scrollingObjects = new HashMap<>();
    private final int SCREEN_WIDTH = GamePanel.WIDTH;
    private final int SCREEN_HEIGHT = GamePanel.HEIGHT;
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

    private Bitmap resizeBitMap(Bitmap bitmap){
        System.out.println(bitmap.getHeight());
        System.out.println(WIDTH);
        return Bitmap.createScaledBitmap(bitmap,WIDTH,(int)(bitmap.getHeight()/((float)bitmap.getWidth()/WIDTH)),false);

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
        GameObject original = gameObject;
        gameObject.setBitmap(resizeBitMap(gameObject.getBitmap()));
        scrollingObjects.put(gameObject, original);
    }

    public void draw(Canvas canvas){
        int number=0;

        for(int i =0;i<shownAmount;i++){
            Point point = getPoint(number);
            canvas.drawBitmap(scrollingObjects.get(number).getBitmap(),point.x ,point.y - offset,null);
            number++;
            count++;
        }
        count =0;


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
                    for(GameObject gameObject:scrollingObjects.keySet()){
                        if(gameObject.touches(new Point((int)x,(int)y))){
                            //TODO: Draw it on the screen
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
        int x=0,y=0;
        //TODO: Add the other enums
        switch (type){
            case RIGHT_SIDE:
                point.x = SCREEN_WIDTH-WIDTH;

                point.y = SCREEN_HEIGHT/(2*shownAmount)+HEIGHT/shownAmount*count;
                break;

        }
        return point;
    }




    public enum ScrollBarType{
        LEFT_SIDE,
        RIGHT_SIDE,
        TOP,
        BOTTOM;
    }



}
