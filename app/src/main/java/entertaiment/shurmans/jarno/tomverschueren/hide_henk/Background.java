package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by TomVerschueren on 24/11/2016.
 */

public class Background {

    private Bitmap image;
    private int x,y;

    public Background(Bitmap bitmap){
        image = bitmap;
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}
