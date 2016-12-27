package filter;

import model.SSDModel;

import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/17.
 */
public class BaseFilter {

    protected String[] keywords_up = {"作出上述辩解。", "本院依法予以确认。"};

    protected boolean checkKeywords(String s){
        for (String key: keywords_up) {
            if (s.contains(key)){
                int index = s.indexOf(key);
                while (index>=0 && s.charAt(index) != ',') index --;
                if (index<0) return true;
            }
        }
        return false;
    }

    public ArrayList<SSDModel> filter(ArrayList<SSDModel> ssds){
        ArrayList<SSDModel> result = new ArrayList<SSDModel>();

        for (SSDModel ssd: ssds) {
            //获得拆解的事实
            ArrayList<String> ss = ssd.getDetails();
            ArrayList<String> newSs = new ArrayList<String>();
            //筛选
            for (String str: ss) {
                if (checkKeywords(str)) continue;
                newSs.add(str);
            }
            if (newSs.size()>0){
                ssd.setDetails(newSs);
                result.add(ssd);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String test = " 被告作出上述 辩 解。 ";
        BaseFilter filter = new BaseFilter();
//        System.out.println(filter.checkKeywords(test));
        System.out.println(test.trim());
    }
}
