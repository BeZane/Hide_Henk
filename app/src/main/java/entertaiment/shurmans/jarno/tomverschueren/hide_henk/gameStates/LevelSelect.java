package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by Admin on 2/12/2016.
 */

public class LevelSelect extends GameState {

    private Bitmap unlockedButton;
    private Bitmap background;

    //boolean to see if screen has been backgroundDrawn
    private boolean backgroundDrawn = false;

    public LevelSelect(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        unlockedButton = this.scaleSquared(BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.level_select_button));
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.WIDTH, GamePanel.HEIGHT, false);
    }

    public void update(){}

    public void draw(Canvas canvas){

        canvas.drawBitmap(background,0,0,null);

        //drawing the title
        Paint p = new Paint();
        p.setTextSize(40*GamePanel.SCALING_FACTOR_X);
        p.setColor(Color.DKGRAY);
        canvas.drawText("Level Select", GamePanel.WIDTH / 2 - 90*GamePanel.SCALING_FACTOR_X, 40*GamePanel.SCALING_FACTOR_Y, p);

        //setting the paint to draw the numbers of the levels.
        p.setTextSize(25*GamePanel.SCALING_FACTOR_X);
        p.setColor(Color.BLACK);

        //3 rows and 5 columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++) {
                //drawing the buttons
                canvas.drawBitmap(unlockedButton, (GamePanel.WIDTH/2 - unlockedButton.getWidth() * 4 + j * unlockedButton.getWidth() * 3 / 2),(
                        160*GamePanel.SCALING_FACTOR_X + unlockedButton.getHeight() * i * 3 / 2), null);
                //drawing the numbers on the buttons
                canvas.drawText("" + (i * 5 + j + 1),GamePanel.SCALING_FACTOR_Y*5+ GamePanel.WIDTH/2 - unlockedButton.getWidth() * 4 + j * unlockedButton.getWidth() * 3 / 2 + 10,
                        100*GamePanel.SCALING_FACTOR_Y + unlockedButton.getHeight() * i * 3 / 2, p);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        return true;
    }










}
