package hackerearth.satya.tomatopie.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public class Functions {

    @Nullable
    public static double[] getGPS(Context context) {
        try {
            LocationManager lm = (LocationManager) context
                    .getSystemService(Context.LOCATION_SERVICE);

            Location location = null;

            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }

            if (hasGPSProvider(context)) {
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    if (hasNetworkProvider(context)) {
                        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
            } else if (hasNetworkProvider(context)) {
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            double[] gps = new double[2];
            if (location != null) {
                gps[0] = location.getLatitude();
                gps[1] = location.getLongitude();
            } else {
                gps[0] = 0;
                gps[1] = 0;
            }
            return gps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean hasGPSProvider(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) {
            return false;
        }
        final List<String> providers = mgr.getAllProviders();
        return providers != null && providers.contains(LocationManager.GPS_PROVIDER);
    }

    private static boolean hasNetworkProvider(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) {
            return false;
        }
        final List<String> providers = mgr.getAllProviders();
        return providers != null && providers.contains(LocationManager.NETWORK_PROVIDER);
    }
}
