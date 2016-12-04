package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

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

    @Override
    public boolean touches(Point point) {
        if(this.x+50 > point.x && point.x < this.x-50
                && this.y+50 >point.y && point.y < this.y+50)
            return true;
        return false;
    }
}
