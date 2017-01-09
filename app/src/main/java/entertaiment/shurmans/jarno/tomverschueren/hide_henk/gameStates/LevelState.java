package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Calendar;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.ObjectManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HorizontalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.options.Preferences;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;

/**
 * This is the superclass of all the levels.
 */

public class LevelState extends GameState {

    public static String lastLoadedID = "";
    private ArrayList<String> offlineLevelIDs = new ArrayList<>();

    //fields
    protected ArrayList<GameObject> objects = new ArrayList<GameObject>();
    protected ArrayList<GameObject> toPlace = new ArrayList<GameObject>();
    private GameObject selectedObject;
    private Bitmap background;
    private Bitmap previous;
    protected Henk henk;
    //hose
    private Bitmap hose;
    private double hosePos;
    private boolean hoseSpawned;
    private boolean hoseHasSprayed;
    private double hoseSpeed = 3;
    protected boolean objectsLoaded = false;
    protected ScrollBar scrollBar;
    private ObjectManager objectManager = new ObjectManager();

    private int level = 0;

    private int collisions = 0;

    public LevelState(GameStateManager gsm){
        this.gsm = gsm;
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
        System.out.println(offlineLevelIDs.size() + "I: + " + level);
        System.out.println("LEVEL: " + offlineLevelIDs.get(level));
        lastLoadedID = offlineLevelIDs.get(level);
    }


    public void init(){
        if(offlineLevelIDs.isEmpty()) {
            //offlineLevelIDs.add("derde!HORIZONTAL_PLANK,229.0301,34.0,499.95501708984375,595.87646484375,true;VERTICAL_PLANK,23.54515,160.0,601.4005126953125,621.89208984375,true;HENK,99.88852,100.0,619.6070556640625,532.4853515625,true;VERTICAL_PLANK,23.54515,160.0,724.5401611328125,444.4189453125,true;VERTICAL_PLANK,23.54515,160.0,496.112060546875,445.4296875,true;!VERTICAL_PLANK,159.82164,24.0,897.0,540.0,false;VERTICAL_PLANK,159.82164,24.0,897.0,540.0,false;HORIZONTAL_PLANK,159.82164,24.0,897.0,540.0,false;");
            offlineLevelIDs.add("eerstelevel!HORIZONTAL_PLANK,159.82164,24.0,715.9699096679688,388.36669921875,true;HORIZONTAL_PLANK,159.82164,24.0,455.39300537109375,392.36572265625,true;HENK,99.88852,100.0,662.4163818359375,516.46728515625,true;!BRICKS,119.86623,120.0,0.0,0.0,false;");
            offlineLevelIDs.add("tweedelevel!HORIZONTAL_PLANK,159.82164,24.0,659.2391357421875,464.43603515625,true;HENK,99.88852,100.0,772.7216186523438,396.36474609375,false;VERTICAL_PLANK,23.54515,160.0,986.141357421875,478.4326171875,true;HORIZONTAL_PLANK,159.82164,24.0,839.69921875,647.9296875,false;HORIZONTAL_PLANK,159.82164,24.0,487.54180908203125,465.435791015625,true;VERTICAL_PLANK,23.54515,160.0,820.9030151367188,486.45263671875,true;VERTICAL_PLANK,23.54515,160.0,989.3812866210938,310.95703125,true;!TIRE,200.0,200.0,0.0,0.0,false;TIRE,200.0,200.0,0.0,0.0,false");
            offlineLevelIDs.add("derde2!HORIZONTAL_PLANK,274.69342,40.666668,493.9381408691406,609.89501953125,true;VERTICAL_PLANK,23.54515,160.0,743.812744140625,441.38671875,true;VERTICAL_PLANK,23.54515,160.0,496.112060546875,444.4189453125,true;HENK,99.88852,100.0,629.2433471679688,556.50146484375,true;!VERTICAL_PLANK,23.54515,160.0,897.0,540.0,false;VERTICAL_PLANK,23.54515,160.0,897.0,540.0,false;HORIZONTAL_PLANK,159.82164,24.0,897.0,540.0,false;");

        }
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
        previous = Bitmap.createScaledBitmap(previous, 140, 140, false);
        //making hose bitmap
        Bitmap unScaledHose = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.hose);
        unScaledHose = Bitmap.createScaledBitmap(unScaledHose, 100, 110, false);
        int destWidth = (int)(unScaledHose.getWidth() * GamePanel.X_SCALE);
        int destHeight = (int) (unScaledHose.getHeight() * GamePanel.Y_SCALE);
        hose = Bitmap.createScaledBitmap(unScaledHose, destWidth, destHeight, false);
        scrollBar = new ScrollBar(objectManager);
        scrollBar.setShownAmount(4);
        scrollBar.setHEIGHT(GamePanel.SCREEN_HEIGHT);
        scrollBar.setWIDTH(GamePanel.SCREEN_WIDTH / 8);
        //ADD HERE ALL THE OBJECTS BY DOING SCROLLBAR.ADDOBJECT
        scrollBar.setType(ScrollBar.ScrollBarType.RIGHT_SIDE);

        //populate();
        objectsLoaded = true;
    }



    private void reset(){
        objects = new ArrayList<>();
        toPlace = new ArrayList<>();
        hosePos = -hose.getWidth();
        hoseSpawned = false;
        objectsLoaded = false;
        hoseHasSprayed = false;
        objectsLoaded = false;
        henk.makeDirty();
    }

    private void checkCollisions(GameObject object){

        for(GameObject o: objects){
            if(!object.equals(o)){
                if(object.checkCollision(o)){
                    collisions++;
                    System.out.println("Collision between " + object.getType() + " hitting " + o.getType() + " and this was nr " + collisions + " in the chain reaction");
                    o.update();
                    if(collisions > 100){
                        object.setDx(0);
                        object.setDy(0);
                        return;
                    }
                    checkCollisions(o);
                }
            }
        }
    }

    public void update(){
        //only start update() function if all objects are loaded and we make sure there is a Henk present
        if(!objectsLoaded) {
            return;
        }
        if(henk == null){
            for(GameObject gameObject:objects){
                if(gameObject.getType() == GameObject.Types.HENK)
                    henk =(Henk) gameObject;
            }
            if(henk == null)
                return;
        }

        //actual update() function
        boolean hoseMustSpawn = true;

        for(int i = 0; i < objects.size(); i++){
            GameObject o = objects.get(i);
            o.update();
            checkCollisions(o);
            collisions = 0;
            if(o.getType() == GameObject.Types.WATERDROP){
                WaterDrop drop = (WaterDrop)o;
                if(drop.getTimeLived() > drop.getTimeToLive()){
                    objects.remove(i);
                    i--;
                }
            }
            if(o.getDx() > 0.1 || o.getDy() > 0.1){
                hoseMustSpawn = false;
            }
            if(o.getY() > 1500){
                objects.remove(i);
                i--;
            }
        }

        if(objectsLoaded && toPlace.size() == 0 && hoseMustSpawn){
            hoseSpawned = true;
            for(GameObject object:objects){
                object.setSolid(true);
                object.setDx(0);
                object.setDy(0);
            }
        }

       if(!objects.contains(henk) && objectsLoaded){
            reset();
            gsm.setState(GameStateManager.GAMEOVER);
        }


        if(hoseSpawned){
            if(hosePos % 10 == 0){
                WaterDrop w = new WaterDrop(hosePos * hoseSpeed + hose.getWidth() / GamePanel.X_SCALE * 4 / 5, hose.getHeight() / GamePanel.Y_SCALE);
                w.setDx(hoseSpeed/3);
                w.setDy(5);
                objects.add(w);
            }
            hosePos++;
            if((hosePos - 10) * hoseSpeed > GamePanel.GAME_WIDTH){
                hoseSpawned = false;
                hoseHasSprayed = true;
            }
        }
        if(hoseHasSprayed){
            boolean waterDropsPresent = false;
            for(GameObject o : objects){
                if(o.getType() == GameObject.Types.WATERDROP){
                    waterDropsPresent = true;
                }
            }
            if(!waterDropsPresent && !henk.isCleaned()){
                reset();
                Preferences.levelsUnlocked[level + 1] = true;
                System.out.println("LEVEL_COMPLETED");
                DatabaseManager.request(UrlRequest.incrementStat("gamesplayed"));
                DatabaseManager.request(UrlRequest.incrementStat("gamesfailed"));

                gsm.setState(GameStateManager.LEVELCOMPLETED);
            }
            else if(henk.isCleaned()){
                System.out.println("GAME_OVER");
                DatabaseManager.request(UrlRequest.incrementStat("gamesplayed"));
                DatabaseManager.request(UrlRequest.incrementStat("gameswon"));


                reset();

                gsm.setState(GameStateManager.GAMEOVER);
            }
        }
        if(selectedObject != null) {
            selectedObject.drawUpdate();
        }
    }

    public void draw(Canvas canvas){
        if(!objectsLoaded) {
            return;
        }
        canvas.drawBitmap(background,0,0,null);
        for(GameObject o: objects){
            o.draw(canvas);
        }
        if(!hoseSpawned && ! hoseHasSprayed){ //ONLY DRAW THE SCROLLBAR IF HOSE ISN4T SPAWNED
            scrollBar.draw(canvas);
        }
        if(hoseSpawned){
            canvas.drawBitmap(hose,(int) (hosePos * hoseSpeed * GamePanel.X_SCALE), 0, null);
        }
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);
        if(selectedObject !=null) {
            selectedObject.draw(canvas);
        }
        for(GameObject o: toPlace){
            o.draw(canvas);
        }

        if(toPlace.size() > 0){
            Paint p = new Paint();
            p.setARGB(70,70,70,125);
            canvas.drawRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT/3, p);
        }






    }

    private long startClickTime = 0;
    private int MAX_CLICK_DURATION = 200;
    private long clickDuration =0;

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                clickDuration = 0;
                float x = event.getX() / GamePanel.X_SCALE;
                float y = event.getY() / GamePanel.Y_SCALE;

                if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    reset();
                    gsm.setState(gsm.LEVELSELECT);
                    return false;
                }
                else if(0 < toPlace.size()) {
                    System.out.println("IN HERE");
                    selectedObject = toPlace.get(0);
                    scrollBar.removeObject(selectedObject);
                    selectedObject.setX(x);
                    if(y > GamePanel.SCREEN_HEIGHT / 3 ){
                        y = GamePanel.SCREEN_HEIGHT / 3;
                    }
                    selectedObject.setY(y);
                }
                //return scrollBar.actionDown(event);
                return true;

            case MotionEvent.ACTION_MOVE:

                float xPos = event.getX() / GamePanel.X_SCALE;
                float yPos = event.getY() / GamePanel.Y_SCALE;

                if(selectedObject != null) {
                    selectedObject.setX(xPos);
                    if(yPos > GamePanel.SCREEN_HEIGHT / 3 ){
                        yPos = GamePanel.SCREEN_HEIGHT / 3;
                    }
                        selectedObject.setY(yPos);
                }
                //return scrollBar.actionMove(event);
                return true;


            case MotionEvent.ACTION_UP:
                    if (selectedObject != null) {
                        System.out.println("HERE");
                        toPlace.remove(selectedObject);
                        selectedObject.init();
                        objects.add(selectedObject);
                        selectedObject = null;
                        DatabaseManager.request(UrlRequest.incrementStat("ojbectsplaced"));
                        return true;
                    }

                //return scrollBar.actionUp(event);
                break;
        }
        return true;
    }


}