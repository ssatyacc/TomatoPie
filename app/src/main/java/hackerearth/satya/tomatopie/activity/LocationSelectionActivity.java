package hackerearth.satya.tomatopie.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hackerearth.satya.tomatopie.R;
import hackerearth.satya.tomatopie.adapter.PopularCitiesListAdapter;
import hackerearth.satya.tomatopie.databinding.ActivityLocationSelectionBinding;
import hackerearth.satya.tomatopie.model.City;
import hackerearth.satya.tomatopie.presenter.LocationSelectionViewInterface;
import hackerearth.satya.tomatopie.presenter.implement.LocationSelectionPresenter;
import hackerearth.satya.tomatopie.utils.Functions;

public class LocationSelectionActivity extends AppCompatActivity implements View.OnClickListener,
        LocationSelectionViewInterface, TextWatcher, AdapterView.OnItemClickListener {

    private static final String TAG = "LocationSelectionActivi";
    private static final int LOCATION_EDIT_TEXT_PROCESSING_BUFFER_TIME = 1000;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 007;
    private final Handler submitLocationManualHandler = new Handler();
    private ActivityLocationSelectionBinding B;
    private LocationSelectionPresenter presenter;
    private final Runnable submitLocationManually = new Runnable() {
        @Override
        public void run() {
            String locationName = B.locationEditText.getText().toString();
            // Empty locationName will show popular cities
            presenter.onLocationManuallyEntered(locationName);
        }
    };
    private List<City> citiesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = DataBindingUtil.setContentView(this, R.layout.activity_location_selection);
        presenter = new LocationSelectionPresenter(this);
        B.imageLocation.setOnClickListener(this);
        B.textCurrentLocation.setOnClickListener(this);
        B.listPopularLocation.setAdapter(new PopularCitiesListAdapter(this));
        B.listPopularLocation.setOnItemClickListener(this);
        B.locationEditText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == B.imageLocation || v == B.textCurrentLocation) {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        LocationSelectionActivity.REQUEST_CODE_LOCATION_PERMISSION);
            } else {
                presenter.onUseCurrentLocationSelected();
            }
        }
    }

    @Override
    public void onLocationExist(City city) {
        MainActivity.start(this, city);
        finish();
    }

    @Override
    public void onLocationsExist(List<City> cities) {
        citiesList.clear();
        citiesList.addAll(cities);
        ((PopularCitiesListAdapter) B.listPopularLocation.getAdapter()).setCities(cities);
    }

    @Override
    public void onLocationInvalid() {
        Toast.makeText(this, "Location Not Invalid! Enter manually!", Toast.LENGTH_LONG).show();
        B.locationEditText.requestFocus();
    }

    @Override
    public void onGpsEnabled() {
        double[] location = Functions.getGPS(this);
        if (location == null) {
            onGpsNotEnabled();
            return;
        }
        presenter.onLocationSelected(location[0], location[1]);
    }

    @Override
    public void onGpsNotEnabled() {
        Toast.makeText(this, "Location Not enabled! Enter manually!", Toast.LENGTH_LONG).show();
        B.locationEditText.requestFocus();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do Nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Do Nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        submitLocationManualHandler.removeCallbacks(submitLocationManually);
        submitLocationManualHandler.postDelayed(submitLocationManually,
                LOCATION_EDIT_TEXT_PROCESSING_BUFFER_TIME);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onLocationSelected((
                (PopularCitiesListAdapter) B.listPopularLocation.getAdapter())
                .getItem(position).name);
    }
}
