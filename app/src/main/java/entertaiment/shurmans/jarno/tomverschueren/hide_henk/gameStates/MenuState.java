package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.Background;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;


/**
 * Created by Admin on 1/12/2016.
 */

public class MenuState extends GameState{

    //background stuff
    private Background background;
    private float bgWidth;
    private float bgHeight;

    private Plank plank;

    public MenuState(GameStateManager gsm){
        this.gsm = gsm;

    }

    public void init(){
        background = new Background(BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1));
        bgWidth = background.getWidth();
        bgHeight = background.getHeight();
        plank = new Plank(BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.plank2_resized));
    }

    public void update(){
        background.update();
    }

    public void draw(Canvas canvas){
        if(canvas!= null) {
            GamePanel.SCALING_FACTOR_X = GamePanel.WIDTH/bgWidth;
            GamePanel.SCALING_FACTOR_Y = GamePanel.HEIGHT/bgHeight;
            final int savedState = canvas.save();
            canvas.scale(GamePanel.SCALING_FACTOR_X,GamePanel.SCALING_FACTOR_Y);
            background.draw(canvas);
            plank.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                plank.onDrag(event.getX(),event.getY());
                break;
            default:
        }
        return true;
    }

}