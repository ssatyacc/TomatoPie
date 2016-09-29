package hackerearth.satya.tomatopie.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hackerearth.satya.tomatopie.R;
import hackerearth.satya.tomatopie.model.RestaurantInfo;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public class RestaurantsListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private List<RestaurantInfo> restaurants = new ArrayList<>();

    public RestaurantsListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public RestaurantInfo getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder vh;
        Context context = parent.getContext();

        if (v == null) {
            v = inflater.inflate(R.layout.row_restaurant, null);
            vh = new ViewHolder();
            vh.layout = (RelativeLayout) v.findViewById(R.id.layout_restaurant);
            vh.title = (TextView) v.findViewById(R.id.title);
            vh.locality = (TextView) v.findViewById(R.id.locality);
            vh.address = (TextView) v.findViewById(R.id.address);
            vh.cuisines = (TextView) v.findViewById(R.id.cuisines);
            vh.rating = (TextView) v.findViewById(R.id.rating);
            vh.costForTwo = (TextView) v.findViewById(R.id.costForTwo);
            vh.onlineDelivery = (TextView) v.findViewById(R.id.hasOnlineDelivery);
            vh.deliveringNow = (TextView) v.findViewById(R.id.isDeliverignNow);
            vh.tableBooking = (TextView) v.findViewById(R.id.hasTableBooking);
            vh.photosUrl = (ImageView) v.findViewById(R.id.photos);
            vh.menuUrl = (ImageView) v.findViewById(R.id.menu);

            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        RestaurantInfo restaurantInfo = getItem(position);

        vh.title.setText(restaurantInfo.name);
        vh.locality.setText(restaurantInfo.locality);
        vh.address.setText(restaurantInfo.address);
        vh.cuisines.setText(restaurantInfo.cuisines);
        vh.rating.setText("Rating- " + restaurantInfo.rating);
        vh.costForTwo.setText("Cost for 2:" + restaurantInfo.costForTwo + " " +
                restaurantInfo.currencyUnits);
        vh.onlineDelivery.setText("OnlineDelivery - " +
                (restaurantInfo.hasOnlineDelivery == 1 ? "Yes" : "No"));
        vh.deliveringNow.setText("Delivering Now - " +
                (restaurantInfo.isDeliveringNow == 1 ? "Yes" : "No"));
        vh.tableBooking.setText("Table Booking - " +
                (restaurantInfo.hasTableBooking == 1 ? "Yes" : "No"));

        if (!TextUtils.isEmpty(restaurantInfo.photosUrl)) {
            vh.photosUrl.setVisibility(View.VISIBLE);
            Picasso.with(context).load(restaurantInfo.photosUrl).error(R.mipmap.ic_launcher).into(vh.photosUrl);
        } else {
            vh.photosUrl.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(restaurantInfo.menuUrl)) {
            vh.menuUrl.setVisibility(View.VISIBLE);
            Picasso.with(context).load(restaurantInfo.menuUrl).error(R.mipmap.ic_launcher).into(vh.menuUrl);
        } else {
            vh.menuUrl.setVisibility(View.GONE);
        }

        return v;
    }

    public void setRestaurants(List<RestaurantInfo> restaurants) {
        this.restaurants.clear();
        this.restaurants.addAll(restaurants);
        notifyDataSetChanged();
    }

    private class ViewHolder {
        RelativeLayout layout;
        TextView title;
        TextView locality;
        TextView address;
        TextView cuisines;
        TextView rating;
        TextView costForTwo;
        TextView onlineDelivery;
        TextView deliveringNow;
        TextView tableBooking;
        ImageView photosUrl;
        ImageView menuUrl;
    }
}
