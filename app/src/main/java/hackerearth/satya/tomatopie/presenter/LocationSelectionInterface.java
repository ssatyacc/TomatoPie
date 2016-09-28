package hackerearth.satya.tomatopie.presenter;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public interface LocationSelectionInterface {

    void onLocationSelected(String locationName);

    void onLocationSelected(double lat, double lng);

    void onUseCurrentLocationSelected();

    void onLocationManuallyEntered(String locationName);
}
