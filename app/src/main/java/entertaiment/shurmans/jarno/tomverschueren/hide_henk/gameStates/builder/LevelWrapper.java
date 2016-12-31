package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import java.lang.reflect.Array;
import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;

/**
 * Created by TomVerschueren on 31/12/2016.
 */

public class LevelWrapper {


    private ArrayList<GameObject> presetObjects = new ArrayList<>();
    private ArrayList<GameObject> objects = new ArrayList<>();
    private String name;
    private String ID = null;


    public void setPresetObjects(ArrayList<GameObject> presetObjects){
        this.presetObjects = presetObjects;
    }

    public ArrayList<GameObject> getPresetObjects(){
        return presetObjects;
    }

    public void setObjects(ArrayList<GameObject> Objects){
        this.objects = Objects;
    }

    public ArrayList<GameObject> getObjects(){
        return objects;
    }

    public void loadFromDatabase(String StringID){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void getStringID(){
        ID = name + "|";
        for(GameObject gameObject:presetObjects){
            ID = ID +gameObject.getType() + "," +
                    gameObject.getBitmap().getWidth() + "," +
                    gameObject.getBitmap().getHeight() + "," +
                    gameObject.getX() +"," +
                    gameObject.getY() + ","+
                    gameObject.isPrepareSolid() + ";";
        }
        ID = ID + "|";
        for(GameObject gameObject:objects){
            ID = ID +gameObject.getType() + "," +
                    gameObject.getBitmap().getWidth() + "," +
                    gameObject.getBitmap().getHeight() + "," +
                    gameObject.getX() +"," +
                    gameObject.getY() + ","+
                    gameObject.isPrepareSolid() + ";";
        }

    }



}
