package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;

/**
 * Created by Admin on 8/12/2016.
 */

public class Level1State extends LevelState {

    public Level1State(GameStateManager gsm){
        super(gsm);
        populate();
    }

    public void populate(){
        henk = new Henk(905, 350);
        Plank p1 = new Plank(800, 450);
        p1.setSolid(true);
        Plank p2 = new Plank(510, 450);
        p2.setSolid(true);
        objects.add(henk);
        objects.add(p1);
        objects.add(p2);
    }

}
