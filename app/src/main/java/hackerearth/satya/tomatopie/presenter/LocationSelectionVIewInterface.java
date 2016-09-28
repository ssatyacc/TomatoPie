package hackerearth.satya.tomatopie.presenter;

import java.util.List;

import hackerearth.satya.tomatopie.model.City;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public interface LocationSelectionViewInterface {
    void onLocationExist(City city);

    void onLocationsExist(List<City> cities);

    void onLocationInvalid();

    void onGpsEnabled();

    void onGpsNotEnabled();
}