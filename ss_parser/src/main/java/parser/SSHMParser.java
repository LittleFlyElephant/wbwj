package parser;

import model.SSModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by C91CBQ on 2016/12/27.
 */
public class SSHMParser extends SSParser{

    @Override
    protected String extractKey(String ss) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+([\\u4e00-\\u9fa5]{0,1}元)|\\d+英镑");
        Matcher matcher = pattern.matcher(ss);
        while(matcher.find()) {
            System.out.println(matcher.group());
            result.append(matcher.group() + ";");
        }
        return result.toString();
    }

    @Override
    public void setKeyInSS(SSModel ssModel) {
        String ss = ssModel.getValue();
        ssModel.setHow_much(extractKey(ss));
    }

    public static void main(String[] args) {
        SSHMParser parser = new SSHMParser();
        String test = "2016年12月27日，111美元，222元，333英镑，444日元，555加币，666欧元";
        System.out.println(parser.extractKey(test));
    }

}
