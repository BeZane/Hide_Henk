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

import entertaiment.shurmans.jarno.tomverschueren.hide_henk.GamePanel;

/**
 * Created by TomVerschueren on 8/12/2016.
 */

public class DatabaseManager {

    private Context context;


    public DatabaseManager(Context context){
        this.context = context;

    }


    public void request(UrlRequest URL) {

// Instantiate the RequestQueue with the cache and network.

// Start the queue
        RequestSingleton.getInstance(context).getRequestQueue().start();

        String url = URL.getUrl();

        // \Formulate the request and handle the response.
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("\n---------------------------////////////////////////\n");
                        System.out.println("response: " + response.toString());
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










