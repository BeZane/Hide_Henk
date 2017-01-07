package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.activities.ActivityManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.activities.NameActivity;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;

/**
 * Created by TomVerschueren on 31/12/2016.
 */

public class Building3State extends GameState {

    private LevelWrapper levelWrapper;

    public Building3State(GameStateManager gsm){
        this.gsm = gsm;

    }

    public void setLevelWrapper(LevelWrapper levelWrapper){
        this.levelWrapper = levelWrapper;
    }

    @Override
    protected void init() {
        //Game.setNameLayout(true);
        System.out.println("SENDING");

    }

    @Override
    protected void update() {
      //  if(!Game.getNameLayout()){
        //    levelWrapper.setName(Game.getInputName());
        //}
        System.out.println("HEY");
        if(levelWrapper !=null && NameActivity.done) {
            ActivityManager.getInstance().startActivity(0);
            levelWrapper.setName(NameActivity.NAME);
            DatabaseManager.request(UrlRequest.insertLevel(NameActivity.NAME, levelWrapper.getStringID()));
            System.out.println("INSERTING");
            gsm.setState(GameStateManager.BUILDERMENU);
            NameActivity.done = false;
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
