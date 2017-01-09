package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectangleObject;

/**
 * Created by Admin on 8/12/2016.
 */

public class VerticalPlank extends RectangleObject {

    public VerticalPlank(double x, double y){
        super(x,y);
        init();
    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {
        Bitmap newBitmap = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.vertical_plank);
        picture = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false);
    }

    public void init(){
        super.init();
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.vertical_plank);
        picture = Bitmap.createScaledBitmap(picture, 24, 160, false);
        calculateDimensions(picture);
        scalePicture(picture);
        type = Types.VERTICAL_PLANK;
        density = 0.9;
        calculateMass();
    }


}