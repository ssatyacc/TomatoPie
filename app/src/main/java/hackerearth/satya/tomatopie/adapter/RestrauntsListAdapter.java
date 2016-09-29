package hackerearth.satya.tomatopie.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import hackerearth.satya.tomatopie.model.RestaurantInfo;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public class RestrauntsListAdapter extends BaseAdapter {
    private List<RestaurantInfo> restraunts;

    // TODO: 9/29/16 Fill the restraunt details !

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setRestaurants(List<RestaurantInfo> restraunts) {
        this.restraunts.clear();

        this.restraunts.addAll(restraunts);
        notifyDataSetChanged();
    }
}
