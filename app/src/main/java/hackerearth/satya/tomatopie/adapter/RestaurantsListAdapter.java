package hackerearth.satya.tomatopie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
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
import java.util.Locale;

import hackerearth.satya.tomatopie.R;
import hackerearth.satya.tomatopie.model.RestaurantInfo;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public class RestaurantsListAdapter extends BaseAdapter {

    private static final String TAG = "RestaurantsListAdapter";

    private LayoutInflater mInflater;
    private List<RestaurantInfo> mRestaurantsList = new ArrayList<>();

    public RestaurantsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mRestaurantsList.size();
    }

    @Override
    public RestaurantInfo getItem(int position) {
        return mRestaurantsList.get(position);
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
            v = mInflater.inflate(R.layout.row_restaurant, null);
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
            vh.featuredImage = (ImageView) v.findViewById(R.id.photos);
            vh.thumb = (ImageView) v.findViewById(R.id.menu);

            v.setTag(vh);
        } else {
            vh = (ViewHolder) v.getTag();
        }
        final RestaurantInfo restaurantInfo = getItem(position);

        vh.title.setText(restaurantInfo.name);
        vh.locality.setText(restaurantInfo.locality);
        vh.address.setText(restaurantInfo.address);
        vh.cuisines.setText(restaurantInfo.cuisines);
        vh.rating.setText(restaurantInfo.rating + "(" + restaurantInfo.votes + "votes)");
        vh.costForTwo.setText(restaurantInfo.costForTwo +
                restaurantInfo.currencyUnits + " approx. for 2");
        vh.onlineDelivery.setText("OnlineDelivery - " +
                (restaurantInfo.hasOnlineDelivery == 1 ? "Yes" : "No"));
        vh.deliveringNow.setText("Delivering Now - " +
                (restaurantInfo.isDeliveringNow == 1 ? "Yes" : "No"));
        vh.tableBooking.setText("Table Booking - " +
                (restaurantInfo.hasTableBooking == 1 ? "Yes" : "No"));

        if (!TextUtils.isEmpty(restaurantInfo.featuredImage)) {
            Log.d(TAG, "getView: " + restaurantInfo.photosUrl);
            vh.featuredImage.setVisibility(View.VISIBLE);
            Picasso.with(context).load(restaurantInfo.featuredImage).error(R.mipmap.ic_launcher).into(vh.featuredImage);
        } else {
            vh.featuredImage.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(restaurantInfo.thumb)) {
            vh.thumb.setVisibility(View.VISIBLE);
            Picasso.with(context).load(restaurantInfo.thumb).error(R.mipmap.ic_launcher).into(vh.thumb);
        } else {
            vh.thumb.setVisibility(View.GONE);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(%s)", restaurantInfo.location[0], restaurantInfo.location[1], restaurantInfo.name);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);
            }
        };
        vh.locality.setOnClickListener(listener);
        vh.address.setOnClickListener(listener);

        return v;
    }

    public void setmRestaurantsList(List<RestaurantInfo> mRestaurantsList) {
        this.mRestaurantsList.clear();
        this.mRestaurantsList.addAll(mRestaurantsList);
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
        ImageView featuredImage;
        ImageView thumb;
    }
}
