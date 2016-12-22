package entertaiment.shurmans.jarno.tomverschueren.hide_henk.options;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TomVerschueren on 18/12/2016.
 */

public class Preferences {

    private Context context;
    public static boolean SOUND = false;
    private static SharedPreferences settings;

    public Preferences(Context context){
        settings = context.getSharedPreferences("options",0);
        this.context = context;
        load();

    }


    private void load() {
        SharedPreferences.Editor editor = settings.edit();
        if(!settings.contains("sound"))
            editor.putBoolean("sound",false);
        SOUND = settings.getBoolean("sound",false);

        editor.commit();
    }

    public static void update(String option, boolean b){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(option,b);
        editor.commit();

    }




}
