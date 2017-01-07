package entertaiment.shurmans.jarno.tomverschueren.hide_henk.database;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.options.Preferences;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public class UrlRequest {



    String url ="";

    UrlRequest(String url){
        this.url = url;

    }


    public static String createTables(){
        return "http://api.a16_sd507.studev.groept.be/createtables";

    }

    public static String getID(String name){
        return "http://api.a16_sd507.studev.groept.be/getid/" + name;
    }

    public static String insertLevel(String name, String stringID){
        return "http://api.a16_sd507.studev.groept.be/insertonlinegame/"+name+ "/"+ stringID + "/" + Preferences.uuid.toString();
    }

    public static String getOnlineLevels(){
        return "http://api.a16_sd507.studev.groept.be/getlevels";
    }

    public static String getStats(){
        return "http://api.a16_sd507.studev.groept.be/getstats/"+ Preferences.uuid.toString();
    }



    public static String insertStat(String stat, int value){
        return "ttp://api.a16_sd507.studev.groept.be/insertstat/"  + stat + "/" + Preferences.uuid.toString() + "/" +value;
    }

    public static String insertPlayer(){
        System.out.println("INSERTING");
        return "http://api.a16_sd507.studev.groept.be/insertplayer/" + Preferences.uuid.toString();
    }

    public static String checkPlayer(){
        return "http://api.a16_sd507.studev.groept.be/checkplayer/" + Preferences.uuid.toString();
    }

    public static String getOnlineNames(){
        return "http://api.a16_sd507.studev.groept.be/getonlinenames";
    }

    public static String getOnlineLevel(String name){
        return "http://api.a16_sd507.studev.groept.be/getonlinelevel/"+name;
    }


}
