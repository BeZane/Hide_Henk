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

    /**
     * NOT USED AT THE MOMENT AND NOT VALID!!
     * @return
     */

    /*public static String createTables(){
        return "http://studev.groept.be/api/a16_sd507/createtables";

    }*/

    public static String getID(String name){
        return "http://studev.groept.be/api/a16_sd507/getid/" + name;
    }

    public static String insertLevel(String name, String stringID){
        return "http://studev.groept.be/api/a16_sd507/insertonlinegame/"+name+ "/"+ stringID + "/" + Preferences.uuid.toString();
    }

    public static String getOnlineLevels(){
        return "http://studev.groept.be/api/a16_sd507/getlevels";
    }

    public static String getStats(){
        return "http://studev.groept.be/api/a16_sd507/getstats/"+ Preferences.uuid.toString();
    }



    public static String insertStat(String stat, int value){
        return "http://studev.groept.be/api/a16_sd507/insertstat/"  + stat + "/" + Preferences.uuid.toString() + "/" +value;
    }

    public static String insertPlayer(){
        System.out.println("INSERTING");
        return "http://studev.groept.be/api/a16_sd507/insertplayer/" + Preferences.uuid.toString();
    }

    public static String checkPlayer(){
        return "http://studev.groept.be/api/a16_sd507/checkplayer/" + Preferences.uuid.toString();
    }

    public static String getOnlineNames(){
        return "http://studev.groept.be/api/a16_sd507/getonlinenames";
    }

    public static String getOnlineLevel(String name){
        return "http://studev.groept.be/api/a16_sd507/getonlinelevel/"+name;
    }

    public static String incrementStat(String stat){
        return "http://studev.groept.be/api/a16_sd507/incrementstat/" + stat + "/" + stat + "/" + Preferences.uuid.toString();
    }


}
