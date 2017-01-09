package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;


public abstract class GameState {

    protected GameStateManager gsm;

    protected abstract void init();
    protected abstract void update();
    protected abstract void draw(Canvas canvas);
    protected abstract boolean onTouchEvent(MotionEvent event);

}
