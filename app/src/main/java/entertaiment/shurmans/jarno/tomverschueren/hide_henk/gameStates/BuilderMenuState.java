package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameobjects.API.MenuButton;


public class BuilderMenuState extends GameState {

    private Bitmap background;
    private MenuButton buildButton;
    private MenuButton playButton;
    private Bitmap previous;

    public BuilderMenuState(GameStateManager gsm){
        this.gsm = gsm;
    }


    public void init(){
        Bitmap tempBackground =  BitmapFactory.decodeResource(GamePanel.RESOURCES,R.drawable.background_ingame1);
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.WIDTH, GamePanel.HEIGHT, false);
        buildButton = new MenuButton(this.scaleBitMap(BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.plank_button)),"BUILD");
        playButton =  new MenuButton(this.scaleBitMap(BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.plank_button)),"PLAY");
        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);
    }


    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background,0,0,null);
        buildButton.setX(GamePanel.WIDTH/2-buildButton.getHeight()/2);
        buildButton.setY(GamePanel.HEIGHT/4*3-buildButton.getWidth()/2);
        playButton.setX(GamePanel.WIDTH/2 - buildButton.getHeight()/2);
        playButton.setY(GamePanel.HEIGHT/4*1-buildButton.getWidth()/2);
        buildButton.draw(canvas);
        playButton.draw(canvas);

        //drawing the return button
        canvas.drawBitmap(previous, GamePanel.WIDTH - previous.getWidth() - 10, 10, null);

    }

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if(buildButton.touches(new Point((int)x,(int)y))){
                    gsm.setState(gsm.BUILDING);
                }else if(playButton.touches(new Point((int)x,(int)y))){
                    gsm.setState(gsm.ONLINESELECT);
                }
                else if(x > GamePanel.WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
                    gsm.setState(gsm.MENUSTATE);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //plank.onDrag(event.getX(),event.getY());
                break;
            default:
        }
        return true;
    }
}
