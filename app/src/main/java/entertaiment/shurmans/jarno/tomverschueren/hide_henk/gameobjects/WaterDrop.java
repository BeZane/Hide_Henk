package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.CircleObject;

/**
 * Created by Admin on 13/12/2016.
 */

public class WaterDrop extends CircleObject{
    private static int timeToLive = 200;
    private int timeLived;

    public WaterDrop(double x, double y){
        super(x, y);
        init();
    }

    public int getTimeToLive(){return timeToLive;};
    public int getTimeLived(){return timeLived;};

    public void init(){
        super.init();
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.water_drop);
        picture = Bitmap.createScaledBitmap(picture, 30, 30, false);
        radius = picture.getWidth() / 2;
        scalePicture(picture);
        type = Types.WATERDROP;
        density = 5;
        calculateMass();
    }

    @Override
    public void update(){
        super.update();
        timeLived++;
    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {
        Bitmap newBitmap = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.water_drop);
        picture = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false);
        radius = picture.getWidth() / 2;
    }

}
