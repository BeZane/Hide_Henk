package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.LevelSelectButton;

/**
 * Created by Admin on 2/12/2016.
 */

public class LevelSelect extends GameState {

    private LevelSelectButton unlockedButton;
    private Bitmap background;
    private Bitmap previous;

    //boolean to see if screen has been backgroundDrawn
    private boolean backgroundDrawn = false;

    public LevelSelect(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        unlockedButton = new LevelSelectButton();
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
    }

    protected void update(){}

    public void draw(Canvas canvas){

        canvas.drawBitmap(background,0,0,null);

        //drawing the title
        Paint p = new Paint();
        p.setTextSize(40*GamePanel.SCALING_FACTOR_X);
        p.setColor(Color.DKGRAY);
        canvas.drawText("Level Select", GamePanel.SCREEN_WIDTH / 2 - 90*GamePanel.SCALING_FACTOR_X, 40*GamePanel.SCALING_FACTOR_Y, p);

        //setting the paint to draw the numbers of the levels.
        unlockedButton.setTextSize((int)(25*GamePanel.X_SCALE));


        //3 rows and 5 columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++) {
                System.out.println("i: " + i + " j: " + j );
                //drawing the buttons
                unlockedButton.setText("" + (i * 5 + j + 1));
                unlockedButton.draw(canvas,(int) (GamePanel.SCREEN_WIDTH / 2 + unlockedButton.getPicture().getWidth() * (j - 2.5) * 1.5),
                        GamePanel.SCREEN_HEIGHT / 2 + (i - 1) * unlockedButton.getPicture().getHeight() * 3 / 2 );
            }
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
