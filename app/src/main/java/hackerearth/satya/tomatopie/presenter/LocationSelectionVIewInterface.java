package hackerearth.satya.tomatopie.presenter;

import hackerearth.satya.tomatopie.model.City;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public interface LocationSelectionViewInterface {
    void onLocationExists(City city);

    void onLocationInvalid();

    void onGpsEnabled();

    void onGpsNotEnabled();
}