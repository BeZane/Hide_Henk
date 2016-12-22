package entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameState;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.MenuButton;


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
        background = Bitmap.createScaledBitmap(tempBackground, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, false);
        buildButton = new MenuButton();
        buildButton.setText("Build");
        buildButton.setX(GamePanel.SCREEN_WIDTH / 2);
        buildButton.setY((int)(160 * GamePanel.Y_SCALE + 0.5 * (buildButton.getPicture().getHeight() + 35 * GamePanel.Y_SCALE)));
        playButton =  new MenuButton();
        playButton.setText("Play");
        playButton.setX(GamePanel.SCREEN_WIDTH / 2);
        playButton.setY((int)(160 * GamePanel.Y_SCALE + 1.5 * (buildButton.getPicture().getHeight() + 35 * GamePanel.Y_SCALE)));

        previous = BitmapFactory.decodeResource(GamePanel.RESOURCES, R.drawable.previous);

    }


    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background,0,0,null);
        playButton.draw(canvas);
         buildButton.draw(canvas);

        //drawing the return button
        canvas.drawBitmap(previous, GamePanel.SCREEN_WIDTH - previous.getWidth() - 10, 10, null);

    }

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if(buildButton.contains(x,y)){
                    gsm.setState(gsm.BUILDING);
                }else if(playButton.contains((float)x,(float)y)){
                    gsm.setState(gsm.ONLINESELECT);
                }
                else if(x > GamePanel.SCREEN_WIDTH - previous.getWidth() - 10 && y < 10 + previous.getHeight()){
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
