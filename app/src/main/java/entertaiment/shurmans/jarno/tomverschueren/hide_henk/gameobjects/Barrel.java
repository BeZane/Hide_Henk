package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectangleObject;

/**
 * Created by Admin on 9/01/2017.
 */

public class Barrel extends RectangleObject {


    public Barrel(double x, double y){
        super(x,y);
        init();
    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {
        Bitmap newBitmap = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.barrel);
        picture = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false);
    }

    public void init(){
        super.init();
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.barrel);
        picture = Bitmap.createScaledBitmap(picture, 80, 160, false);
        calculateDimensions(picture);
        scalePicture(picture);
        type = Types.BARREL;
        density = 1.1;
        calculateMass();
    }


}
