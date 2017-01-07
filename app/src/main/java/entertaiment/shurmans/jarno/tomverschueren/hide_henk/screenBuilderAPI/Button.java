package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;

/**
 * Created by TomVerschueren on 4/12/2016.
 */

public class Button {

    protected String text;
    protected int textSize;
    protected int color;
    protected int x;
    protected int y;
    protected Bitmap picture;


    public Button() {
        color = Color.DKGRAY;
        text = "";
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setTextSize(int size){textSize = size;}
    public void setText(String text){
        this.text = text;
    }

    public Bitmap getPicture(){
        return picture;
    }
    public int getY(){return y;}
    public int getX(){return x;}

    protected void scaleToScreensize(Bitmap picture){
        int destWidth = (int)(picture.getWidth() * GamePanel.X_SCALE);
        int destHeight = (int) (picture.getHeight() * GamePanel.Y_SCALE);
        this.picture = Bitmap.createScaledBitmap(picture, destWidth, destHeight, false);
    }

    public boolean contains(float x, float y){

        boolean contains = false;

        if(x > this.x - picture.getWidth() / 2 && x < this.x + picture.getWidth() / 2 &&
                y > this.y - picture.getHeight() / 2 && y < this.y + picture.getHeight() ){
            contains = true;
        }

        return contains;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(picture, x - picture.getWidth() / 2, y - picture.getHeight() / 2, null);
        if(text.equalsIgnoreCase("")|| text == null)
            return;
        Paint p = new Paint();
        p.setColor(color);
        p.setTextSize(textSize);
        canvas.drawText(text, x - text.length() * textSize / 4, y + textSize / 4, p);
    }

}
