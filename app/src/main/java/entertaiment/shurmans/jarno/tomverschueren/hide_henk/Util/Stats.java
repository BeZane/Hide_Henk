package entertaiment.shurmans.jarno.tomverschueren.hide_henk.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.DatabaseManager;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.database.UrlRequest;

/**
 * Created by TomVerschueren on 6/01/2017.
 */

public class Stats {

    private static List<Stat> stats = new ArrayList<>();


    public static void load(){
        DatabaseManager.request(UrlRequest.getStats());
    }

    public static void convert(JSONArray jsonArray){

        for(StatType statType:StatType.values()) {
            try{
            stats.add(new Stat(statType,jsonArray.getJSONObject(0).getInt(statType.toDatabaseString())));
            }catch (JSONException e){
                System.out.println("Error loading stats for:" + statType.toDatabaseString());
                e.printStackTrace();
            }
        }
    }

    public static int getStat(StatType statType){
        for(Stat stat:stats){
            if(stat.getType() == statType){
                return stat.getValue();
            }
        }
        return 0;
    }
}
