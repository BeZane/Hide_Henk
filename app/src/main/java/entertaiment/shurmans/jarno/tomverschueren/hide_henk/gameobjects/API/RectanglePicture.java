package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by TomVerschueren on 4/12/2016.
 */

public class RectanglePicture extends RectangleObject {


    public RectanglePicture(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,x,y,null);
    }
}
