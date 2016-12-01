package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Admin on 1/12/2016.
 */

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;

    public GameStateManager(){

        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.get(currentState).init();
    }

    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void draw(Canvas canvas){
        gameStates.get(currentState).draw(canvas);
    }




}
