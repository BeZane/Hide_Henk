package entertaiment.shurmans.jarno.tomverschueren.hide_henk.options;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/**
 * Created by TomVerschueren on 18/12/2016.
 */

public class Preferences {

    private Context context;
    public static boolean SOUND = false;
    private static SharedPreferences settings;
    public static UUID uuid;

    public Preferences(Context context){
        settings = context.getSharedPreferences("options",0);
        this.context = context;
        load();

    }


    private void load() {
        SharedPreferences.Editor editor = settings.edit();
        if(!settings.contains("sound"))
            editor.putBoolean("sound",false);
        if(!settings.contains("uuid")){
            editor.putString("uuid",UUID.randomUUID().toString());
        }


        editor.commit();
        SOUND = settings.getBoolean("sound",false);
        this.uuid = UUID.fromString(settings.getString("uuid", "0"));
    }

    public static void update(String option, boolean b){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(option,b);
        editor.commit();

    }




}
