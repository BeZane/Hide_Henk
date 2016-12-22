package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;

/**
 * Created by TomVerschueren on 4/12/2016.
 */



public class BuildingState extends GameState {

    private Bitmap background;
    private ScrollBar scrollBar;
    private BuildingObjectManager objectManager;

    public BuildingState(GameStateManager gsm){
        this.gsm = gsm;
        objectManager = new BuildingObjectManager();
    }


    @Override
    protected void init() {
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        scrollBar = new ScrollBar(objectManager);
        scrollBar.setShownAmount(2);
        scrollBar.setHEIGHT(GamePanel.SCREEN_HEIGHT);
        scrollBar.setWIDTH(GamePanel.SCREEN_WIDTH/5);
        scrollBar.addObject(new Plank(0,0));
        scrollBar.addObject(new Henk(0,0));
        scrollBar.addObject(new WaterDrop(0,0));
        scrollBar.setType(ScrollBar.ScrollBarType.RIGHT_SIDE);


    }

    @Override
    protected void update() {
        objectManager.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(background,0,0,null);
        scrollBar.draw(canvas);
        objectManager.draw(canvas);


    }

    @Override
    protected boolean onTouchEvent(MotionEvent event) {
        return scrollBar.onTouchEvent(event);
}
}
