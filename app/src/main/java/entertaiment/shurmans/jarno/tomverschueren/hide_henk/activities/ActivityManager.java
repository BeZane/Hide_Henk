package entertaiment.shurmans.jarno.tomverschueren.hide_henk.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.R;

/**
 * Created by TomVerschueren on 7/01/2017.
 */

public class ActivityManager {


    //ID
    //1 = Game
    // 2 = NameActivity

    private static ActivityManager activityManager;
    private ArrayList<Activity> activities = new ArrayList<>();
    private Activity active;

    private ActivityManager(){

    }

    public static ActivityManager getInstance(){
        if(activityManager == null)
            activityManager = new ActivityManager();
        return activityManager;
    }

    public void addActivity(Activity activity){
        activities.add(activity);

    }

    public Activity getActivity(int ID){
        for(Activity activity:activities){
            if(((IDActivity)activity).getID() == ID)
                return activity;
        }
        return null;
    }

    public Activity getActive(){
        return active;
    }

    public void setActive(Activity activity){
        active = activity;
    }

    public boolean startActivity(int ID){
                Activity activity = active;
                Intent intent = null;
                if(ID == 0){
                    intent = new Intent(activity,Game.class);
                }else if(ID == 1){
                    intent = new Intent(activity,NameActivity.class);
                }
                if(intent !=null)
                    activity.startActivity(intent);
                 if(intent == null)
                return false;
                    return true;

    }

    public static class Game extends Activity implements IDActivity {


        private static boolean nameLayout = false;
        private static EditText editText = null;
        private Button button;

        public Game(){
            nameLayout = false;
        }



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            getInstance().setActive(this);
            //turn title off
            requestWindowFeature(Window.FEATURE_NO_TITLE);

            //set window to full screen
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(new GamePanel(this));


        }

        @Override
        public void onResume(){
            super.onResume();
            //switchContent();
        }

        public static void switching(){

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


        @Override
        public int getID() {
            return 0;
        }
    }
}
