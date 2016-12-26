package parser;

import model.SSModel;

/**
 * Created by raychen on 2016/12/26.
 */
public class SSParser {

    protected String extractKey(String ss){
        return "样例";
    }

    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWhere(extractKey(ss));
    }
}
