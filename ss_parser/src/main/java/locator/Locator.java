package locator;

import model.SSDModel;

import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/17.
 */
public class Locator {

    private SSDLocator locator;

    public Locator() {
        locator = SSDLocator.getInstance();
    }

    public ArrayList<SSDModel> getSSDs(String fileType, String filePath){
        ArrayList<SSDModel> ret = new ArrayList<SSDModel>();
        SSDModel result = locator.getSSD(filePath, fileType);
        if (result != null) ret.add(result);
        return ret;
    }
}
