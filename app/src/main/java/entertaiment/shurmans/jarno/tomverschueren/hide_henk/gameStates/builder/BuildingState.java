package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.DoneButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.SolidButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ToastUtil;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ZoomInButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ZoomOutButton;

/**
 * Created by TomVerschueren on 4/12/2016.
 */



public class BuildingState extends GameState {

    private Bitmap background;
    private ScrollBar scrollBar;
    private ObjectManager objectManager;
    private ZoomInButton zoomInButton;
    private ZoomOutButton zoomOutButton;
    private SolidButton solidButton;
    private DoneButton doneButton;
    private long startClickTime;
    private long MAX_CLICK_DURATION = 200;
    private boolean moving = false;


    public BuildingState(GameStateManager gsm) {
        this.gsm = gsm;
        objectManager = new ObjectManager();
    }


    @Override
    protected void init() {
        Bitmap tempBackground = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        scrollBar = new ScrollBar(objectManager);
        scrollBar.setShownAmount(2);
        scrollBar.setHEIGHT(GamePanel.SCREEN_HEIGHT);
        scrollBar.setWIDTH(GamePanel.SCREEN_WIDTH / 8);
        scrollBar.addObject(new Plank(0, 0));
        scrollBar.addObject(new Henk(0, 0));
        scrollBar.addObject(new WaterDrop(0, 0));
        scrollBar.setType(ScrollBar.ScrollBarType.RIGHT_SIDE);
        zoomInButton = new ZoomInButton();
        zoomInButton.setX((int)(50));
        zoomInButton.setY((int)(50));
        zoomOutButton = new ZoomOutButton();
        zoomOutButton.setX((int)(150));
        zoomOutButton.setY((int)(50));
        solidButton = new SolidButton();
        solidButton.setX((int)(250));
        solidButton.setY((int)(50));

        doneButton = new DoneButton();
        doneButton.setX((int)(700));
        doneButton.setY((int)(50));

    }

    @Override
    protected void update() {
        objectManager.update();

    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        scrollBar.draw(canvas);
        objectManager.draw(canvas);
        zoomInButton.draw(canvas);
        solidButton.draw(canvas);
        zoomOutButton.draw(canvas);
        for(GameObject gameObject:objectManager.getObjects()){
            if(gameObject.getType() == GameObject.Types.HENK) {
                doneButton.draw(canvas);
                break;
            }


        }



    }

    @Override
    protected boolean onTouchEvent(MotionEvent event) {
        float x = event.getX()/GamePanel.X_SCALE;
        float y = event.getY()/GamePanel.Y_SCALE;
        long clickDuration=0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startClickTime = Calendar.getInstance().getTimeInMillis();
                return scrollBar.actionDown(event);
            case MotionEvent.ACTION_MOVE:
                clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(MAX_CLICK_DURATION <clickDuration) {
                    if(!moving){
                        Queue<GameObject> gameObjects = new LinkedList<>(objectManager.getObjects());
                        for(int i=0;i<objectManager.getObjects().size();i++) {
                            GameObject gameObject = gameObjects.poll();
                            if (gameObject.contains(x, y)) {
                                gameObject.setX(x);
                                gameObject.setY(y);
                                objectManager.setLastObject(gameObject);
                                moving = true;
                                return true;

                            }
                        }
                    }else{

                         GameObject gameObject = objectManager.getLastSelected();
                        if(gameObject.contains(x,y)) {
                            gameObject.setX(x);
                            gameObject.setY(y);
                            return true;
                        }

                    }
                    return scrollBar.actionMove(event);

                }
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();
                moving = false;
                clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION) {

                    if (zoomInButton.contains(x, y)) {
                        objectManager.resizeLastGameObject(1.2f);
                        return true;
                    } else if (zoomOutButton.contains(x, y)) {
                        objectManager.resizeLastGameObject(0.8f);

                        return true;
                    }else if(solidButton.contains(x,y)){
                        if(objectManager.getLastSelected() == null) {
                            ToastUtil.createToast("No element selected!",Toast.LENGTH_SHORT).show();
                        }else if(objectManager.getLastSelected().isPrepareSolid()){
                            ToastUtil.createToast("Element is now applied to gravity!", Toast.LENGTH_SHORT).show();
                            objectManager.getLastSelected().setPrepareSolid(false);
                        }else if(!objectManager.getLastSelected().isPrepareSolid()) {
                            ToastUtil.createToast("Element is now solid!", Toast.LENGTH_SHORT).show();
                            objectManager.getLastSelected().setPrepareSolid(true);
                        }
                    }else if(doneButton.contains(x,y)){
                        LevelWrapper levelWrapper = new LevelWrapper();
                        levelWrapper.setPresetObjects(new ArrayList<GameObject>(objectManager.getObjects()));
                        GameState gameState = gsm.setState(GameStateManager.BUILDING2);
                        Building2State building2State = (Building2State)gameState;
                        building2State.setLevelWrapper(levelWrapper);
                    }
                    return scrollBar.actionUp(event);
                }
                break;

        }
        return false;
        //return true;
    }
}
