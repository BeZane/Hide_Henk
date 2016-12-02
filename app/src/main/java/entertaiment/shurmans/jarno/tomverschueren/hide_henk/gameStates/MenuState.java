package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.SoundPool;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;


/**
 * Created by Admin on 1/12/2016.
 */

public class MenuState extends GameState{

    //layout
    private Bitmap background;
    private Bitmap menuButton;
    private String[] options = {"START", "STATS", "BUILDER"};


    //music
    private SoundPool sounds;

    //tom
    private Plank plank;

    public MenuState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        //load the background image and scale it to match the size of the screen
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.WIDTH, GamePanel.HEIGHT, false);
        menuButton = BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.menu_button);
        plank = new Plank(BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.plank));


    }

    public void update(){
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background,0,0,null);

        //drawing the buttons
        Paint p = new Paint();
        p.setTextSize(150);
        p.setColor(Color.DKGRAY);
        for(int i = 0; i < 3 ; i++){
            canvas.drawBitmap(menuButton, GamePanel.WIDTH / 2 - menuButton.getWidth() / 2 ,
                    50 + menuButton.getHeight() * i, null);
            canvas.drawText(options[i], GamePanel.WIDTH / 2 - options[i].length() * p.getTextSize() / 4,
                    menuButton.getHeight() * (i + 1), p);
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                //check if we pressed a button in the main menu
                if((GamePanel.WIDTH / 2 - menuButton.getWidth() / 2 < x) && (x  < GamePanel.WIDTH / 2 + menuButton.getWidth() / 2)){
                    if( y > 50 && y < menuButton.getHeight() + 50){
                        gsm.setState(gsm.LEVELSELECT);
                    }
                    if( y > menuButton.getHeight() + 50 && y < menuButton.getHeight() * 2 + 50){
                        gsm.setState(gsm.STATS);
                    }
                    if( y > menuButton.getHeight() * 2 + 50 && y < menuButton.getHeight() * 3 + 50){
                        gsm.setState(GameStateManager.BUILDER);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //plank.onDrag(event.getX(),event.getY());
                break;
            default:
        }
        return true;
    }



}
