package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.CircleObject;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public class Henk extends CircleObject{

    public Henk(double x, double y) {
        super(x,y);
        init();
    }

    private void init(){
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.dirty_henk);
        radius = picture.getWidth() / 2;
        scalePicture(picture);
        type = Types.HENK;
    }

    public void cleanHenk(){
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.clean_henk);
        scalePicture(picture);
    }


}
