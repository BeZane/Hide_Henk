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

    private void init(){
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.water_drop);
        radius = picture.getWidth() / 2;
        scalePicture(picture);
        type = Types.WATERDROP;
    }

    @Override
    public void update(){
        super.update();
        timeLived++;
    }

}
