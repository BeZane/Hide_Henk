package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;

/**
 * Created by Admin on 5/01/2017.
 */

public class LevelCompleted extends MenuState{

    public LevelCompleted(GameStateManager gsm){
        super(gsm);
        options[0] = "Next Level";
        options[1] = "Replay Level";
        options[2] = "Level Select";
    }

    public void init(){
        super.init();
    }

    public void update(){
        super.init();
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                //check if we pressed a button in the main menu
                int i = 0;
                for(MenuButton b : menuButtons){
                    if(b.contains(x, y)){
                        if(i == 0){
                            //TODO go to next level
                        }
                        else if(i == 1){
                            LevelState levelState =(LevelState) gsm.setState(GameStateManager.ONLINELEVEL);
                            levelState.populate();
                        }
                        else if(i == 2){
                            gsm.setState(GameStateManager.LEVELSELECT);
                        }
                    }
                    i++;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
        }
        return true;
    }

}
