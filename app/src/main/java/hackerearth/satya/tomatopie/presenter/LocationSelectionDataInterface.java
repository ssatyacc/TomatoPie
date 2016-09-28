package hackerearth.satya.tomatopie.presenter;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public interface LocationSelectionDataInterface {

    void getCityByLocation(String locationName);

    void getCitiesByLocation(String locationName);

    void getCityByLatLng(double lat, double lng);
}
