package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.CircleObject;

/**
 * Created by Admin on 17/12/2016.
 */

public class Tire extends CircleObject {

    public Tire(double x, double y){
        super(x,y);
        init();
    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {
        Bitmap newBitmap = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.tire);
        picture = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false);
        radius = newWidth/2;

    }

    public void init(){
        super.init();
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.tire);
        picture = Bitmap.createScaledBitmap(picture, 200, 200, false);
        radius = picture.getWidth() / 2;
        scalePicture(picture);
        type = Types.TIRE;
        density = 1.5;
        calculateMass();

    }

}
