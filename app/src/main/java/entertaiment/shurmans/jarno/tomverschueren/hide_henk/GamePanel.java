package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread  thread;
    private Background background;
    private Plank plank;
    private GameStateManager gsm;

    //dimensions of the screen
    public static int HEIGHT;
    public static int WIDTH;
    public static float SCALING_FACTOR_X = 0;
    public static float SCALING_FACTOR_Y = 0;

    public static Resources RESOURCES;

    public GamePanel(Context context){

        super(context);



        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(),this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);

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
