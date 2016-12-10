package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by Admin on 1/12/2016.
 */

public class Stats extends GameState {

    private Bitmap previous;

    public Stats (GameStateManager gsm){
        this.gsm = gsm;
    }


    public void init(){
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
    }


    public void update(){

    }

    public void draw(Canvas canvas){
        //drawing the return button
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    gsm.setState(gsm.MENUSTATE);
                }
                break;
        }
        return true;
    }
}
