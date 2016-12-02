package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;


public class Builder extends GameState {

    //just for fun
    private float x;
    private float y;
    private Bitmap plank;

    public Builder (GameStateManager gsm){
        this.gsm = gsm;
    }


    public void init(){
         plank = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.plank);
    }


    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(plank, x, y, null);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX() - plank.getWidth() / 2;
                y = event.getY();
                break;
        }
        return true;
    }
}
