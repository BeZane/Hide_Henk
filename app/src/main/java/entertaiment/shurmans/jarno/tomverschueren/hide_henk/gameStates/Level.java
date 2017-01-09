package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class Level extends LevelState {

    public static String lastLoadedID = "";



    public Level(GameStateManager gsm) {
        super(gsm);
    }




    public void populate(){

        LevelWrapper levelWrapper = new LevelWrapper();
        levelWrapper.loadFromString(lastLoadedID);
        scrollBar.clear();
        objects.clear();
        //System.out.println("POPULATING");
        scrollBar.addAllObjects(levelWrapper.getObjects());
        toPlace.addAll(levelWrapper.getObjects());
        System.out.println("SIZE:" +toPlace.size());
        System.out.println("X:" + levelWrapper.getPresetObjects().get(0).getX());
        //System.out.println("SCROLLINGBAROBJECTS: " + scrollBar.getObjects().toString());
        objects.addAll(levelWrapper.getPresetObjects());

        lastLoadedID = "";
        objectsLoaded = true;

    }






}
