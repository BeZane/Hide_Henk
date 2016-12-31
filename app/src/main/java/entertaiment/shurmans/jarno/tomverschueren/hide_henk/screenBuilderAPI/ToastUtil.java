package entertaiment.shurmans.jarno.tomverschueren.hide_henk.screenBuilderAPI;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by TomVerschueren on 29/12/2016.
 */

public class ToastUtil {

    public static  Context context;


    public static Toast createToast(String text,int duration){
        return Toast.makeText(context, text, duration);
    }
}
