package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import java.lang.reflect.Array;
import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HorizontalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Tire;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.VerticalPlank;

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
            String[] stringArray = StringID.split("!");
            setName(stringArray[0]);
            String[] presetObjectInfo = stringArray[1].split(";");
            if(presetObjectInfo[0] ==GameObject.Types.HENK.toString()){
                Henk object = new Henk(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                presetObjects.add(object);
            }else if(presetObjectInfo[0] ==GameObject.Types.VERTICAL_PLANK.toString()){
                VerticalPlank object = new VerticalPlank(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                presetObjects.add(object);
            }else if(presetObjectInfo[0] ==GameObject.Types.HORIZONTAL_PLANK.toString()){
                HorizontalPlank object = new HorizontalPlank(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                presetObjects.add(object);
            }else if(presetObjectInfo[0] ==GameObject.Types.TIRE.toString()){
                Tire object = new Tire(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                presetObjects.add(object);
            }
            String[] objectInfo = stringArray[1].split(";");
            if(objectInfo[0] ==GameObject.Types.HENK.toString()){
                Henk object = new Henk(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                objects.add(object);
        }else if(objectInfo[0] ==GameObject.Types.VERTICAL_PLANK.toString()){
            VerticalPlank object = new VerticalPlank(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
            object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
            object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                objects.add(object);
        }else if(objectInfo[0] ==GameObject.Types.HORIZONTAL_PLANK.toString()){
            HorizontalPlank object = new HorizontalPlank(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
            object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
            object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
                objects.add(object);
        }else if(objectInfo[0] ==GameObject.Types.TIRE.toString()){
            Tire object = new Tire(Integer.parseInt(presetObjectInfo[3]),Integer.parseInt(presetObjectInfo[4]));
            object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
            object.rescaleObject(Integer.parseInt(presetObjectInfo[1]),Integer.parseInt(presetObjectInfo[2]));
            objects.add(object);
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getStringID(){
        ID = name + "!";
        for(GameObject gameObject:presetObjects){
            ID = ID +gameObject.getType().toString() + "," +
                    gameObject.getBitmap().getWidth() + "," +
                    gameObject.getBitmap().getHeight() + "," +
                    gameObject.getX() +"," +
                    gameObject.getY() + ","+
                    gameObject.isPrepareSolid() + ";";
        }
        ID = ID + "!";
        for(GameObject gameObject:objects){
            System.out.println("GAMEOBJECT: " + gameObject);
            ID = ID +gameObject.getType().toString() + "," +
                    gameObject.getBitmap().getWidth() + "," +
                    gameObject.getBitmap().getHeight() + "," +
                    gameObject.getX() +"," +
                    gameObject.getY() + ","+
                    gameObject.isPrepareSolid() + ";";
        }
        return ID;
    }

    public void addObject(GameObject gameObject){
        objects.add(gameObject);
    }


}
