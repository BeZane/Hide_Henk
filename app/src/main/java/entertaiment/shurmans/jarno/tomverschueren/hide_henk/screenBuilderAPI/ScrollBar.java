package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.ObjectManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HorizontalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;

/**
 * Created by TomVerschueren on 4/12/2016.
 */



public class ScrollBar {


    //Resized objects to fit in the scrollbar is the first, second is unresized.
    protected ArrayList<GameObject> scrollingObjects = new ArrayList<>();
    protected final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
    protected final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    protected int WIDTH;
    protected int HEIGHT;
    protected int shownAmount;
    protected ScrollBarType type;
    protected static final int MAX_CLICK_DURATION = 200;
    protected long startClickTime;
    protected int dy=0;
    protected int x=0,y=0;
    //The count is how far the map is scrolled. This only used to track which items should be represented on the bar.
    protected int count =0;
    protected int offset =0;

    private ObjectManager objectManager;

    private Bitmap resizeBitMap(Bitmap bitmap){
        if(bitmap.getWidth() > this.WIDTH)
            return Bitmap.createScaledBitmap(bitmap,WIDTH,(int)(bitmap.getHeight()/((float)bitmap.getWidth()/WIDTH)),false);
        return bitmap;

    }

    public ScrollBar(ObjectManager objectManager){
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
                break;
            case MIDDLE:
                if(x>(SCREEN_WIDTH-WIDTH)/2 || x<((SCREEN_WIDTH-WIDTH)/2)+WIDTH)
                    return true;
        }
        return false;
    }

    public boolean actionDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
            this.y = (int)y;
        return true;
    }

    public boolean actionMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (isIn((int) x, (int) y)) {
            offset = offset + (this.y - (int) y);
            this.y = (int) y;
            return true;
        }
        return false;
    }

    public boolean actionUp(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
                if(isIn((int)x,(int)y)) {
                    //click event has occurred
                    this.y = (int)y;
                    GameObject gameObject = getSelectedObject((int)event.getY());
                    gameObject.setX(SCREEN_WIDTH/2);
                    gameObject.setY(SCREEN_HEIGHT/2);
                    gameObject.setSolid(true);
                    objectManager.addObject(gameObject);
                    System.out.println("COMING HERE");

                    return true;
                }


        return false;
    }

    /**
     * Get position of the image that is numbered "number" according to the count.
     * @param number The number of the element visible
     * @return returns a point with X and Y filled in.
     */
    protected Point getPoint(int number){
        Point point = new Point();
        //TODO: Add the other enums
        switch (type){
            case RIGHT_SIDE:
                point.x = SCREEN_WIDTH-(WIDTH/2)-(scrollingObjects.get(number).getBitmap().getWidth()/2);
                if(scrollingObjects.get(number).getShape() == GameObject.Shapes.CIRCLE){
                    point.x = SCREEN_WIDTH-WIDTH/2;
                }

                point.y = SCREEN_HEIGHT/(2*shownAmount)+HEIGHT/shownAmount*number;
                break;
            case MIDDLE:
                point.x = SCREEN_WIDTH-(WIDTH/2)-(scrollingObjects.get(number).getBitmap().getHeight()/2);
                if(scrollingObjects.get(number).getShape() == GameObject.Shapes.CIRCLE){
                    point.x = (SCREEN_WIDTH)/2;
                }
                point.y = SCREEN_HEIGHT/(2*shownAmount)+HEIGHT/shownAmount*number;
        }
        return point;
    }

    /**
     *
     * @param y
     * @return
     */
    public GameObject getSelectedObject(int y){
        GameObject gameObject = scrollingObjects.get(0);
        int dif =1000000000;
        for(int i=0;scrollingObjects.size()>i;i++){
            if(dif> Math.abs(getPoint(i).y-offset-(y))){
                gameObject = (GameObject)scrollingObjects.get(i);

                dif = Math.abs(getPoint(i).y-y);
            }

        }
        switch (gameObject.getType()) {
            case HENK:
                return new Henk(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
            case WATERDROP:
                return new WaterDrop(SCREEN_WIDTH/2,SCREEN_HEIGHT/2);
            case HORIZONTALPLANK:
                return new HorizontalPlank(SCREEN_WIDTH/2,SCREEN_HEIGHT/2);
            default:
                return null;

        }
    }

    public int getSelectedNumber(int y) {
        GameObject gameObject = scrollingObjects.get(0);
        int dif =1000000000;
        int number=0;
        for(int i=0;scrollingObjects.size()>i;i++){
            if(dif> Math.abs(getPoint(i).y-offset-(y))){
                 number = i;
                dif = Math.abs(getPoint(i).y-y);
            }

        }
        return number;
    }




        public enum ScrollBarType{
        LEFT_SIDE,
        RIGHT_SIDE,
        TOP,
        BOTTOM,
        MIDDLE;
    }



}

