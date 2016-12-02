package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;
import android.view.MotionEvent;


public abstract class GameState {

    protected GameStateManager gsm;

    protected abstract void init();
    protected abstract void update();
    protected abstract void draw(Canvas canvas);
    protected abstract boolean onTouchEvent(MotionEvent event);


}
