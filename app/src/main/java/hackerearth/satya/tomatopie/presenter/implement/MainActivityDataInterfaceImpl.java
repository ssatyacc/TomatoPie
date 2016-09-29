package hackerearth.satya.tomatopie.presenter.implement;

import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Map;

import hackerearth.satya.tomatopie.TomatoPie;
import hackerearth.satya.tomatopie.presenter.MainActivityCallbackInterface;
import hackerearth.satya.tomatopie.presenter.MainActivityDataInterface;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

class MainActivityDataInterfaceImpl implements MainActivityDataInterface {

    private static final String LOCATION_URL = "https://developers.zomato.com/api/v2.1/locations";
    private static final String LOCATION_DETAILS_URL =
            "https://developers.zomato.com/api/v2.1/location_details";
    private static final String API_KEY = "d4a9757d5f44244f8dd9d212ed972363";
    private static final Map<String, String> mHeaders = new ArrayMap<String, String>();

    static {
        mHeaders.put("user-key", API_KEY);
    }

    private MainActivityCallbackInterface callbackInterface;

    public MainActivityDataInterfaceImpl(MainActivityCallbackInterface callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    public void getCityId(String name) {
        String url = URLEncoder.encode(LOCATION_URL +
                "?query=" +
                name);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        if (response.has("location_suggestions")) {
                            try {
                                JSONObject location = response
                                        .getJSONArray("location_suggestions").getJSONObject(0);
                                callbackInterface.onCityIdSuccess(location.getString("title"),
                                        location.getString("entity_type"),
                                        location.getInt("entity_id"));
                                return;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        callbackInterface.onFailure();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callbackInterface.onFailure();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaders;
            }
        };
        TomatoPie.getInstance().addToRequestQueue(request);
    }

    @Override
    public void getCityInfo(String name, int entityId, String entityName) {
        String url = URLEncoder.encode(LOCATION_URL +
                "?entity_id=" +
                entityId +
                "&entity_type=" +
                entityName);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        if (response.has("location_suggestions")) {
                            try {
                                JSONObject location = response
                                        .getJSONArray("location_suggestions").getJSONObject(0);
                                callbackInterface.onCityIdSuccess(location.getString("title"),
                                        location.getString("entity_type"),
                                        location.getInt("entity_id"));
                                return;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        callbackInterface.onFailure();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callbackInterface.onFailure();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaders;
            }
        };
        TomatoPie.getInstance().addToRequestQueue(request);
    }

    @Override
    public void getRestrauntDetails(String name) {

    }
}
