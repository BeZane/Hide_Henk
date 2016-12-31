package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class Game extends Activity {

    private static EditText editText;
    private static ImageView tire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        EditText editText = (EditText) findViewById(R.id.name_level);
        ImageView tire = (ImageView) findViewById(R.id.tire);


        setContentView(new GamePanel(this));
        setContentView(R.layout.activity_game);
    }

    public static  ImageView getEditText(){
        return tire;
    }







}
