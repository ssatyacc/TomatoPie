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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import hackerearth.satya.tomatopie.BuildConfig;
import hackerearth.satya.tomatopie.TomatoPie;
import hackerearth.satya.tomatopie.model.CityStats;
import hackerearth.satya.tomatopie.model.RestaurantInfo;
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
    private static final Map<String, String> headers = new ArrayMap<String, String>();

    static {
        headers.put("user-key", BuildConfig.ZOMATO_API_KEY);
    }

    private MainActivityCallbackInterface callbackInterface;

    public MainActivityDataInterfaceImpl(MainActivityCallbackInterface callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    public void getCityId(String name) {
        String url = LOCATION_URL +
                "?query=" +
                URLEncoder.encode(name);
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
                return headers;
            }
        };
        TomatoPie.getInstance().addToRequestQueue(request);
    }

    @Override
    public void getCityInfo(final String name, int entityId, String entityName) {
        String url = LOCATION_DETAILS_URL +
                "?entity_id=" +
                entityId +
                "&entity_type=" +
                entityName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        try {
                            CityStats stats = new CityStats();
                            List<RestaurantInfo> restaurantInfos = new ArrayList<>();

                            stats.cityTitle = name;
                            stats.nightLifeIndex = response.getString("nightlife_index");
                            stats.popularityIndex = response.getString("popularity");
                            JSONArray array = response.getJSONArray("top_cuisines");
                            List<String> cuisines = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                cuisines.add(array.getString(i));
                            }
                            stats.topCuisines = cuisines;

                            JSONArray restaurantsArray = response.getJSONArray("best_rated_restaurant");
                            for (int j = 0; j < restaurantsArray.length(); j++) {
                                JSONObject jsonObject = restaurantsArray.getJSONObject(j).getJSONObject("restaurant");
                                try {
                                    RestaurantInfo restaurantInfo = new RestaurantInfo();
                                    restaurantInfo.id = jsonObject.getString("id");
                                    restaurantInfo.name = jsonObject.getString("name");
                                    restaurantInfo.url = jsonObject.getString("url");
                                    restaurantInfo.address = jsonObject.getJSONObject("location").getString("address");
                                    restaurantInfo.locality = jsonObject.getJSONObject("location").getString("locality");
                                    restaurantInfo.location = new double[]{
                                            jsonObject.getJSONObject("location").getDouble("latitude"),
                                            jsonObject.getJSONObject("location").getDouble("longitude")
                                    };
                                    restaurantInfo.cuisines = jsonObject.getString("cuisines");
                                    restaurantInfo.costForTwo = jsonObject.getInt("average_cost_for_two");
                                    restaurantInfo.costCategory = jsonObject.getInt("price_range");
                                    restaurantInfo.currencyUnits = jsonObject.getString("currency");
                                    restaurantInfo.thumb = jsonObject.getString("thumb");
                                    restaurantInfo.photosUrl = jsonObject.getString("photos_url");
                                    restaurantInfo.menuUrl = jsonObject.getString("menu_url");
                                    restaurantInfo.featuredImage = jsonObject.getString("featured_image");
                                    restaurantInfo.hasOnlineDelivery = jsonObject.getInt("has_online_delivery");
                                    restaurantInfo.isDeliveringNow = jsonObject.getInt("is_delivering_now");
                                    restaurantInfo.hasTableBooking = jsonObject.getInt("has_table_booking");
                                    restaurantInfo.rating = Double.valueOf(jsonObject.getJSONObject("user_rating").getString("aggregate_rating"));
                                    restaurantInfo.ratingComment = jsonObject.getJSONObject("user_rating").getString("rating_text");
                                    restaurantInfo.ratingColor = jsonObject.getJSONObject("user_rating").getString("rating_color");
                                    restaurantInfo.votes = jsonObject.getJSONObject("user_rating").getInt("votes");
                                    restaurantInfos.add(restaurantInfo);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d(TAG, "onResponse: " + jsonObject.toString());
                                }
                            }
                            Collections.sort(restaurantInfos);
                            callbackInterface.onCityDetailsSuccess(stats, restaurantInfos);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                return headers;
            }
        };
        TomatoPie.getInstance().addToRequestQueue(request);
    }

    @Override
    public void getRestaurantDetails(String name) {

    }
}
