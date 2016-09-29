package hackerearth.satya.tomatopie.presenter;

import java.util.List;

import hackerearth.satya.tomatopie.model.CityStats;
import hackerearth.satya.tomatopie.model.RestaurantInfo;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public interface MainActivityCallbackInterface {

    void onCityIdSuccess(String name, String entityType, int entityId);

    void onFailure();

    void onCityDetailsSuccess(CityStats stats, List<RestaurantInfo> info);

    void onRestrauntDetailsSuccess();
}
