package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;

/**
 * This is the superclass of all the levels.
 */

public class LevelState extends GameState {

    //fields
    protected ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private Bitmap background;
    private Bitmap previous;
    protected Henk henk;


    public LevelState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }


    public void init(){

        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
    }

    public void update(){
        for(GameObject o: objects){
            for(GameObject o2: objects){
                if(!o.equals(o2)) {
                    o.checkCollision(o2);
                }
            }
            o.update();
        }
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(background,0,0,null);
        for(GameObject o: objects){
            o.draw(canvas);
        }
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    gsm.setState(gsm.LEVELSELECT);
                }
                //TODO going back to level select does not yet reset the levels that were being played.
                else{
                    henk.setX(x);
                    henk.setY(y);
                    henk.stop();
                }
                break;
        }
        return true;
    }


}