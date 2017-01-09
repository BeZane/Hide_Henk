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

    private boolean cleaned = false;

    public Henk(double x, double y) {
        super(x,y);
        init();
    }

    public boolean isCleaned(){
        return cleaned;
    }

    public void init(){
        super.init();
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.dirty_henk);
        picture = Bitmap.createScaledBitmap(picture, 100, 100, false);
        radius = picture.getWidth() / 2;
        scalePicture(picture);
        type = Types.HENK;
        density = 0.95;
        calculateMass();
    }

    public void makeDirty(){
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.dirty_henk);
        picture = Bitmap.createScaledBitmap(picture, 100, 100, false);
        cleaned = false;
    }

    public void cleanHenk(){
        if(cleaned) return;
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.clean_henk);
        picture = Bitmap.createScaledBitmap(picture, 100, 100, false);
        scalePicture(picture);
        cleaned = true;
    }

    public void rescaleObject(int newWidth,int newHeight){

        System.out.println("IN RESCALING");
        Bitmap newBitmap = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.dirty_henk);
        this.picture = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false);

}


}
