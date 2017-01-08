package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
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

    public ArrayList<GameObject> getPresetObjects() {
        return presetObjects;
    }

    public void setPresetObjects(ArrayList<GameObject> presetObjects) {
        this.presetObjects = presetObjects;
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<GameObject> Objects) {
        this.objects = Objects;
    }

    /**
     * LOAD THE WHOLE LEVEL INTO THE LEVELWRAPPER
     *
     * @param StringID ID from database of the level
     */
    public void loadFromString(String StringID) {
        System.out.println("STRINGID: " + StringID);
        String[] stringArray = StringID.split("!");
        setName(stringArray[0].replaceAll("=", " "));
        String[] PresetObjectsInfos = stringArray[1].split(";");
        for (String objectInfo : PresetObjectsInfos) {

            String[] presetObjectInfo = objectInfo.split(",");

            if (presetObjectInfo[0].equalsIgnoreCase(GameObject.Types.HENK.toString())) {
                Henk object = new Henk(Float.parseFloat(presetObjectInfo[3]), Float.parseFloat(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                System.out.println("TESTINGBOO");
                object.rescaleObject((int) (Math.ceil(Float.parseFloat(presetObjectInfo[1]) / GamePanel.X_SCALE)), (int) Math.ceil(Float.parseFloat(presetObjectInfo[2]) / GamePanel.Y_SCALE));
                System.out.println("TESTINGBO2");

                object.makeDirty();
                presetObjects.add(object);
            } else if (presetObjectInfo[0].equalsIgnoreCase(GameObject.Types.VERTICAL_PLANK.toString())) {
                VerticalPlank object = new VerticalPlank(Float.parseFloat(presetObjectInfo[3]), Float.parseFloat(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject((int) (Float.parseFloat(presetObjectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(presetObjectInfo[2]) / GamePanel.Y_SCALE));
                presetObjects.add(object);
            } else if (presetObjectInfo[0].equalsIgnoreCase(GameObject.Types.HORIZONTAL_PLANK.toString())) {
                System.out.println("TESTING3" + presetObjectInfo[3] + presetObjectInfo[4]);
                HorizontalPlank object = new HorizontalPlank(Float.parseFloat(presetObjectInfo[3]), Float.parseFloat(presetObjectInfo[4]));
                System.out.println("TESTING4");
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                System.out.println("TESTING5");
                object.rescaleObject((int) (Float.parseFloat(presetObjectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(presetObjectInfo[2]) / GamePanel.Y_SCALE));
                System.out.println("TESTING6");
                presetObjects.add(object);
                System.out.println("TESTING7");
            } else if (presetObjectInfo[0].equalsIgnoreCase(GameObject.Types.TIRE.toString())) {
                Tire object = new Tire(Float.parseFloat(presetObjectInfo[3]), Float.parseFloat(presetObjectInfo[4]));
                object.setSolid(Boolean.valueOf(presetObjectInfo[5]));
                object.rescaleObject((int) (Float.parseFloat(presetObjectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(presetObjectInfo[2]) / GamePanel.Y_SCALE));
                presetObjects.add(object);
            } else {

            }
        }
        System.out.println("TESTING");
        String[] objectsInfos = stringArray[2].split(";");
        for (String info : objectsInfos) {
            String[] objectInfo = info.split(",");

            if (objectInfo[0].equalsIgnoreCase(GameObject.Types.HENK.toString())) {
                Henk object = new Henk(Float.parseFloat(objectInfo[3]), Float.parseFloat(objectInfo[4]));
                object.setSolid(Boolean.valueOf(objectInfo[5]));
                object.rescaleObject((int) (Float.parseFloat(objectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(objectInfo[2]) / GamePanel.Y_SCALE));
                object.makeDirty();
                objects.add(object);
            } else if (objectInfo[0].equalsIgnoreCase(GameObject.Types.VERTICAL_PLANK.toString())) {
                VerticalPlank object = new VerticalPlank(Float.parseFloat(objectInfo[3]), Float.parseFloat(objectInfo[4]));
                object.setSolid(Boolean.valueOf(objectInfo[5]));
                object.rescaleObject((int) (Float.parseFloat(objectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(objectInfo[2]) / GamePanel.Y_SCALE));
                objects.add(object);
            } else if (objectInfo[0].equalsIgnoreCase(GameObject.Types.HORIZONTAL_PLANK.toString())) {
                HorizontalPlank object = new HorizontalPlank(Float.parseFloat(objectInfo[3]), Float.parseFloat(objectInfo[4]));
                object.setSolid(Boolean.valueOf(objectInfo[5]));
                object.rescaleObject((int) (Float.parseFloat(objectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(objectInfo[2]) / GamePanel.Y_SCALE));
                objects.add(object);
            } else if (objectInfo[0].equalsIgnoreCase(GameObject.Types.TIRE.toString())) {
                Tire object = new Tire(Float.parseFloat(objectInfo[3]), Float.parseFloat(objectInfo[4]));
                object.setSolid(Boolean.valueOf(objectInfo[5]));
                object.rescaleObject((int) (Float.parseFloat(objectInfo[1]) / GamePanel.X_SCALE), (int) (Float.parseFloat(objectInfo[2]) / GamePanel.Y_SCALE));
                objects.add(object);
            }
        }
        System.out.println("OBJECTS:" + objects.toString());
        System.out.println("PRESETOBJECTS: " + presetObjects.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringID() {
        ID = name.replaceAll(" ", "=") + "!";
        for (GameObject gameObject : presetObjects) {
            ID = ID + gameObject.getType().toString() + "," +
                    gameObject.getBitmap().getWidth() * GamePanel.X_SCALE + "," +
                    gameObject.getBitmap().getHeight() * GamePanel.Y_SCALE + "," +
                    gameObject.getX() + "," +
                    gameObject.getY() + "," +
                    gameObject.isPrepareSolid() + ";";
        }
        ID = ID + "!";
        for (GameObject gameObject : objects) {
            System.out.println("GAMEOBJECT: " + gameObject);
            ID = ID + gameObject.getType().toString() + "," +
                    gameObject.getBitmap().getWidth() * GamePanel.X_SCALE + "," +
                    gameObject.getBitmap().getHeight() * GamePanel.Y_SCALE + "," +
                    gameObject.getX() + "," +
                    gameObject.getY() + "," +
                    gameObject.isPrepareSolid() + ";";
        }
        return ID;
    }

    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }


}
