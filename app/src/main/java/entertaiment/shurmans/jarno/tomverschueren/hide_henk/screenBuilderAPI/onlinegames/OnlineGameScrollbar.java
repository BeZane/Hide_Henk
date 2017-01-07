package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.onlinegames;

import android.view.MotionEvent;
import android.widget.Toast;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.OnlineLevel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.ObjectManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
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
            DatabaseManager.request(UrlRequest.getOnlineLevel(((OnlineGameObject)this.getSelectedObject((int)event.getY())).getText()));
            ToastUtil.createToast("Loading level from database", Toast.LENGTH_SHORT);


            System.out.println("COMING HERE");

            return true;
        }


        return false;
    }

}
