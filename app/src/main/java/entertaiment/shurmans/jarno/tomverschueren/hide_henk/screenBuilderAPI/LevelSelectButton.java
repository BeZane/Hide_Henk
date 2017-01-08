package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by Admin on 8/12/2016.
 */

public class LevelSelectButton extends Button{

    private boolean unlocked = false;

    public LevelSelectButton(){
        super();
        textSize = (int)(30* GamePanel.X_SCALE);
        Bitmap tempPicture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.level_select_button); //TODO: Replace this with locked button

        tempPicture = Bitmap.createScaledBitmap(tempPicture, 90, 90, false);
        scaleToScreensize(tempPicture);
    }

    public void unlockButton(boolean b){
        unlocked = b;
        if(unlocked){
            Bitmap tempPicture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.level_select_button);
            tempPicture = Bitmap.createScaledBitmap(tempPicture, 90, 90, false);
            scaleToScreensize(tempPicture);
        }
        else{
            Bitmap tempPicture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.level_select_button); //TODO: Replace this with locked one
            tempPicture = Bitmap.createScaledBitmap(tempPicture, 90, 90, false);
            scaleToScreensize(tempPicture);
        }
    }

    public boolean isUnlocked(){
        return unlocked;
    }

}
