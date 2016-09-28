package hackerearth.satya.tomatopie.model;

import java.util.ArrayList;
import java.util.List;

import hackerearth.satya.tomatopie.R;

/**
 * Created by Satya Chaitanya on 9/27/16.
 */

public enum PopularCity {
    DELHI("Delhi NCR", R.mipmap.ic_launcher),
    MUMBAI("Mumbai", R.mipmap.ic_launcher),
    BENGALURU("Bengaluru", R.mipmap.ic_launcher),
    HYDERABAD("Hyderabad", R.mipmap.ic_launcher),
    MADRAS("Madras", R.mipmap.ic_launcher),
    KOLKATA("Kolkata", R.mipmap.ic_launcher);

    public final String cityName;
    public final int cityIcon;

    PopularCity(String cityName, int cityIcon) {
        this.cityName = cityName;
        this.cityIcon = cityIcon;
    }

    public static List<City> getCities() {
        List<City> cities = new ArrayList<>();
        for (PopularCity popularCity : PopularCity.values()) {
            City city = new City(0, popularCity.cityName, 0, "", 0, 0, "", "");
            city.setIcon(popularCity.cityIcon);
            cities.add(city);
        }

        return cities;
    }
}
