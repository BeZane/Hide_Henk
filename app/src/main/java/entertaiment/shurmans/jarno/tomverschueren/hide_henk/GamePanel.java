package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread  thread;
    private Plank plank;
    private GameStateManager gsm;

    //dimensions of the screen
    public static int HEIGHT;
    public static int WIDTH;
    public static float SCALING_FACTOR_X = 0;
    public static float SCALING_FACTOR_Y = 0;

    //sounds
    private SoundPool sounds;
    private int themeSong;

    public static Resources RESOURCES;

    public GamePanel(Context context){

        super(context);



        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);

        //sounds
        sounds = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        themeSong = sounds.load(context,R.raw.main_theme,1);
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
        HEIGHT = getHeight();
        WIDTH = getWidth();

        gsm = new GameStateManager();

        sounds.play(themeSong,1,1,0,-1,1);

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
        plank.draw(canvas);
    }

}
