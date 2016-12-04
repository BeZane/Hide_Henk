package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;

/**
 * Created by TomVerschueren on 4/12/2016.
 */

public class ScrollBar {


    private List<GameObject> objects = new ArrayList<GameObject>();
    private final int SCREEN_WIDTH = GamePanel.WIDTH;
    private final int SCREEN_HEIGHT = GamePanel.HEIGHT;
    private int WIDTH;
    private int HEIGHT;
    private int shownAmount;
    private ScrollBarType type;

    //The count is how far the map is scrolled. This only used to track which items should be represented on the bar.
    private int count;

    private Bitmap resizeBitMap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap,WIDTH,HEIGHT/shownAmount,false);

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
        objects.remove(gameObject);
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
        gameObject.setBitmap(resizeBitMap(gameObject.getBitmap()));
        objects.add(gameObject);
    }

    public void draw(Canvas canvas){
        int number=0;

        for(int i =0;i<shownAmount;i++){
            Point point = getPoint(number);
            System.out.print("drasewing");
            canvas.drawBitmap(objects.get(count).getBitmap(),0,0,null);
            count++;
            number++;
        }
        number = 0;
        count = count-shownAmount;

    }

    public boolean onTouchEvent(MotionEvent event){
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
        switch (type){
            case RIGHT_SIDE:
                x = WIDTH/2;
                y = SCREEN_WIDTH - HEIGHT+HEIGHT/(2*shownAmount)+HEIGHT*count;
                break;

        }
        point.set(x,y);
        return point;
    }




    public enum ScrollBarType{
        LEFT_SIDE,
        RIGHT_SIDE,
        TOP,
        BOTTOM;
    }



}
