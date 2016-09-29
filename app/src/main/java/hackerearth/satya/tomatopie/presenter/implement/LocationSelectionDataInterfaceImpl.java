package hackerearth.satya.tomatopie.presenter.implement;

import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import hackerearth.satya.tomatopie.TomatoPie;
import hackerearth.satya.tomatopie.model.City;
import hackerearth.satya.tomatopie.presenter.LocationSelectionCallbackInterface;
import hackerearth.satya.tomatopie.presenter.LocationSelectionDataInterface;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

class LocationSelectionDataInterfaceImpl implements LocationSelectionDataInterface {

    private static final String CITY_BY_PLACE_URL = "https://developers.zomato.com/api/v2.1/cities";
    private static final String API_KEY = "d4a9757d5f44244f8dd9d212ed972363";
    private static final String TAG = "LocationSelectionDataIn";
    private static final Map<String, String> mHeaders = new ArrayMap<String, String>();

    static {
        mHeaders.put("user-key", API_KEY);
    }

    private LocationSelectionCallbackInterface callbackInterface;

    LocationSelectionDataInterfaceImpl(LocationSelectionCallbackInterface callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    public void getCitiesByLocation(String locationName) {
        String url = URLEncoder.encode(CITY_BY_PLACE_URL +
                "?q=" +
                locationName +
                "&count=10");
        Log.d(TAG, "getCitiesByLocation: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        if (response.has("location_suggestions")) {
                            try {
                                JSONArray locations = response
                                        .getJSONArray("location_suggestions");
                                City city;

                                ArrayList<City> cityArrayList = new ArrayList<>();
                                for (int i = 0; i < locations.length(); i++) {
                                    JSONObject location = locations.getJSONObject(i);
                                    city = new City(location.getInt("id"),
                                            location.getString("name"), location.getInt("country_id"),
                                            location.getString("country_name"),
                                            location.getInt("is_state"), location.getInt("state_id"),
                                            location.getString("state_name"),
                                            location.getString("state_code"));
                                    cityArrayList.add(city);
                                }

                                callbackInterface.onCitiesObtained(cityArrayList);
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
    public void getCityByLocation(String locationName) {
        String url = URLEncoder.encode(CITY_BY_PLACE_URL +
                "?q=" +
                locationName +
                "&count=1");
        Log.d(TAG, "getCitiesByLocation: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        if (response.has("location_suggestions")) {
                            try {
                                JSONObject location = response
                                        .getJSONArray("location_suggestions").getJSONObject(0);
                                City city = new City(location.getInt("id"),
                                        location.getString("name"), location.getInt("country_id"),
                                        location.getString("country_name"),
                                        location.getInt("is_state"), location.getInt("state_id"),
                                        location.getString("state_name"),
                                        location.getString("state_code"));

                                callbackInterface.onCityObtained(city);
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
    public void getCityByLatLng(double lat, double lng) {
        String url = CITY_BY_PLACE_URL +
                "?lat=" +
                lat +
                "&lon=" +
                lng +
                "&count=1";
        Log.d(TAG, "getCitiesByLocation: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        if (response.has("location_suggestions")) {
                            try {
                                JSONObject location = response
                                        .getJSONArray("location_suggestions").getJSONObject(0);
                                City city = new City(location.getInt("id"),
                                        location.getString("name"), location.getInt("country_id"),
                                        location.getString("country_name"),
                                        location.getInt("is_state"), location.getInt("state_id"),
                                        location.getString("state_name"),
                                        location.getString("state_code"));

                                callbackInterface.onCityObtained(city);
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
}
