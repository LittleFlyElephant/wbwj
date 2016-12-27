package parser;

import model.SSModel;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raychen on 2016/12/27.
 */
public class SSWhereParser extends SSParser {

    private String[] preps = {"在","于"};

    @Override
    protected String extractKey(String ss) {
        String ans = "";
        Result result = NlpAnalysis.parse(ss);
        List<Term> originTerms = result.getTerms();
        int p = 0;
        while (p < originTerms.size()){
            Term term = originTerms.get(p);
//            System.out.println(term.getName()+" "+term.getNatureStr());
            p ++;
            if (term.getNatureStr().equals("p")){//介词
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
                if (place.length() > 0) ans += term+place+";";
            }
        }
        return ans;
    }

    @Override
    public void setKeyInSS(SSModel ssModel) {
        String ss = ssModel.getValue();
        ssModel.setWhere(extractKey(ss));
    }

    public static void main(String[] args) {
        SSWhereParser parser = new SSWhereParser();
        String test = "2013年10月底，原告又就医于西安中医脑病医院，被诊断为外伤性震颤";
        String places = parser.extractKey(test);
        System.out.println(places);
    }
}
