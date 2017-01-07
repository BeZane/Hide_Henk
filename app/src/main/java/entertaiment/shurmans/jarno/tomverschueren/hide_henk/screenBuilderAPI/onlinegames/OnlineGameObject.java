package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.onlinegames;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectangleObject;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class OnlineGameObject extends RectangleObject {

    private String text;


    public OnlineGameObject(double x, double y) {
        super(x, y);
        Bitmap picture = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.menu_button);
        picture = Bitmap.createScaledBitmap(picture, 1000, 164, false);
        scalePicture(picture);
        type = Types.SPECIAL;
        //density = 0.95;
        //calculateMass();
        setSolid(true);
    }

    @Override
    public void rescaleObject(int newWidth, int newHeight) {
        //TODO: NOT NEEDED NORMALLY
    }

    public void setText(String name){
        this.text = name;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(picture, (int)drawX, (int)drawY, null);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40*GamePanel.X_SCALE);
        //canvas.drawText(text, (float)x - text.length() * paint.getTextSize() / 4,(float) y + paint.getTextSize() / 4, paint);
        canvas.drawText(text,(float) GamePanel.SCREEN_WIDTH/2-text.length()*(paint.getTextSize()/4),(float) this.drawY+90*GamePanel.X_SCALE, paint);

    }

    public String getText(){
        return text;
    }


    //THIS OBJECT IS ONLY USED FOR ONLINEGAMESSCROLLBAR AND FOR NOTHING ELSE!! THIS OBJECT SHOULD NOT BE TREATED AS A NORMAL GAMEOBJECT



}
