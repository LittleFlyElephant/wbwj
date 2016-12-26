package parser;

import model.SSModel;

/**
 * Created by DengrongGuan on 2016/12/26.
 */
public class SSWhatParser extends SSParser{
    protected String extractKey(String ss){
        return "what";
    }
    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWhat(extractKey(ss));
    }

}
