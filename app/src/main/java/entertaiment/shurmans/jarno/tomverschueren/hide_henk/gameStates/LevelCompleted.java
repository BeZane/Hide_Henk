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
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;

/**
 * Created by Admin on 5/01/2017.
 */

public class LevelCompleted extends GameState{

    private Bitmap background;
    private ArrayList<MenuButton> menuButtons = new ArrayList<>();
    private String[] options = {"Next Level", "Replay level", "Level Select"};

    public LevelCompleted(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){
        for(int i = 0; i < options.length; i++){
            MenuButton b = new MenuButton();
            b.setX(GamePanel.SCREEN_WIDTH / 2);
            b.setY((int)(160 * GamePanel.Y_SCALE + i * (b.getPicture().getHeight() + 35 * GamePanel.Y_SCALE)));
            b.setText(options[i]);
            menuButtons.add(b);
            Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
            background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        }
    }

    public void update(){
        System.out.println("UPDATING LEVELCOMPLETED");
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(background,0,0,null);

        for(MenuButton b: menuButtons){
            b.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                //check if we pressed a button in the main menu
                int i = 0;
                for(MenuButton b : menuButtons){
                    if(b.contains(x, y)){
                        if(i == 0){
                            //TODO go to next level
                        }
                        else if(i == 1){
                            //TODO replay the level
                        }
                        else if(i == 2){
                            gsm.setState(GameStateManager.LEVELSELECT);
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
