package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.SSModel;

/**
 * ansj对时间分词支持并不好,1954年会被认为是数词，所以直接使用正则进行匹配
 * @author Jackey Lee
 *
 */
public class SSWhenParser extends SSParser{
	
	protected String extractKey(String ss){
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+年\\d+月\\d+日\\d+时\\d+分|\\d+年\\d+月\\d+日\\d+时|\\d+年\\d+月\\d+日");
        Matcher matcher = pattern.matcher(ss);
        while(matcher.find()){
        	System.out.println(matcher.group());
        	builder.append(matcher.group()+";");
        }
        return builder.toString();
    }
    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWhen(extractKey(ss));
    }

    public static void main(String[] args){
        SSWhenParser ssWhenParser = new SSWhenParser();
        String words = "1994年8月9日76时23分，1994年8月90日23时原告刘某某向永吉县土地管理局递交《住宅用地申请书》，申请对位于缸窑镇二道村三社的房屋进行翻建。";
        System.out.println(ssWhenParser.extractKey(words));
    }

}
