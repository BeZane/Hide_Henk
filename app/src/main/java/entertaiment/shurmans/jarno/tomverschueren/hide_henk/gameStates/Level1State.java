package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Barrel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Bricks;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HayBale;
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
    public void populate(){

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

        System.out.println("POPULATION");

        //objects to place
        Tire t = new Tire(1100, 0);
        Tire t2 = new Tire(1111, 11);
        Bricks bricks = new Bricks(1500, 0);
        HayBale hayBale = new HayBale(100, 200);
        Barrel barrel = new Barrel(1100, 0);
        toPlace.add(t);
        toPlace.add(bricks);
        toPlace.add(hayBale);
        toPlace.add(barrel);
        toPlace.add(t2);
        scrollBar.addObject(t);
        scrollBar.addObject(bricks);
        scrollBar.addObject(hayBale);
        scrollBar.addObject(barrel);
        scrollBar.addObject(t2);


    }

}
