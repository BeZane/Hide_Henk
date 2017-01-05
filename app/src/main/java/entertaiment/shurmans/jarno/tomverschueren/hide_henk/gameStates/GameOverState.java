package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;

/**
 * Created by Admin on 5/01/2017.
 */

public class GameOverState extends GameState {

    private String[] options = {"Retry", "Level Select", "Main Menu"};
    private ArrayList<MenuButton> menuButtons = new ArrayList<MenuButton>();

    public GameOverState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){
        for(int i = 0; i < options.length; i++){
            MenuButton menuButton = new MenuButton();
            menuButton.setX(GamePanel.SCREEN_WIDTH / 2);
            menuButton.setY((int)(160 * GamePanel.Y_SCALE + i * (menuButton.getPicture().getHeight() + 35 * GamePanel.Y_SCALE)));
            menuButton.setText(options[i]);
            menuButtons.add(menuButton);
        }
    }

    public void update(){}

    public void draw(Canvas canvas){
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
                            gsm.setState(GameStateManager.MENUSTATE);
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
