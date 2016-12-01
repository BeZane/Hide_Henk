package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;



public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int STATS = 1;
    public static final int BUILDER = 2;

    public GameStateManager(){

        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Stats(this));
        gameStates.add(new Builder(this));
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

    public boolean onTouchEvent(MotionEvent event){
        return gameStates.get(currentState).onTouchEvent(event);
    }


}
