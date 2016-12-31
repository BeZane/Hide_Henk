package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;

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
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Tire;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.DoneButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ToastUtil;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.building2state.BuildingScrollBar;

/**
 * Created by TomVerschueren on 29/12/2016.
 */

public class Building2State extends GameState {

    private DoneButton doneButton;
    private GameStateManager gsm;
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
        DoneButton doneButton = new DoneButton();
        doneButton.setX(500);
        doneButton.setY(50);

        scrollBar = new BuildingScrollBar();
        scrollBar.setShownAmount(3);
        scrollBar.setHEIGHT(GamePanel.SCREEN_HEIGHT);
        scrollBar.setWIDTH((int)(GamePanel.SCREEN_WIDTH /1.5));
        scrollBar.addObject(new Plank(0, 0));
        scrollBar.addObject(new Henk(0, 0));
        scrollBar.addObject(new WaterDrop(0, 0));
        scrollBar.addObject(new Tire(0,0));

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
        float x = event.getX() / GamePanel.X_SCALE;
        float y = event.getY() / GamePanel.Y_SCALE;
        long clickDuration = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startClickTime = Calendar.getInstance().getTimeInMillis();
                return scrollBar.actionDown(event);
            case MotionEvent.ACTION_MOVE:
                clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (MAX_CLICK_DURATION < clickDuration) {
                    if(doneButton.contains(x,y)){

                    }
                    return scrollBar.actionMove(event);

                }
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();

                clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION) {

                    return scrollBar.actionUp(event);
                }
                break;

        }
        return false;
    }

}
