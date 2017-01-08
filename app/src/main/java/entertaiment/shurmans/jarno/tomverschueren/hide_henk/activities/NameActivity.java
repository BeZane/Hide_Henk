package entertaiment.shurmans.jarno.tomverschueren.hide_henk.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.GameStateManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.Building3State;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI.ToastUtil;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class NameActivity extends Activity implements IDActivity {

    public static boolean done = false;
    private static EditText editText = null;
    private Button button;
    public static GameStateManager gameStateManager;

    public static String NAME = null;
    //THIS THREAD IS USED TO SWITCH FROM GAMEPANEL TO ACTIVITY


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        editText = (EditText)findViewById(R.id.name_level);

        button = (Button) findViewById(R.id.building3_button);
        System.out.println(button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                NAME = editText.getText().toString();
                System.out.println(editText.getText().toString());
                ToastUtil.createToast("Level saved!", Toast.LENGTH_SHORT).show();
                done = true;
                Building3State building3State = (Building3State)gameStateManager.getState();
                building3State.done();
                //ActivityManager.getInstance().startActivity(0);


            }
        });
    }


    @Override
    public int getID() {
        return 1;
    }
}
