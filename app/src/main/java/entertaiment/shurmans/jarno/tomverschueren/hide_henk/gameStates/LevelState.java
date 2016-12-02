package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This is the superclass of all the levels.
 */

public class LevelState extends GameState {

    public LevelState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){}

    public void update(){}

    public void draw(Canvas canvas){}

    public boolean onTouchEvent(MotionEvent event){


        return true;
    }


}