package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;

/**
 * Created by Admin on 5/01/2017.
 */

public class GameOver extends MenuState {



    public GameOver(GameStateManager gsm){
        super(gsm);
        options[0] = "Retry";
        options[1] = "Level Select";
        options[2] = "Main Menu";
    }

    public void init(){
        super.init();
    }

    public void update(){
        super.update();
    }

    public void draw(Canvas canvas){
       super.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                //check if we pressed a button in the main menu
                int i = 0;
                System.out.println("the list of menubuttons is this: " + menuButtons);
                for(MenuButton b : menuButtons){
                    if(b.contains(x, y)){
                        if(i == 0){
                            gsm.setState(GameStateManager.LEVEL1);
                        }
                        else if(i == 1){
                            gsm.setState(GameStateManager.LEVELSELECT);
                        }
                        else if(i == 2){
                            gsm.setState(GameStateManager.MAINMENU);
                        }
                    }
                    i++;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
        }

        return true;
    }

}
