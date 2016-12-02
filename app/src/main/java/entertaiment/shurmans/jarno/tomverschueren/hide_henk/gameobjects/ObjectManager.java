package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects;

import java.util.ArrayList;

/**
 * Created by Admin on 2/12/2016.
 */

public class ObjectManager {

    private ArrayList<CircleObject> circles;

    public ObjectManager(){}

    public void addObjecet(GameObject object){
        if(object.getShape() == GameObject.collisionShape.CIRCLE){
            circles.add(object);
        }
    }


}
