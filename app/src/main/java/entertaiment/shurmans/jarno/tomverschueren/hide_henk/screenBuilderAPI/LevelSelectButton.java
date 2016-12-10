package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by Admin on 8/12/2016.
 */

public class LevelSelectButton extends Button{

    public LevelSelectButton(){
        super();
        textSize = (int)(30* GamePanel.X_SCALE);
        Bitmap tempPicture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.level_select_button);
        scaleToScreensize(tempPicture);
    }

}
