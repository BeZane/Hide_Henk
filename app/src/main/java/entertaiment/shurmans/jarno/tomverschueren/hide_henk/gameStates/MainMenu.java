package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;
import android.view.Menu;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;

/**
 * Created by Admin on 8/01/2017.
 */

public class MainMenu extends MenuState {


    public MainMenu(GameStateManager gsm){
        super(gsm);
        options[0] = "START";
        options[1] = "STATS";
        options[2] = "BUILDER";
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
                for(MenuButton b : menuButtons){
                    if(b.contains(x, y)){
                        if(i == 0){
                            gsm.setState(GameStateManager.LEVELSELECT);
                        }
                        else if(i == 1){
                            gsm.setState(GameStateManager.STATS);
                            DatabaseManager.request(UrlRequest.getStats());
                        }
                        else if(i == 2){
                            gsm.setState(GameStateManager.BUILDERMENU);
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
