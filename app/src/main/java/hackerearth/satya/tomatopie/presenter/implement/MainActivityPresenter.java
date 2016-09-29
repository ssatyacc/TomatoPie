package hackerearth.satya.tomatopie.presenter.implement;

import java.util.List;

import hackerearth.satya.tomatopie.model.CityStats;
import hackerearth.satya.tomatopie.model.RestaurantInfo;
import hackerearth.satya.tomatopie.presenter.MainActivityCallbackInterface;
import hackerearth.satya.tomatopie.presenter.MainActivityInterface;
import hackerearth.satya.tomatopie.presenter.MainActivityViewInterface;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public class MainActivityPresenter implements MainActivityInterface, MainActivityCallbackInterface {

    private MainActivityViewInterface viewInterface;
    private MainActivityDataInterfaceImpl dataInterface;

    private CityStats stats;
    private List<RestaurantInfo> restaurantInfo;

    public MainActivityPresenter(MainActivityViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        dataInterface = new MainActivityDataInterfaceImpl(this);
    }

    @Override
    public void onRestrauntSelected() {
        // TODO: 9/29/16 Get data of restraunt
    }

    @Override
    public void onCreated(String cityName) {
        dataInterface.getCityId(cityName);
    }

    @Override
    public void onCityIdSuccess(String cityName, String entityName, int entityId) {
        dataInterface.getCityInfo(cityName, entityId, entityName);
    }

    @Override
    public void onFailure() {
        viewInterface.showRetry();
    }

    @Override
    public void onCityDetailsSuccess(CityStats stats, List<RestaurantInfo> restaurantInfo) {
        this.stats = stats;
        this.restaurantInfo = restaurantInfo;

        viewInterface.showRestaurantsInfo(stats, restaurantInfo);
    }

    @Override
    public void onRestrauntDetailsSuccess() {
    }
}
