package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.Game;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;

/**
 * This is the superclass of all the levels.
 */

public class LevelState extends GameState {

    //fields
    protected ArrayList<GameObject> objects = new ArrayList<GameObject>();
    protected ArrayList<GameObject> toPlace = new ArrayList<GameObject>();
    private int indexToPlace;
    private GameObject selectedObject;
    private Bitmap background;
    private Bitmap previous;
    protected Henk henk;
    //hose
    private Bitmap hose;
    private double hosePos;
    private boolean hoseSpawned;
    private double hoseSpeed = 3;




    public LevelState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
        previous = Bitmap.createScaledBitmap(previous, 140, 140, false);
        //making hose bitmap
        Bitmap unScaledHose = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.hose);
        unScaledHose = Bitmap.createScaledBitmap(unScaledHose, 100, 110, false);
        int destWidth = (int)(unScaledHose.getWidth() * GamePanel.X_SCALE);
        int destHeight = (int) (unScaledHose.getHeight() * GamePanel.Y_SCALE);
        hose = Bitmap.createScaledBitmap(unScaledHose, destWidth, destHeight, false);
        populate();
    }

    protected void populate(){
        //TODO read in the file that has info about the level layout.
    }

    private void reset(){
        objects = new ArrayList<>();
        hosePos = -hose.getWidth();
        hoseSpawned = false;
    }

    private void checkCollisions(GameObject object){
        for(GameObject o: objects){
            if(!object.equals(o)){
                if(object.checkCollision(o)){
                    checkCollisions(o);
                }
            }
        }
    }

    public void update(){
        for(int i = 0; i < objects.size(); i++){
            GameObject o = objects.get(i);
            o.update();
            checkCollisions(o);
            if(o.getType() == GameObject.Types.WATERDROP){
                WaterDrop drop = (WaterDrop)o;
                if(drop.getTimeLived() > drop.getTimeToLive()){
                    objects.remove(i);
                    i--;
                }
            }
        }


        if(hoseSpawned){
            if(hosePos % 10 == 0){
                WaterDrop w = new WaterDrop(hosePos * hoseSpeed + hose.getWidth() / GamePanel.X_SCALE * 4 / 5, hose.getHeight() / GamePanel.Y_SCALE);
                w.setDx(hoseSpeed/3);
                w.setDy(5);
                objects.add(w);
            }
            hosePos++;
            if(hosePos > GamePanel.GAME_WIDTH){
                hoseSpawned = false;
            }
        }
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(background,0,0,null);
        for(GameObject o: objects){
            o.draw(canvas);
        }
        if(hoseSpawned){
            canvas.drawBitmap(hose,(int) (hosePos * hoseSpeed * GamePanel.X_SCALE), 0, null);
        }
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);
        canvas.drawBitmap(previous, 0,0,null);

        selectedObject.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX() / GamePanel.X_SCALE;
                float y = event.getY() / GamePanel.Y_SCALE;

                if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    reset();
                    gsm.setState(gsm.LEVELSELECT);
                }
                else if( x < 50 && y < 50){
                    hoseSpawned = true;
                    hosePos = 0;
                }
                else {
                    if(indexToPlace < toPlace.size()) {
                        selectedObject = toPlace.get(indexToPlace);
                        selectedObject.setX(x);
                        selectedObject.setY(y);
                        indexToPlace++;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float xPos = event.getX() / GamePanel.X_SCALE;
                float yPos = event.getY() / GamePanel.Y_SCALE;

                if(selectedObject != null) {
                    System.out.println(xPos);
                    selectedObject.setX(xPos);
                    selectedObject.setY(yPos);
                    System.out.println(selectedObject.getX());
                }

                break;

            case MotionEvent.ACTION_UP:
                if(selectedObject != null) {
                    objects.add(selectedObject);
                    selectedObject = null;
                }
        }
        return true;
    }


}