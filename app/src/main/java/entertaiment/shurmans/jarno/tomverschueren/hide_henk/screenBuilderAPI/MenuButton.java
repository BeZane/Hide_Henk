package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by Admin on 8/12/2016.
 */

public class MenuButton extends Button {

    public MenuButton(){
        super();
        Bitmap tempPicture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.menu_button);
        tempPicture = Bitmap.createScaledBitmap(tempPicture, 328, 164, false);
        scaleToScreensize(tempPicture);
        textSize = (int)(60* GamePanel.X_SCALE);
    }

}
