package parser;

import model.SSModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author: Qi Bao
 * @Date: 12/27/2016
 * @Description: How Much 即对文书事实中出现的金额进行分析，经过文书的阅读和整理，发现金额表述较为规范，基本都以"数字+元"的方式出现，出现过美元单位。因此采用正则表达式匹配的方式对金额进行提取，考虑到可能少量的外币，对"元"进行单字模糊匹配，结果会附带单位。
 * @Test Case: "2016年12月27日，111美元，222元，333英镑，444日元，555加币，666欧元"
 * @Test Outcome: "111美元；222元；333英镑；444日元；666欧元；"
 *
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
