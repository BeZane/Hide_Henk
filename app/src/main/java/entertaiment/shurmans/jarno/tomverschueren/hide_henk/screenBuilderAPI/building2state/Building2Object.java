package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.building2state;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;

/**
 * Created by TomVerschueren on 30/12/2016.
 */

public class Building2Object extends GameObject {



    /**
     *
     * THIS CLASS SHOULD NOT BE USED AS GAMEOBJECT IT EXTENDS GAMEOBJET SINCE SCROLLBAR NEEDS IT. THIS IS A SPECIAL GAMEOBJECT
     * ESPECIALLY MADE FOR BUILDING2STATE. DON'T USE IT ELSEWHERE!
     */


    public Building2Object(double x, double y) {
        super(x, y);


    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {

    }

    @Override
    public boolean contains(float x, float y) {
        return false;
    }


    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

    }
}
