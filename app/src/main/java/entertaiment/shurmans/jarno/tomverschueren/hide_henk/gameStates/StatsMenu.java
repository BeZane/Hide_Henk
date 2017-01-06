package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.HashMap;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.Util.StatType;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.Util.Stats;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;

/**
 * Created by Admin on 1/12/2016.
 */

public class StatsMenu extends GameState {

    private Bitmap previous;
    private Bitmap background;
    private ScrollBar scrollBar;
    private HashMap<String,Integer> stats;

    public StatsMenu(GameStateManager gsm){
        this.gsm = gsm;
    }


    public void init(){
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
        previous = Bitmap.createScaledBitmap(previous, 140, 140, false);



    }


    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background,0,0,null);
        //drawing the return button
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);
        int x=200,y=200;
        for(StatType statType:StatType.values()) {
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            p.setTextSize(80 * GamePanel.X_SCALE);
            y = (int) (y+100*GamePanel.X_SCALE);
            canvas.drawText(statType.toShowString() + ":  " + Stats.getStat(statType), x,y, p);
        }

    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    stats = null;
                    gsm.setState(gsm.MENUSTATE);
                }
                break;
        }
        return true;
    }
}
