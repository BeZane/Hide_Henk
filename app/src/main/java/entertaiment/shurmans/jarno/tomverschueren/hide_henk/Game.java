package entertaiment.shurmans.jarno.tomverschueren.hide_henk;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Game extends Activity {


    private static boolean nameLayout = false;
    private static EditText editText = null;
    private Button button;

    public Game(){
        nameLayout = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);







    }

    @Override
    public void onResume(){
        super.onResume();
        switchContent();
    }

    public void switchContent(){
        if(!nameLayout) {
            setContentView(new GamePanel(this));
        }else {
            setContentView(R.layout.activity_game);
            editText = (EditText)findViewById(R.id.name_level);

            button = (Button) findViewById(R.id.building3_button);
            System.out.println(button);

            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    nameLayout = false;
                }
            });
        }
    }

    public static void setNameLayout(boolean on){
        nameLayout = on;
    }

    public static boolean getNameLayout(){
        return nameLayout;

    }

    @NonNull
    public static String getInputName(){
        System.out.println(editText.getText().toString());
        return editText.getText().toString();

    }









}
