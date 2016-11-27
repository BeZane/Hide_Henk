package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by TomVerschueren on 27/11/2016.
 */

public abstract class RectangleObject extends GameObject {

    public RectangleObject(Bitmap bitmap) {
        super(bitmap);
    }

    public Rect getRectangle(){
        return new Rect(x,y,x+width,y+height);

    }
}
