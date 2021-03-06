package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.BuildingState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.options.Preferences;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ToastUtil;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread  thread;
    private GameStateManager gsm;

    //dimensions of the screen
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static final int GAME_HEIGHT = 720;
    public static final int GAME_WIDTH = 1280;
    public static float X_SCALE;
    public static float Y_SCALE;

    //sounds
    private SoundPool sounds;
    private int themeSong;

    public static Resources RESOURCES;

    public GamePanel(Context context){

        super(context);
        new Preferences(context);

        DatabaseManager databaseManager =new DatabaseManager(getContext());
        //INSERT THE PLAYER IF IT DOES NOT EXIST IN THE DATABASE;
        databaseManager.request(UrlRequest.checkPlayer());
        //LOAD ALL THE STATS
        databaseManager.request(UrlRequest.getStats());


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        ToastUtil.context = context;
        thread = new MainThread(getHolder(),this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);

       //sounds

         sounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
         themeSong = sounds.load(context, R.raw.main_theme, 1);

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //we can safely start the gameloop;
        thread.setRunning(true);
        thread.start();


        init();
    }

    private void init(){
        RESOURCES = getResources();
        SCREEN_HEIGHT = getHeight();
        SCREEN_WIDTH = getWidth();
        X_SCALE = 1.0f * SCREEN_WIDTH / GAME_WIDTH;
        Y_SCALE = 1.0f * SCREEN_HEIGHT / GAME_HEIGHT;

        gsm = new GameStateManager();

        if(Preferences.SOUND) {
            sounds.play(themeSong, 1, 1, 0, -1, 1);
        }

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return gsm.onTouchEvent(event);
    }


    public void update(){
        gsm.update();
    }
    @Override
    public void draw(Canvas canvas){
        gsm.draw(canvas);

    }

}
