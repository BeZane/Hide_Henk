package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;


/**
 * Created by Admin on 1/12/2016.
 */

public class MenuState extends GameState{

    //layout
    private Bitmap background;
    private String[] options = {"START", "STATS", "BUILDER"};
    private ArrayList<MenuButton> menuButtons = new ArrayList<MenuButton>();



    public MenuState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
        //load the background image and scale it to match the size of the screen
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        if(this.menuButtons.isEmpty())
            addButtons();
    }

    private void addButtons(){
        for(int i = 0; i < options.length; i++){
            MenuButton menuButton = new MenuButton();
            menuButton.setX(GamePanel.SCREEN_WIDTH / 2);
            menuButton.setY((int)(160 * GamePanel.Y_SCALE + i * (menuButton.getPicture().getHeight() + 35 * GamePanel.Y_SCALE)));
            menuButton.setText(options[i]);
            menuButtons.add(menuButton);
        }
    }

    public void update(){
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background, 0, 0, null);

        for (MenuButton b: menuButtons){
            b.draw(canvas);
        }
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
