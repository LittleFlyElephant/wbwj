package parser;

import model.SSModel;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by raychen on 2016/12/27.
 */
public class SSWhereParser extends SSParser {

    //表示地点前的介词
    private ArrayList<String> preps = new ArrayList<String>(Arrays.asList("在","于","至","往","从","沿"));

    @Override
    protected String extractKey(String ss) {
        String ans = "";
        //使用ansj进行分析
        Result result = NlpAnalysis.parse(ss);
        List<Term> originTerms = result.getTerms();
        int p = 0;
        while (p < originTerms.size()){
            Term term = originTerms.get(p);
//            System.out.println(term);
            p ++;
            //找到对应介词,表示之后的词可能是地点
            if (term.getNatureStr().equals("p") && preps.contains(term.getName())){//介词
                String place = "";
                while (p < originTerms.size()){
                    Term innerTerm = originTerms.get(p);
                    //连接名词,代词等,用于组成一个地点
                    if (innerTerm.getNatureStr().startsWith("n") ||
                            innerTerm.getNatureStr().startsWith("r") ||
                            innerTerm.getNatureStr().equals("s")){
                        place += innerTerm.getName();
                        p ++;
                        continue;
                    }
                    break;
                }
                //去除非地点词,感觉这里需要改进
                if (place.length() > 0 &&
                        !place.startsWith("本院") &&
                        !place.startsWith("被告")&&
                        !place.startsWith("本案")&&
                        !place.equals("范围")&&
                        !place.equals("界限"))
                    ans += place+";";
            }
        }
        return ans;
    }

    @Override
    public void setKeyInSS(SSModel ssModel) {
        String ss = ssModel.getValue();
        String res = extractKey(ss);
        //去掉空串
        if (!res.equals("")) ssModel.setWhere(res);
    }

    public static void main(String[] args) {
        SSWhereParser parser = new SSWhereParser();
        String test = "四至界限为：东邻旱沟，南邻覃朝良旱地，西邻杉树，北邻旱沟";
        String places = parser.extractKey(test);
        System.out.println(places);
    }
}
