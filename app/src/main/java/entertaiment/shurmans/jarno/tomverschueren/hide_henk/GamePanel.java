package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.Plank;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread  thread;
    private Background background;
    private Plank plank;
    private float BACKGROUND_WIDTH;
    private float BACKGROUND_HEIGHT;
    public static float SCALING_FACTOR_X = 0;
    public static float SCALING_FACTOR_Y = 0;

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
        //we can safely start the gameloop.
        background = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.background_ingame1));
        BACKGROUND_WIDTH = background.getWidth();
        BACKGROUND_HEIGHT = background.getHeight();
        plank = new Plank(BitmapFactory.decodeResource(getResources(),R.drawable.plank2_resized));
        thread.setRunning(true);
        thread.start();

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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                plank.onDrag(event.getX(),event.getY());
                break;
            default:
        }
        return super.onTouchEvent(event);
    }


    public void update(){
        background.update();
    }

    @Override
    public void draw(Canvas canvas){
        if(canvas!= null) {
            final int savedState = canvas.save();
            SCALING_FACTOR_X = getWidth()/BACKGROUND_WIDTH;
            SCALING_FACTOR_Y = getHeight()/BACKGROUND_HEIGHT;
            canvas.scale(getWidth()/BACKGROUND_WIDTH,getHeight()/BACKGROUND_HEIGHT);
            background.draw(canvas);
            plank.draw(canvas);


            canvas.restoreToCount(savedState);
        }
    }

}
