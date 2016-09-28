package hackerearth.satya.tomatopie.presenter.implement;

import java.util.List;

import hackerearth.satya.tomatopie.model.City;
import hackerearth.satya.tomatopie.presenter.LocationSelectionCallbackInterface;
import hackerearth.satya.tomatopie.presenter.LocationSelectionDataInterface;
import hackerearth.satya.tomatopie.presenter.LocationSelectionInterface;
import hackerearth.satya.tomatopie.presenter.LocationSelectionViewInterface;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public class LocationSelectionPresenter implements LocationSelectionInterface,
        LocationSelectionCallbackInterface {

    private final LocationSelectionViewInterface viewInterface;
    private final LocationSelectionDataInterface dataInterface;

    public LocationSelectionPresenter(LocationSelectionViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        this.dataInterface = new LocationSelectionDataInterfaceImpl(this);
    }

    @Override
    public void onLocationSelected(String locationName) {
        dataInterface.getCityByLocation(locationName);
    }

    @Override
    public void onLocationSelected(double lat, double lng) {
        dataInterface.getCityByLatLng(lat, lng);
    }

    @Override
    public void onUseCurrentLocationSelected() {
        // TODO: 9/28/16 Ask for GPS if not exists and follow
    }

    @Override
    public void onLocationManuallyEntered(String locationName) {
        dataInterface.getCitiesByLocation(locationName);
    }

    @Override
    public void onCityObtained(City city) {
        viewInterface.onLocationExist(city);
    }

    @Override
    public void onCitiesObtained(List<City> cities) {
        viewInterface.onLocationsExist(cities);
    }

    @Override
    public void onFailure() {
        viewInterface.onLocationInvalid();
    }
}
