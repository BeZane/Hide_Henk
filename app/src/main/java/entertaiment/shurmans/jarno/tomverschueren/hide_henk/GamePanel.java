package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by TomVerschueren on 24/11/2016.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public GamePanel(Context context){

        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
    }


}
