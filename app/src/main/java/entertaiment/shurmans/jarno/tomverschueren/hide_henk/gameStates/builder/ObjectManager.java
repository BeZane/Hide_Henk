package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;

/**
 * Created by TomVerschueren on 21/12/2016.
 */

public class ObjectManager {

    private Queue<GameObject> objects = new LinkedList<GameObject>();
    private GameObject lastSelected;



    public ObjectManager(){


    }

    public Queue<GameObject> getObjects(){

        return objects;
    }


    public void addObject(GameObject gameObject){
        if(objects.contains(gameObject))
            objects.remove(gameObject);
        objects.add(gameObject);
        lastSelected = gameObject;
    }

    public void removeObject(GameObject gameObject){
        if(objects.contains(gameObject))
        objects.remove( gameObject);
    }

    public void update() {
        for (GameObject object : objects) {
            object.update();
        }
    }

    public GameObject getLastSelected(){
        return lastSelected;
    }

    /**
     * Multiplies the size of the picture by the multiplier.
     * @param multiplier
     * @return gameobject
     */
    public void resizeLastGameObject(float multiplier){
        GameObject gameObject = getLastSelected();
        if(gameObject.getPicture().getWidth() < 5 || gameObject.getPicture().getHeight() <5)
            return;
        gameObject.rescaleObject((int)(gameObject.getPicture().getWidth()*multiplier),(int)(gameObject.getPicture().getHeight()*multiplier));
    }


    public void draw(Canvas canvas){
        for(GameObject object:objects){
            object.draw(canvas);
        }


    }

    public void clear(){
        objects.clear();
        lastSelected = null;
    }

    public void setLastObject(GameObject gameObject){
        lastSelected = gameObject;
    }


    /**
     * Use this method to enlarge bitmaps. Normal scaling to make a bitmap bigger does not work
     * @param bitmapToScale
     * @param newWidth
     * @param newHeight
     * @return
     */
    private static Bitmap scaleBitmap(Bitmap bitmapToScale, float newWidth, float newHeight) {
        if(bitmapToScale == null)
            return null;
//get the original width and height
        int width = bitmapToScale.getWidth();
        int height = bitmapToScale.getHeight();
// create a matrix for the manipulation
        Matrix matrix = new Matrix();

// resize the bit map
        matrix.postScale(newWidth / width, newHeight / height);

// recreate the new Bitmap and set it back
        return Bitmap.createBitmap(bitmapToScale, 0, 0, bitmapToScale.getWidth(), bitmapToScale.getHeight(), matrix, true);  }
}
