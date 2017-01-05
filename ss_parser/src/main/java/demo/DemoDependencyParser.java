package demo;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengrong on 2017/1/3.
 */
public class DemoDependencyParser {
    public static String findDescriptions(Map<String,String> link, String name){
        String result = name;
        while(link.containsKey(name)){
            result = link.get(name)+result;
            name = link.get(name);
        }
        return result;
    }
    //依存句法分析
    public static void main(String[] args)
    {
        CoNLLSentence sentence = HanLP.parseDependency("围绕案件争议焦点，原告向法庭提供了如下证据：事故认定书、医疗费收据、诊断书、病历、一日清单、鉴定报告、鉴定费收据，原告用以上证据证明发生交通事故自己受伤及花销情况");
//        System.out.println(sentence);
        // 可以方便地遍历它
        Map<String,String> describeLink = new HashMap<String, String>();
        for (CoNLLWord word : sentence)
        {
            if(word.DEPREL.equals("定中关系")){
                describeLink.put(word.HEAD.LEMMA,word.LEMMA);
            }
            if(word.DEPREL.equals("动宾关系")){
                System.out.println(findDescriptions(describeLink,word.LEMMA));
            }
//            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }

        System.out.println("----------------------------------------------");
//         也可以直接拿到数组，任意顺序或逆序遍历
        CoNLLWord[] wordArray = sentence.getWordArray();
        for (int i = wordArray.length - 1; i >= 0; i--)
        {
            CoNLLWord word = wordArray[i];
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
//        System.out.println("----------------------------------------------");
//        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
//        CoNLLWord head = wordArray[12];
//        while ((head = head.HEAD) != null)
//        {
//            if (head == CoNLLWord.ROOT) System.out.println(head.LEMMA);
//            else System.out.printf("%s --(%s)--> ", head.LEMMA, head.DEPREL);
//        }
    }
}
