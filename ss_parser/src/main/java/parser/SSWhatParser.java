package parser;

import model.SSModel;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.Iterator;
import java.util.List;

/**
 * Created by DengrongGuan on 2016/12/26.
 */
public class SSWhatParser extends SSParser{
    protected String extractKey(String ss){
        //词性标注规范 https://github.com/NLPchina/ansj_seg/wiki/%E8%AF%8D%E6%80%A7%E6%A0%87%E6%B3%A8%E8%A7%84%E8%8C%83
        Result parserResult = NlpAnalysis.parse(ss);
        Iterator<Term> it = parserResult.iterator();
        StringBuilder builder = new StringBuilder();
        while(it.hasNext()){
            Term t = it.next();
            String nature = t.getNatureStr();
            if(nature.startsWith("n")){
                if(nature.startsWith("nr") || nature.startsWith("ns")){
                    continue;
                }else{
                    builder.append(t.getRealName()+";");
                }
            }
        }
        return builder.toString();
    }
    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWhat(extractKey(ss));
    }

    public static void main(String[] args){
        SSWhatParser ssWhatParser = new SSWhatParser();
        String words = "1994年8月9日，原告刘某某向永吉县土地管理局递交《住宅用地申请书》，申请对位于缸窑镇二道村三社的房屋进行翻建。";
        System.out.println(NlpAnalysis.parse(words));
        System.out.println(ssWhatParser.extractKey(words));
//        Result result = ToAnalysis.parse(words);
//        List<Term> terms = result.getTerms();
//        System.out.println(result.get(0).getNatureStr());

    }

}
