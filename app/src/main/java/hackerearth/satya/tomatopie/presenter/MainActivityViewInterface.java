package hackerearth.satya.tomatopie.presenter;

import java.util.List;

import hackerearth.satya.tomatopie.model.CityStats;
import hackerearth.satya.tomatopie.model.RestaurantInfo;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public interface MainActivityViewInterface {

    void showLoading();

    void showRetry();

    void showRestaurantsInfo(CityStats stats, List<RestaurantInfo> info);

    void showRestaurant(RestaurantInfo info);
}
