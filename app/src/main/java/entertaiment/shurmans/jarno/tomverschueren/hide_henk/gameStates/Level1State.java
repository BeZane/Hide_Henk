package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.view.MotionEvent;

import java.util.Iterator;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Tire;

/**
 * Created by Admin on 8/12/2016.
 */

public class Level1State extends LevelState {

    public Level1State(GameStateManager gsm){
        super(gsm);
    }


    @Override
    protected void populate(){
        //environment
        henk = new Henk(800, 300);
        Plank p1 = new Plank(740, 420);
        p1.setSolid(true);
        Plank p2 = new Plank(580, 600);
        p2.setSolid(true);
        Plank p3 = new Plank(420, 420);
        p3.setSolid(true);
        objects.add(henk);
        objects.add(p1);
        objects.add(p2);
        objects.add(p3);

        //objects to place
        Tire t = new Tire(1100, 0);
        toPlace.add(t);

    }

}
