package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HorizontalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Tire;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.VerticalPlank;

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
        henk = new Henk(650, 520);
        henk.setSolid(false);
        HorizontalPlank p1 = new HorizontalPlank(740, 420);
        p1.setSolid(true);
        HorizontalPlank p2 = new HorizontalPlank(580, 600);
        p2.setSolid(true);
        HorizontalPlank p3 = new HorizontalPlank(420, 420);
        p3.setSolid(true);
        VerticalPlank p4 = new VerticalPlank(910, 260);
        p4.setSolid(true);
        objects.add(henk);
        objects.add(p1);
        objects.add(p2);
        objects.add(p3);
        objects.add(p4);



        //objects to place
        Tire t = new Tire(1100, 0);
        toPlace.add(t);

    }

}
