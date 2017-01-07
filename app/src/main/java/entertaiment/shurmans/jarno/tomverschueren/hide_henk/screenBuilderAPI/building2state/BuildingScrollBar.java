package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.building2state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.GameObject;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ScrollBar;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ZoomInButton;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ZoomOutButton;

/**
 * Created by TomVerschueren on 30/12/2016.
 */

public class BuildingScrollBar extends ScrollBar {



    private ZoomInButton zoomIn;
    private ZoomOutButton zoomOut;
    private HashMap<Integer, Integer> amount = new HashMap<>();


    public BuildingScrollBar() {
        super(null);
        zoomIn = new ZoomInButton();
        zoomOut = new ZoomOutButton();
    }

    @Override
    public boolean actionUp(MotionEvent event){
            int number = getSelectedNumber((int)(event.getY()));
            if(event.getX() > SCREEN_WIDTH/2){
                if(amount.get(number) > 0)
                    amount.put(number,amount.get(number)-1);
            }else if(event.getX() < SCREEN_WIDTH/2){
                    amount.put(number, amount.get(number)+1);
            }
        return true;
    }



    public void draw(Canvas canvas){
        int number=0;
        for(int i =0;i<scrollingObjects.size();i++){

            Point point = getPoint(number);

            scrollingObjects.get(number).setX((point.x)/GamePanel.X_SCALE);
            scrollingObjects.get(number).setY((point.y-offset)/GamePanel.Y_SCALE);
            scrollingObjects.get(number).update();
            scrollingObjects.get(number).setSolid(true);
            scrollingObjects.get(number).draw(canvas);
            if(scrollingObjects.get(number).getShape() == GameObject.Shapes.CIRCLE) {
                zoomIn.setX((int) ((point.x - 50*GamePanel.X_SCALE - scrollingObjects.get(number).getBitmap().getWidth() / 2)));
                zoomOut.setX((int) ((point.x + 50*GamePanel.Y_SCALE + scrollingObjects.get(number).getBitmap().getWidth() / 2)));
            }else{
                zoomIn.setX((int) ((point.x - 50*GamePanel.X_SCALE)));
                zoomOut.setX((int) ((point.x + 50*GamePanel.Y_SCALE + scrollingObjects.get(number).getBitmap().getWidth())));
            }
            zoomIn.setY((int)(point.y-offset));
            zoomOut.setY((int)(point.y-offset));
            zoomIn.draw(canvas);
            zoomOut.draw(canvas);
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            p.setTextSize(60* GamePanel.X_SCALE);

            if(!amount.containsKey(number)){
                amount.put(number,0);
            }
            String text = amount.get(number) + "";
            canvas.drawText(text, 100,point.y-offset, p);
            number++;
            count++;
        }
        count =0;
        number=0;

    }

    public void updateLevelWrapper(LevelWrapper levelWrapper){
        Iterator it = amount.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            for(int i = 0;i<(int)pair.getValue();i++) {
                levelWrapper.addObject(getSelectedObjectByNumber((int) pair.getKey()));
            }
        }
    }
}
