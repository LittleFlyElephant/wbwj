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

    private ArrayList<String> preps = new ArrayList<String>(Arrays.asList("在","于","至","往","从","沿"));

    @Override
    protected String extractKey(String ss) {
        String ans = "";
        Result result = NlpAnalysis.parse(ss);
        List<Term> originTerms = result.getTerms();
        int p = 0;
        while (p < originTerms.size()){
            Term term = originTerms.get(p);
//            System.out.println(term);
            p ++;
            if (term.getNatureStr().equals("p") && preps.contains(term.getName())){//介词
                String place = "";
                while (p < originTerms.size()){
                    Term innerTerm = originTerms.get(p);
                    //名词
                    if (innerTerm.getNatureStr().startsWith("n") ||
                            innerTerm.getNatureStr().startsWith("r") ||
                            innerTerm.getNatureStr().equals("s")){
                        place += innerTerm.getName();
                        p ++;
                        continue;
                    }
                    break;
                }
                //筛选
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
        if (!res.equals("")) ssModel.setWhere(res);
    }

    public static void main(String[] args) {
        SSWhereParser parser = new SSWhereParser();
        String test = "四至界限为：东邻旱沟，南邻覃朝良旱地，西邻杉树，北邻旱沟";
        String places = parser.extractKey(test);
        System.out.println(places);
    }
}
