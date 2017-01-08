package entertaiment.shurmans.jarno.tomverschueren.hide_henk.database;



import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.Util.Stats;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.Level;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.OnlineSelect;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public class DatabaseManager {

    private static Context context;


    public DatabaseManager(Context context){
        this.context = context;
        request(UrlRequest.checkPlayer());

    }


    public static void request(final String url) {

// Instantiate the RequestQueue with the cache and network.

// Start the queue
        RequestSingleton.getInstance(context).getRequestQueue().start();



        // \Formulate the request and handle the response.
        final JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if(url.contains("insert"))
                            System.out.println("INSERT: " + response);
                        if(url.contains("checkplayer")){
                            if(response.isNull(0))
                                request(UrlRequest.insertPlayer());
                            System.out.println("INSERTING PLAYER");
                        }
                        if(url.contains("getid")){

                        }
                        if(url.contains("getstats")){
                            Stats.convert(response);
                            System.out.println(response);
                        }else if(url.contains("getonlinenames")){
                            OnlineSelect.updateScrollBar(response);
                            System.out.println("Just responded");
                        }else if(url.contains("getonlinelevel")){
                            try {
                                System.out.println(response);
                                System.out.println(response.getJSONObject(0).getString("id"));
                                Level.lastLoadedID = response.getJSONObject(0).getString("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                    }
                });


// Add the request to the RequestQueue.

        RequestSingleton.getInstance(context).addToRequestQueue(jsObjRequest);

    }



}










