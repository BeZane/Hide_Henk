package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class OnlineLevel extends LevelState {

    public static String lastLoadedID = "";



    public OnlineLevel(GameStateManager gsm) {
        super(gsm);
    }




    public void populate(){
        LevelWrapper levelWrapper = new LevelWrapper();
        levelWrapper.loadFromString(lastLoadedID);
        scrollBar.clear();
        objects.clear();
        scrollBar.setObjects(levelWrapper.getObjects());
        objects.addAll(levelWrapper.getPresetObjects());
        lastLoadedID = "";
    }




}
