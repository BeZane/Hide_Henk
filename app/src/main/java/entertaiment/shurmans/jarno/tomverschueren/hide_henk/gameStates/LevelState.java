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
    private boolean objectsLoaded = false;
    protected ScrollBar scrollBar;
    private ObjectManager objectManager = new ObjectManager();

    private int level = 0;

    private int collisions = 0;

    public LevelState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void init(){
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

        populate();
        objectsLoaded = true;
    }

    protected void populate(){
        //TODO: I WOULD USE THE LEVELWRAPPER CLASS. PREPAREOBJECTS ARE OBJECTS THAT ARE ALREADY IN THE GAME. NORMAL OBJECTS
        //TODO: ARE THE OBJECTS THAT NEED TO BE PLACED
        //TODO read in the file that has info about the level layout.
        //TODO set the int level to the level we are currently playing.
    }

    private void reset(){
        objects = new ArrayList<>();
        toPlace = new ArrayList<>();
        hosePos = -hose.getWidth();
        hoseSpawned = false;
        objectsLoaded = false;
        hoseHasSprayed = false;
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
                        objects.add(selectedObject);
                        toPlace.remove(selectedObject);
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