package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.Game;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;

/**
 * Created by TomVerschueren on 31/12/2016.
 */

public class Building3State extends GameState {

    private LevelWrapper levelWrapper;

    public Building3State(GameStateManager gsm){
        this.gsm = gsm;
        Game.setNameLayout(true);

    }

    public void setLevelWrapper(LevelWrapper levelWrapper){
        this.levelWrapper = levelWrapper;
    }

    @Override
    protected void init() {
        Game.setNameLayout(true);
    }

    @Override
    protected void update() {
        if(!Game.getNameLayout()){
            levelWrapper.setName(Game.getInputName());
        }
    }

    @Override
    protected void draw(Canvas canvas) {

    }

    @Override
    protected boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
