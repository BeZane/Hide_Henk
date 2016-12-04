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

    /**
     * Be sure that SCALING_FACTOR_X and SCALING_FACTOR_Y is intialized correctly before using this method!
     * @param bitmap Put the bitmap in here that needs to be scaled
     * @return a scaled bitmap
     */
    protected Bitmap scaleBitMap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()* GamePanel.SCALING_FACTOR_X), (int)(bitmap.getHeight() * GamePanel.SCALING_FACTOR_Y),false);
    }

    /**
     * If your bitmap needs to stay squared, use this method
     * @param bitmap
     * @return
     */
    protected Bitmap scaleSquared(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()* GamePanel.SCALING_FACTOR_X), (int)(bitmap.getHeight() * GamePanel.SCALING_FACTOR_X),false);
    }



}
