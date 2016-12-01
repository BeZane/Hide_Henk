package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API;

/**
 * Created by TomVerschueren on 1/12/2016.
 */

public interface Moveable {


    /**
     * This method should be called when the player is trying to drag the object
     * @param x New X point
     * @param y New Y point
     */

    void onDrag(float x, float y);

}
