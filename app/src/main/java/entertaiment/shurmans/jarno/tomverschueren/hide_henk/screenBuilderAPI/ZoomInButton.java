package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by TomVerschueren on 28/12/2016.
 */

public class ZoomInButton extends Button {

    public ZoomInButton(){
        super();
        Bitmap tempPicture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.zoom_in);
        tempPicture = Bitmap.createScaledBitmap(tempPicture, 100, 100, false);
        scaleToScreensize(tempPicture);
        setText("");
    }
}
