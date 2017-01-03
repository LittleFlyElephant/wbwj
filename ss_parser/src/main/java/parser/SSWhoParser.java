package parser;

import model.SSModel;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.*;

import java.util.Iterator;

/**
 * Created by 管通 on 2016/12/27.
 * 通过ansj的词性标注提取出文本中的人名
 * 经过比较发现ansj提供的几种方法中NlpAnalysis识别人名的效果最好
 */
public class SSWhoParser extends SSParser{
    protected String extractKey(String ss){
        Result parserResult = NlpAnalysis.parse(ss);
        Iterator<Term> it = parserResult.iterator();
        StringBuilder builder = new StringBuilder();
        while(it.hasNext()){
            Term t = it.next();
            String nature = t.getNatureStr();
            if(nature.startsWith("nr")){
                builder.append(t.getRealName()+";");
            }
        }
        return builder.toString();
    }
    public void setKeyInSS(SSModel ssModel){
        String ss = ssModel.getValue();
        ssModel.setWho(extractKey(ss));
    }

    public static void main(String[] args){
        SSWhoParser ssWhoParser = new SSWhoParser();
        String words = "经审理查明：2013年9月2日，被告陈玉坤向原告借款200000元，约定还款期限为2013年10月2日止。当日，被告向原告出具借条，其内容为“借条。兹因经营资金周转需要，向吴选城，借款人民币贰拾万元整（小写：200000元）。本借款的月利率为2％，借款人应按月支付借款利息。借款期限为2013年9月2日起至2013年10月2日止。借款人未按期归还本金利息的，则应承担借款本金30％逾期还款违约金。如借款人未按期交纳利息的，或出借人认为有必要提前收回借款的，借款人应按出借人的要求提前归还本次借款的本金及利息，并承担出借人催讨本次借款所需的费用（含律师费，保全费，诉讼费，鉴定评估费用，公告费用、差旅费用）。该笔借款由简作林、（空白）提供担保，并承担连带保证责任，保证范围为借款本金、利息及出借人追款所需的全部费用，保证期限为两年。如借款人不能偿还本金利息的，出借人有权拍卖借款人和保证人名下的汽车、房子等一切财产，用于偿还本次借款、利息及其他所需费用。各方一致同意由永定县人民法院管辖本借款合同。管理本合同争议。借款人：陈玉坤。保证人：简作林。2013年9月2日。借款现金200000元，本人于2012年9月2日已收到。借款人：陈玉坤，2013年9月2日”。现被告仍欠原告200000元。";
        System.out.println(ToAnalysis.parse(words));
        System.out.println(NlpAnalysis.parse(words));
        System.out.println(DicAnalysis.parse(words));
        System.out.println(IndexAnalysis.parse(words));
        System.out.println(ssWhoParser.extractKey(words));
    }
}
