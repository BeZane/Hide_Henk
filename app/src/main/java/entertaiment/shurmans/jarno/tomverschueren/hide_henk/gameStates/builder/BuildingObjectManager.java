package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Objects;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;

/**
 * Created by TomVerschueren on 21/12/2016.
 */

public class BuildingObjectManager {

    private ArrayList<GameObject> objects = new ArrayList<>();



    public BuildingObjectManager(){


    }


    public void addObject(GameObject gameObject){
        objects.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        objects.remove( gameObject);
    }

    public void update() {
        for (GameObject object : objects) {
            object.update();
        }
    }



    public void draw(Canvas canvas){
        for(GameObject object:objects){
            System.out.print("drawing");
            object.draw(canvas);
        }


    }
}
