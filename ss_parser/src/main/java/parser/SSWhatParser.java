package parser;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import model.SSModel;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;

/**
 * Created by DengrongGuan on 2016/12/26.
 */
public class SSWhatParser extends SSParser{
    public static String findDescriptions(Map<String,String> link, String name){
        String result = name;
        while(link.containsKey(name)){
            result = link.get(name)+result;
            name = link.get(name);
        }
        return result;
    }
    private String hanlpParse(String ss){

        Set<String> results = new HashSet<String>();
        String result = "";
        CoNLLSentence sentence = HanLP.parseDependency(ss);
//        System.out.println(sentence);
        // 可以方便地遍历它
        Map<String,String> describeLink = new HashMap<String, String>();
        for (CoNLLWord word : sentence)
        {
            if(word.DEPREL.equals("定中关系")){
                describeLink.put(word.HEAD.LEMMA,word.LEMMA);
            }
            if(word.DEPREL.equals("动宾关系")){
                String tmp = findDescriptions(describeLink,word.LEMMA);
                if(results.contains(tmp)){
                    continue;
                }
                results.add(tmp);
                result += tmp+";";
            }
//            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        return result;
    }
    protected String extractKey(String ss){
        //词性标注规范 https://github.com/NLPchina/ansj_seg/wiki/%E8%AF%8D%E6%80%A7%E6%A0%87%E6%B3%A8%E8%A7%84%E8%8C%83
//        Result parserResult = NlpAnalysis.parse(ss);
//        Iterator<Term> it = parserResult.iterator();
//        StringBuilder builder = new StringBuilder();
//        while(it.hasNext()){
//            Term t = it.next();
//            String nature = t.getNatureStr();
//            if(nature.startsWith("n")){
//                if(nature.startsWith("nr") || nature.startsWith("ns")){
//                    continue;
//                }else{
//                    builder.append(t.getRealName()+";");
//                }
//            }
//        }
//        return builder.toString();
        //用只提取书名的方式尝试一下
        System.out.println("parse ss: "+ss);
        String result = "";
        int start  = 0;
        int end = 0;
        Set<String> results = new HashSet<String>();
        for(int i = 0;i<ss.length();i++){
            if(ss.charAt(i) == '《'){
                start = i+1;
            }else if(ss.charAt(i) == '》'){
                end = i;
                if(results.contains(ss.substring(start,end))){
                    continue;
                }
                result += ss.substring(start,end)+";";
                results.add(ss.substring(start,end));
            }
        }
        if(result.equals("")){
            if(ss.contains("(")||ss.contains("（")){
                //带括号好像有问题，待解决
                //TODO
                return result;
            }
            result = hanlpParse(ss);
        }
        return result;

    }
    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWhat(extractKey(ss));
    }

    public static void main(String[] args){
        SSWhatParser ssWhatParser = new SSWhatParser();
        String words = "原告现依法诉讼，要求被告偿还借款本金10万元及利息,利息从起诉之日起，按中国人民银行发布的利率标准计算";
//        System.out.println(NlpAnalysis.parse(words));
        System.out.println(ssWhatParser.extractKey(words));
//        Result result = ToAnalysis.parse(words);
//        List<Term> terms = result.getTerms();
//        System.out.println(result.get(0).getNatureStr());

    }

}
