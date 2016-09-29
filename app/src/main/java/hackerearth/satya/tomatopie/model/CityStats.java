package hackerearth.satya.tomatopie.model;

import java.util.List;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public class CityStats {

    public CityStats(String popularityIndex, String nightLifeIndex, List<String > topCuisines, String cityTitle) {
        this.popularityIndex = popularityIndex;
        this.nightLifeIndex = nightLifeIndex;
        this.topCuisines = topCuisines;
        this.cityTitle = cityTitle;
    }

    public String popularityIndex;
    public String nightLifeIndex;
    public List<String> topCuisines;
    public String cityTitle;
}
