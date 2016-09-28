package hackerearth.satya.tomatopie.model;

/**
 * Created by Satya Chaitanya on 9/28/16.
 */

public class City {
    public int id;
    public String name;
    public int countryId;
    public String countryName;
    public int isState;
    public int stateId;
    public String stateName;
    public String stateCode;
    public int icon;

    public City(int id, String name, int countryId, String countryName, int isState,
                int stateId, String stateName, String stateCode) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.countryName = countryName;
        this.isState = isState;
        this.stateId = stateId;
        this.stateName = stateName;
        this.stateCode = stateCode;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
