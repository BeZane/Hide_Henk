package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.CircleObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.Moveable;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public class Pig extends CircleObject implements Moveable {


    public Pig(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public boolean touches(Point point) {
        return false;
    }


    @Override
    public void onDrag(float x, float y) {

    }
}
