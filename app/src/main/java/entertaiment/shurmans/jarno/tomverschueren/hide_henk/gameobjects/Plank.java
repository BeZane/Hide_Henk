package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.Moveable;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectangleObject;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public class Plank extends RectangleObject implements Moveable {





    public Plank(Bitmap bitmap) {
        super(bitmap);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);

    }

    @Override
    public void onDrag(float newX,float newY) {
      x = (int) (newX/ GamePanel.SCALING_FACTOR_X)-getWidth()/2;
        y = (int) (newY/GamePanel.SCALING_FACTOR_Y) - getHeight()/2;

    }
}
