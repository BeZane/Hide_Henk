package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class Level extends LevelState {

    public static String lastLoadedID = "";
    private ArrayList<String> offlineLevelIDs = new ArrayList<>();


    public Level(GameStateManager gsm) {
        super(gsm);
        offlineLevelIDs.add("1!HORIZONTAL_PLANK,473.6,66.0,456.0,589.5,true;HORIZONTAL_PLANK,160.0,24.0,758.4000244140625,417.0,true;HORIZONTAL_PLANK,160.0,24.0,458.3999938964844,414.0,true;HENK,99.2,99.0,688.0,507.0,false;!TIRE,200,200,400.0,240.0,false");
        offlineLevelIDs.add("2!HORIZONTAL_PLANK,784.0,105.0,221.25741577148438,594.0,true;HENK,99.2,99.0,616.0397338867188,522.0,false;VERTICAL_PLANK,24.0,159.0,985.5999755859375,279.0,true;VERTICAL_PLANK,24.0,159.0,220.91195678710938,283.5,true;VERTICAL_PLANK,24.0,159.0,217.60000610351563,430.4234924316406,true;VERTICAL_PLANK,24.0,159.0,217.10009765625,435.0,true;!TIRE,200.0,199.5,0.0,0.0,false;TIRE,200.0,199.5,0.0,0.0,false;TIRE,200.0,199.5,0.0,0.0,false;TIRE,200.0,199.5,0.0,0.0,false");
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

    public void setLastLoadedID(int level){
        System.out.println("LEVEL: " + offlineLevelIDs.get(level));
        lastLoadedID = offlineLevelIDs.get(level);
    }






}
