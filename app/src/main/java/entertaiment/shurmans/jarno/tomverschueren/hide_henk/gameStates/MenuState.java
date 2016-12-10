package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;


/**
 * Created by Admin on 1/12/2016.
 */

public class MenuState extends GameState{

    //layout
    private Bitmap background;
    private String[] options = {"START", "STATS", "BUILDER"};
    private MenuButton menuButton;



    public MenuState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        //load the background image and scale it to match the size of the screen
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        menuButton = new MenuButton();

    }

    public void update(){
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background, 0, 0, null);
        for(int i = 0; i < options.length; i++){
            menuButton.setText(options[i]);
            menuButton.draw(canvas, GamePanel.SCREEN_WIDTH / 2, (int)(160 + i * (menuButton.getPicture().getHeight() + 35) * GamePanel.Y_SCALE));
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                //check if we pressed a button in the main menu
                if((GamePanel.SCREEN_WIDTH / 2 - menuButton.getPicture().getWidth() / 2 < x) && (x  < GamePanel.SCREEN_WIDTH / 2 + menuButton.getPicture().getWidth() / 2)){
                    if( y > 50 && y < menuButton.getPicture().getHeight() + 50){
                        gsm.setState(gsm.LEVELSELECT);
                    }
                    if( y > menuButton.getPicture().getHeight() + 50 && y < menuButton.getPicture().getHeight() * 2 + 50){
                        gsm.setState(gsm.STATS);
                    }
                    if( y > menuButton.getPicture().getHeight() * 2 + 50 && y < menuButton.getPicture().getHeight() * 3 + 50){
                        //gsm.setState(GameStateManager.BUILDERMENU );
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
        }

        return true;
    }



}
