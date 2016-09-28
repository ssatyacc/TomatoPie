package hackerearth.satya.tomatopie.presenter.implement;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import hackerearth.satya.tomatopie.TomatoPie;
import hackerearth.satya.tomatopie.model.City;
import hackerearth.satya.tomatopie.presenter.LocationSelectionCallbackInterface;
import hackerearth.satya.tomatopie.presenter.LocationSelectionDataInterface;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public class LocationSelectionDataInterfaceImpl implements LocationSelectionDataInterface {

    public static final String CITY_BY_PLACE_URL = "https://developers.zomato.com/api/v2.1/cities";
    private static final String TAG = "LocationSelectionDataIn";
    LocationSelectionCallbackInterface callbackInterface;

    public LocationSelectionDataInterfaceImpl(LocationSelectionCallbackInterface callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    public void getCityByLocation(final String locationName) {
        String url = CITY_BY_PLACE_URL +
                "?q=" +
                locationName +
                "&count=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null && response.has("location_suggestions")) {
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
        });
        TomatoPie.getInstance().addToRequestQueue(request);
    }

    @Override
    public void getCityByLatLng(double lat, double lng) {
        String url = CITY_BY_PLACE_URL +
                "?alt=" +
                lat +
                "&lng=" +
                lng +
                "&count=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null && response.has("location_suggestions")) {
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
        });
        TomatoPie.getInstance().addToRequestQueue(request);
    }
}
