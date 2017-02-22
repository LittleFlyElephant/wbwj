package parser;

import java.util.Iterator;
import java.util.List;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;

import model.SSModel;

//import com.hankcs.hanlp.seg.common.Term;
//import org.ansj.domain.Term;

/**
 * Created by 管通 on 2016/12/27.
 * 通过ansj的词性标注提取出文本中的人名
 * 经过比较发现ansj提供的几种方法中NlpAnalysis识别人名的效果最好
 * hanlp提供的N最短路径分词器识别机构名的效果最好
 */
public class SSWhoParser extends SSParser{
    protected String extractKey(String ss){
        Segment segment = new NShortSegment().enableOrganizationRecognize(true).enablePlaceRecognize(true).enableNameRecognize(true);
        StringBuilder builder = new StringBuilder();
        
        //hanlp
        List<com.hankcs.hanlp.seg.common.Term> termList = segment.seg(ss);
        Iterator<com.hankcs.hanlp.seg.common.Term> itr = termList.iterator();
        while(itr.hasNext()){
            com.hankcs.hanlp.seg.common.Term t = itr.next();
            String nature = t.nature.toString();
            if((nature.startsWith("nt") || nature.startsWith("ns") || nature.startsWith("nr")) && !builder.toString().contains(t.word.toString())){
               builder.append(t.word.toString()+";");
           }
        }
        
//        Result parserResult = NlpAnalysis.parse(ss);
//        Iterator<org.ansj.domain.Term> it = parserResult.iterator();
//        while(it.hasNext()){
//            org.ansj.domain.Term t = it.next();
//            String nature = t.getNatureStr();
//            if(nature.startsWith("nr") && !builder.toString().contains(t.getRealName())){
//                builder.append(t.getRealName()+";");
//            }
//        }

        return builder.toString();
    }
    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWho(extractKey(ss));
    }

    public static void main(String[] args){
        SSWhoParser ssWhoParser = new SSWhoParser();
        String words = "被告广德县人力资源和社会保障局于2010年1月15日对原广德县萤石矿劳动服务公司职工晏霞作出基本养老金核定，核定其月养老金待遇为434元。经审理查明，2013年至2014年11月期间被告罗奋新陆续向原告李秀梅借款。2014年11月7日经双方结算，被告罗奋新尚欠原告李秀梅借款本金50000元及利息12500元（按月利率2％计算），本息合计62500元。同日，被告罗奋新重新出具一张借条给原告李秀梅，该借条载明“兹向李秀梅借到人民币陆万贰仟伍佰元整，小写￥62500元。用于短期流动资金使用，借款期限6个月，如不按期归还，本人同意指定到将乐县人民法院诉讼受理该笔还款，由此产生的一切费用由罗奋新全部承担”。被告宋永昆以担保人身份在借条上签字，为被告罗奋新提供担保。借款于2015年5月6日到期后，被告罗奋新未还借款，至今尚欠原告李秀梅62500元，被告宋永昆也未履行担保义务。上述事实有原告李秀梅提供的身份证、借条等证据以及原告李秀梅及被告罗奋新在法庭上的陈述相互印证，被告宋永昆经依法传唤未到庭参加诉讼，视为自愿放弃举证、质证权利，应承担相应后果，本院对原告李秀梅提供的上述证据依法予以采信。";
    /*  System.out.println(ToAnalysis.parse(words));
        System.out.println(NlpAnalysis.parse(words));
        System.out.println(DicAnalysis.parse(words));
        System.out.println(IndexAnalysis.parse(words));*/
        System.out.println(ssWhoParser.extractKey(words));
        /*Segment segment = HanLP.newSegment().enableOrganizationRecognize(true).enablePlaceRecognize(true);
        List<Term> termList = segment.seg(words);
        System.out.println(termList);
        termList = NLPTokenizer.segment(words);
        System.out.println(termList);
        termList = IndexTokenizer.segment(words);
        System.out.println(termList);
        segment = new NShortSegment().enableOrganizationRecognize(true).enablePlaceRecognize(true);
        termList = segment.seg(words);
        System.out.println(termList);
        segment = new ViterbiSegment().enableOrganizationRecognize(true).enablePlaceRecognize(true);
        termList = segment.seg(words);
        System.out.println(termList);
        segment = new DijkstraSegment().enableOrganizationRecognize(true).enablePlaceRecognize(true);
        termList = segment.seg(words);
        System.out.println(termList);*/


    }
}
