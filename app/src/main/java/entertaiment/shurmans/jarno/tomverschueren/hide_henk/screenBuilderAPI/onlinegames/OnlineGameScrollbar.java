package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.onlinegames;

import android.view.MotionEvent;
import android.widget.Toast;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ToastUtil;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class OnlineGameScrollbar extends ScrollBar {

    private GameStateManager gameStateManager;


    public OnlineGameScrollbar(GameStateManager gameStateManager) {
        super(null);
        this.gameStateManager = gameStateManager;
        setType(ScrollBarType.MIDDLE);

    }


    @Override
    public boolean actionUp(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        if(isIn((int)x,(int)y)) {
            //click event has occurred
            this.y = (int)y;
            ToastUtil.createToast("Loading level from database", Toast.LENGTH_SHORT);
            DatabaseManager.request(UrlRequest.getOnlineLevel(((OnlineGameObject)this.getSelectedObject((int)event.getY())).getText()));

            resetOffset();
            System.out.println("COMING HERE");

            return true;
        }


        return false;
    }

}
