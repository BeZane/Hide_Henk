package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread  thread;
    private Background background;


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
        return super.onTouchEvent(event);
    }


    public void update(){
        background.update();
    }

    @Override
    public void draw(Canvas canvas){

        if(canvas!= null) {
            final int savedState = canvas.save();
            canvas.scale(getWidth()/background.getWidth(), getHeight()/background.getHeight());
            background.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

}
