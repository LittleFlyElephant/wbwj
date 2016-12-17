package locator;

import util.XMLUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/17.
 */
public class Locator {

    private SSDLocator locator;

    public Locator() {
        locator = SSDLocator.getInstance();
    }

    public ArrayList<String> getSSDs(String fileType, String filePath){
        ArrayList<String> ret = new ArrayList<String>();
        String result = locator.getSSD(filePath, fileType);
        if (result != null) ret.add(result);
        return ret;
    }
}
