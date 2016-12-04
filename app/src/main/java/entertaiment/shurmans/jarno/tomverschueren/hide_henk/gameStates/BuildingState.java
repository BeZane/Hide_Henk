package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.MenuButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.RectanglePicture;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;

/**
 * Created by TomVerschueren on 4/12/2016.
 */

public class BuildingState extends GameState {

    private Bitmap background;
    private ScrollBar scrollBar;

    public BuildingState(GameStateManager gsm){
        this.gsm = gsm;
    }


    @Override
    protected void init() {
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.WIDTH, GamePanel.HEIGHT, false);
        Plank plank = new Plank(BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.plank));
        scrollBar = new ScrollBar();
        scrollBar.setShownAmount(2);
        scrollBar.setHEIGHT(GamePanel.HEIGHT);
        scrollBar.setWIDTH(GamePanel.WIDTH/5);
        scrollBar.addObject(plank);
        scrollBar.addObject(new RectanglePicture(BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.menu_button)));

        scrollBar.setType(ScrollBar.ScrollBarType.RIGHT_SIDE);

    }

    @Override
    protected void update() {

    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(background,0,0,null);
        scrollBar.draw(canvas);
    }

    @Override
    protected boolean onTouchEvent(MotionEvent event) {
        return scrollBar.onTouchEvent(event);
}
}
