package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.Calendar;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.activities.ActivityManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Barrel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Bricks;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HayBale;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HorizontalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Tire;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.VerticalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.DoneButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.building2state.BuildingScrollBar;

/**
 * Created by TomVerschueren on 29/12/2016.
 */

public class Building2State extends GameState {

    private DoneButton doneButton;
    private Bitmap background;
    private BuildingScrollBar scrollBar;
    private long startClickTime;
    private long MAX_CLICK_DURATION = 200;
    private LevelWrapper levelWrapper;






    public Building2State(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public void setLevelWrapper(LevelWrapper levelWrapper){
        this.levelWrapper = levelWrapper;
    }

    @Override
    protected void init() {
        Bitmap tempBackground = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        doneButton = new DoneButton();

        doneButton.setX((int)(1200*GamePanel.X_SCALE));
        doneButton.setY((int)(650*GamePanel.Y_SCALE));

        scrollBar = new BuildingScrollBar();
        scrollBar.setShownAmount(3);
        scrollBar.setHEIGHT(GamePanel.SCREEN_HEIGHT);
        scrollBar.setWIDTH((int)(GamePanel.SCREEN_WIDTH /1.5));
        scrollBar.addObject(new HorizontalPlank(0, 0));
        scrollBar.addObject(new VerticalPlank(0, 0));
        scrollBar.addObject(new Barrel(0,0));
     /*   scrollBar.addObject(new HayBale(0,0));
        scrollBar.addObject(new Tire(0,0));
        scrollBar.addObject(new Bricks(0,0)); */

        scrollBar.setType(ScrollBar.ScrollBarType.MIDDLE);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);

        scrollBar.draw(canvas);
        doneButton.draw(canvas);
    }

    @Override
    protected boolean onTouchEvent(MotionEvent event) {
        long clickDuration = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startClickTime = Calendar.getInstance().getTimeInMillis();
                return scrollBar.actionDown(event);
            case MotionEvent.ACTION_MOVE:
                clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (MAX_CLICK_DURATION < clickDuration) {

                    return scrollBar.actionMove(event);

                }
                break;
            case MotionEvent.ACTION_UP:
                float x = event.getX();///GamePanel.X_SCALE;
                float y = event.getY();///GamePanel.Y_SCALE;

                clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION) {
                    if(doneButton.contains(x,y)){
                        System.out.println("STARTING BUILDINGSTATE 3");
                        scrollBar.updateLevelWrapper(levelWrapper);
                        System.out.println("STARTING BUILDINGSTATE 3 UPDATED WRAPPER");

                        Building3State building3State = (Building3State) gsm.setState(GameStateManager.BUILDING3);
                        System.out.println("STARTING BUILDINGSTATE 3 GOT STATE");


                        building3State.setLevelWrapper(levelWrapper);
                        System.out.println("STARTING BUILDINGSTATE 3 ADDED WRAPPER");

                        ActivityManager.getInstance().startActivity(1);

                        return true;
                    }
                    return scrollBar.actionUp(event);
                }
                break;

        }
        return false;
    }

}
