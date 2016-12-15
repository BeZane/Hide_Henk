package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.LevelSelectButton;

/**
 * Created by Admin on 2/12/2016.
 */

public class LevelSelect extends GameState {

    private ArrayList<LevelSelectButton> unlockedButtons = new ArrayList<LevelSelectButton>();
    private Bitmap background;
    private Bitmap previous;


    public LevelSelect(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
        addButtons();
    }


    private void addButtons(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++) {
                //drawing the buttons
                LevelSelectButton unlockedButton = new LevelSelectButton();
                unlockedButton.setTextSize((int)(40*GamePanel.X_SCALE));
                unlockedButton.setText("" + (i * 5 + j + 1));
                unlockedButton.setX((int) (GamePanel.SCREEN_WIDTH / 2 + unlockedButton.getPicture().getWidth() * (j - 2.5) * 1.5));
                unlockedButton.setY(GamePanel.SCREEN_HEIGHT / 2 + (i - 1) * unlockedButton.getPicture().getHeight() * 3 / 2 );
                unlockedButtons.add(unlockedButton);
            }
        }
    }

    protected void update(){}

    public void draw(Canvas canvas){

        canvas.drawBitmap(background,0,0,null);

        //drawing the title
        Paint p = new Paint();
        p.setTextSize(100 * GamePanel.X_SCALE);
        p.setColor(Color.DKGRAY);
        canvas.drawText("Level Select", GamePanel.SCREEN_WIDTH / 2 - p.getTextSize() * "Level Select".length() / 4, 100 * GamePanel.Y_SCALE, p);
        //setting the paint to draw the numbers of the levels.


        //3 rows and 5 columns
        for(LevelSelectButton b: unlockedButtons){
            b.draw(canvas);
        }

        //drawing the return button
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, p);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    gsm.setState(gsm.MENUSTATE);
                }

                if(x < GamePanel.SCREEN_WIDTH / 2 && y < GamePanel.SCREEN_HEIGHT / 2){
                    gsm.setState(gsm.LEVEL1);
                }
                break;
        }
        return true;
    }








}
