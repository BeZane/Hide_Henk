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
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.options.Preferences;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.LevelSelectButton;

/**
 * Created by Admin on 2/12/2016.
 */

public class LevelSelect extends GameState {

    private ArrayList<LevelSelectButton> buttons = new ArrayList<LevelSelectButton>();
    private Bitmap background;
    private Bitmap previous;


    public LevelSelect(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
        previous = Bitmap.createScaledBitmap(previous, 140, 140, false);
        addButtons();
    }


    private void addButtons(){
        for(int i = 0; i < 5; i++) {
            //drawing the buttons
            LevelSelectButton unlockedButton = new LevelSelectButton();
            unlockedButton.setTextSize((int)(40*GamePanel.X_SCALE));
            if(Preferences.levelsUnlocked[i]) {
                unlockedButton.setText("" + (i + 1));
            }
            unlockedButton.setX((int) (GamePanel.SCREEN_WIDTH / 2 + unlockedButton.getPicture().getWidth() * (i - 2.5) * 1.5));
            unlockedButton.setY(GamePanel.SCREEN_HEIGHT / 2 );
            unlockedButton.unlockButton(Preferences.levelsUnlocked[i]);
            buttons.add(unlockedButton);
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
        int i = 0;
        for(LevelSelectButton b: buttons){
            i++;
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
                    gsm.setState(gsm.MAINMENU);
                }
                int i = 0;
                for(LevelSelectButton b : buttons){
                    if(b.contains(x,y) && i == 0){
                        gsm.setState(GameStateManager.LEVEL1);
                    }
                    if(b.contains(x,y) && b.isUnlocked()){
                       //TODO Load the i-nd level
                    }
                    i++;
                }

                break;
        }
        return true;
    }








}
