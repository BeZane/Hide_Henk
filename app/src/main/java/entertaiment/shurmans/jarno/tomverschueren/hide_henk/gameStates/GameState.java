package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Canvas;

/**
 * Created by Admin on 1/12/2016.
 */

public abstract class GameState {

    protected GameStateManager gsm;

    protected abstract void init();
    protected abstract void update();
    protected abstract void draw(Canvas canvas);


}
