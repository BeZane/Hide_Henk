package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public GamePanel(Context context){

        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }
}
