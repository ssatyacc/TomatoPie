package hackerearth.satya.tomatopie.model;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public class RestaurantInfo implements Comparable<RestaurantInfo> {

    public String id;
    public String name;
    public String url;
    public String address;
    public String locality;
    public double[] location;
    public String cuisines;
    public int costForTwo;
    public int costCategory;
    public String currencyUnits;
    public String thumb;

    public double rating;
    public String ratingColor;
    public String ratingComment;
    public int votes;
    public int hasTableBooking;
    public int isDeliveringNow;
    public int hasOnlineDelivery;

    public String photosUrl;
    public String menuUrl;
    public String featuredImage;

    @Override
    public int compareTo(RestaurantInfo o) {
        if (rating > o.rating) {
            return -1;
        }

        if (rating == o.rating) {
            if (votes > o.votes) {
                return -1;
            }

            if (votes == o.votes) {
                return 0;
            }

            return 1;
        }

        return 1;
    }
}
