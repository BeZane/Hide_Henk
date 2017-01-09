package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.CircleObject;

/**
 * Created by Admin on 9/01/2017.
 */

public class HayBale extends CircleObject {

    public HayBale(double x, double y){
        super(x,y);
        init();
    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {
        Bitmap newBitmap = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.hay_bale);
        picture = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false);
    }

    public void init(){
        super.init();
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.hay_bale);
        picture = Bitmap.createScaledBitmap(picture, 250, 250, false);
        radius = picture.getWidth() / 2;
        scalePicture(picture);
        type = Types.HAYBALE;
        density = 0.2;
        calculateMass();
    }

}
