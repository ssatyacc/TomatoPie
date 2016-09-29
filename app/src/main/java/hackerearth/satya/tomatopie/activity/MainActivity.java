package hackerearth.satya.tomatopie.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import hackerearth.satya.tomatopie.R;
import hackerearth.satya.tomatopie.SharedPrefs;
import hackerearth.satya.tomatopie.adapter.RestrauntsListAdapter;
import hackerearth.satya.tomatopie.databinding.ActivityMainBinding;
import hackerearth.satya.tomatopie.model.City;
import hackerearth.satya.tomatopie.model.CityStats;
import hackerearth.satya.tomatopie.model.RestaurantInfo;
import hackerearth.satya.tomatopie.presenter.MainActivityViewInterface;
import hackerearth.satya.tomatopie.presenter.implement.MainActivityPresenter;
import hackerearth.satya.tomatopie.utils.Functions;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MainActivityViewInterface {

    ActivityMainBinding B;
    MainActivityPresenter presenter;
    View header;

    public static void start(Context context, City city) {
        Intent starter = new Intent(context, MainActivity.class);
        new SharedPrefs(context).setCityName(city.name);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = DataBindingUtil.setContentView(this, R.layout.activity_main);

        String savedCity = new SharedPrefs(this).getCityName();
        if (TextUtils.isEmpty(savedCity)) {
            LocationSelectionActivity.start(this);
            finish();
        }

        presenter = new MainActivityPresenter(this);

        header = getLayoutInflater().inflate(R.layout.view_header, null);
        ((TextView) header.findViewById(R.id.cityName)).setText(savedCity);
        B.listRestraunts.addHeaderView(header);
        B.listRestraunts.setAdapter(new RestrauntsListAdapter());

        B.listRestraunts.setOnItemClickListener(this);
        presenter.onCreated(savedCity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO: 9/29/16 Get restraunt objct and show info of the same!
    }

    @Override
    public void showLoading() {
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
        findViewById(R.id.retry).setVisibility(View.GONE);
        B.listRestraunts.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        findViewById(R.id.loading).setVisibility(View.GONE);
        findViewById(R.id.retry).setVisibility(View.VISIBLE);
        B.listRestraunts.setVisibility(View.GONE);
    }

    @Override
    public void showRestaurantsInfo(CityStats stats, List<RestaurantInfo> restaurantInfo) {
        ((TextView) header.findViewById(R.id.nightLifeIndex))
                .setText(String.format(getString(R.string.nightlife_index), stats.nightLifeIndex));
        ((TextView) header.findViewById(R.id.popularity))
                .setText(String.format(getString(R.string.popularity), stats.nightLifeIndex));

        ((TextView) header.findViewById(R.id.topCuisines)).setText(Functions.concatStrings(stats.topCuisines));
        ((RestrauntsListAdapter) B.listRestraunts.getAdapter()).setRestaurants(restaurantInfo);

        findViewById(R.id.loading).setVisibility(View.GONE);
        findViewById(R.id.retry).setVisibility(View.GONE);
        B.listRestraunts.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRestaurant(RestaurantInfo info) {

    }
}
