package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectangleObject;

/**
 * Created by TomVerschueren on 4/12/2016.
 */

public class MenuButton extends RectangleObject {

    private String text;
    private int textSize;
    private int color;


    public MenuButton(Bitmap bitmap, String text) {
        super(bitmap);
        this.text = text;
        textSize = (int)(40*GamePanel.SCALING_FACTOR_X);
        color = Color.DKGRAY;
    }

    @Override
    public void update() {

    }

    public int getTextColor() {
        return color;
    }

    public void setTextColor(int color) {
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,x,y,null);
        Paint p = new Paint();
        p.setColor(color);
        p.setTextSize(textSize);
        canvas.drawText(text, x+90*GamePanel.SCALING_FACTOR_X,y+80*GamePanel.SCALING_FACTOR_Y, p);

        //setting the paint to draw the numbers of the levels.
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
