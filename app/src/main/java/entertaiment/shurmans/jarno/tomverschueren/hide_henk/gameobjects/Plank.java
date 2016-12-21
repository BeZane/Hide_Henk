package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectangleObject;

/**
 * Created by Admin on 8/12/2016.
 */

public class Plank extends RectangleObject {

    public Plank(double x, double y){
        super(x,y);
        init();
    }

    private void init(){
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.plank);
        picture = Bitmap.createScaledBitmap(picture, 160, 24, false);
        calculateDimensions(picture);
        scalePicture(picture);
        type = Types.PLANK;
        density = 0.9;
        calculateMass();
    }


}