package hackerearth.satya.tomatopie.presenter;

/**
 * Created by Satya Chaitanya on 9/29/16.
 */

public interface MainActivityDataInterface {

    void getCityId(String name);

    void getCityInfo(String name, int entityId, String entityName);

    void getRestrauntDetails(String name);
}
