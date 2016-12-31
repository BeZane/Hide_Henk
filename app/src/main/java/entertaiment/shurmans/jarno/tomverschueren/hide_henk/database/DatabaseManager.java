package entertaiment.shurmans.jarno.tomverschueren.hide_henk.database;



import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;
import entertaiment.shurmans.jarno.tomverschueren.hide_henk.gameStates.builder.LevelWrapper;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public class DatabaseManager {

    private static Context context;


    public DatabaseManager(Context context){
        this.context = context;

    }


    private static void request(final String url, String ID) {

// Instantiate the RequestQueue with the cache and network.

// Start the queue
        RequestSingleton.getInstance(context).getRequestQueue().start();



        // \Formulate the request and handle the response.
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if(url.contains("insert"))
                            return;
                        if(url.contains("getid")){

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










