package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.activities.ActivityManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.Building3State;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Henk;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.HorizontalPlank;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Tire;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.WaterDrop;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.onlinegames.OnlineGameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.onlinegames.OnlineGameScrollbar;

/**
 * Created by TomVerschueren on 4/12/2016.
 */


public class OnlineSelect extends GameState {

    private Bitmap background;
    private static OnlineGameScrollbar scrollBar;
    private Bitmap previous;
    private long startClickTime;
    private int MAX_CLICK_DURATION = 200;

    public OnlineSelect(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    protected void init() {
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
        previous = Bitmap.createScaledBitmap(previous, 140, 140, false);

        scrollBar = new OnlineGameScrollbar(gsm);
        scrollBar.setShownAmount(5);
        scrollBar.setHEIGHT(GamePanel.SCREEN_HEIGHT);
        scrollBar.setWIDTH((int)(GamePanel.SCREEN_WIDTH /1.5));
        DatabaseManager.request(UrlRequest.getOnlineNames());
    }

    @Override
    protected void update() {
        if(!OnlineLevel.lastLoadedID.equalsIgnoreCase("")){
            OnlineLevel gameState = (OnlineLevel) gsm.setState(GameStateManager.ONLINELEVEL);
            gameState.populate();

        }
    }

    public static void updateScrollBar(JSONArray jsonArray){
        scrollBar.clear();
        System.out.println(jsonArray);
        for(int i=0;i<jsonArray.length();i++) {
            OnlineGameObject onlineGameObject = new OnlineGameObject(0, 0);
            try {
                onlineGameObject.setText(jsonArray.getJSONObject(i).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            scrollBar.addObject(onlineGameObject);
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        scrollBar.draw(canvas);
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);

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
                    if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                        gsm.setState(gsm.BUILDERMENU);
                        return true;
                    }
                    return scrollBar.actionUp(event);
                }
                break;

        }
        return false;
    }

}