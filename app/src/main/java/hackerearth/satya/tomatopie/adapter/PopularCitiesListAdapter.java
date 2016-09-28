package hackerearth.satya.tomatopie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackerearth.satya.tomatopie.R;
import hackerearth.satya.tomatopie.model.City;
import hackerearth.satya.tomatopie.model.PopularCity;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public class PopularCitiesListAdapter extends BaseAdapter {

    private final List<City> cities;
    private final LayoutInflater inflater;

    public PopularCitiesListAdapter(Context context, List<City> cities) {
        this.cities = cities;
        inflater = LayoutInflater.from(context);
    }

    public void setCities(List<City> cities) {
        this.cities.clear();

        if (cities.size() == 0) {
            cities = PopularCity.getCities();
        }
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public City getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;

        if (v == null) {
            v = inflater.inflate(R.layout.row_popular_city, null);
            viewHolder = new ViewHolder();
            viewHolder.cityName = (TextView) v.findViewById(R.id.popular_city_name);
            viewHolder.cityIcon = (ImageView) v.findViewById(R.id.popular_city_icon);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        City city = getItem(position);
        viewHolder.cityName.setText(city.name);
        if (city.getIcon() != 0) {
            viewHolder.cityIcon.setBackgroundResource(city.icon);
        }
        return v;
    }

    private class ViewHolder {
        TextView cityName;
        ImageView cityIcon;
    }
}
