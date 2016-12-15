package parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengrong on 2016/12/15.
 */
public class SSDParserByDate extends SSDParser {
    protected ArrayList<String> parseString(String str){
        ArrayList<String> res =  new ArrayList<String>();
        String[] sp = str.split("。");
        for (String s: sp) {
            if (!s.equals(" ")) res.add(s+"。");
        }
        return res;
    }

    public static void main(String[] args){
        SSDParser ssdParser = new SSDParserByDate();
        List<String> strings = ssdParser.parseString("综上，本院认定本案事实如下：1997年10月，西联公司为建设外墙砖生产线项目，与温州中行签订《固定资产贷款合同》（合同编号：972C027号），约定：贷款本金最高不超过70万美元，贷款期限为58个月，即从本合同签订之日起至2002年8月20日，贷款利率为三至五年按六个月浮动；等等。同年10月31日，温州中行依约向西联公司发放了70万美元的贷款。贷款到期后，西联公司没有偿还贷款本金及利息。1997年12月，西联公司为建设外墙砖生产线项目，与温州中行签订《固定资产贷款合同》（合同编号：972C040号），约定：贷款本金最高不超过35万美元，贷款期限为68个月，即从本合同签订之日起至2003年8月20日，贷款利率为三至五年按六个月浮动；等等。同年12月23日，温州中行依约向西联公司发放了35万美元的贷款。2002年9月20日，温州中行向西联公司发出两份《催收贷款通知书》，指出截止2002年9月20日，西联公司分别尚欠贷款本金70万美元及利息139178．49美元、贷款本金35万美元及利息87943．83美元，要求西联公司尽快归还贷款本息。西联公司于同年10月11日签收上述两份通知书。后，西联公司仅部分归还972C027号贷款合同项下20万美元，剩余贷款本金85万美元及利息没有偿还。 2004年6月25日，温州中行与信达公司签订《债权转让协议》，约定将包括本案所涉的上述债权在内的温州中行对西联公司享有的14笔债权及全部从权利转让给信达公司。同年10月9日、12月24日，浙江中行与信达公司在《浙江日报》上联合发布了《债权转让暨催收公告》、《债权转让公告》，二次均就包括本案上述债权在内的浙江中行及其下属机构已转让给信达公司的债权及从权利通知借款人、担保人转让事实，并同时进行了催收。2006年12月12日，信达公司与美伦公司（WilliamF．Harley，Ⅲ出具委托书授权许云辉代表美伦公司购买资产包，该协议由许云辉代表美伦公司签字）签订《单户资产转让协议》，确认：本协议由信达公司与美伦公司于2006年8月8日签署；信达公司及美伦公司在此同意，信达公司向美伦公司转让在下述资产（包括本案债权在内）项下拥有的全部权益，该项转让于2006年12月12日完成并生效；自2006年12月12日，信达公司在与该等资产所对应的借款合同、还款协议、担保合同及其他法律文件项下的全部权益也同时一并转让给美伦公司。2006年9月29日，国家发展和改革委员会向信达公司出具《对外转让不良债权备案确认书》，对信达公司向美伦公司转让浙江温州、丽水的不良债权予以备案，并告知据此备案确认书到外汇管理局办理债权转让备案登记。2006年11月29日，国家外汇管理局向信达公司出具批复，同意上述转让，并要求信达公司通知境外投资者或其境内代理人到国家外汇管理局浙江省分局办理备案登记。2006年12月12日，信达公司与美伦公司在《浙江日报》上联合发布了《资产转让暨催收公告》，就包括本案债权在内的信达公司已转让给美伦公司的债权及从权利通知借款人、担保人转让事实，并同时进行了催收。2006年12月20日，上述债权转让事宜在国家外汇管理局浙江省分局办理了备案登记，在办理备案登记时，提交的材料已注明了包括本案债权在内的各债权担保的具体情况。截止至2006年12月12日，西联公司尚欠的本金为85万美元，尚欠的利息及逾期利息不低于Fursa基金公司主张的427320．63美元。 美伦公司系2002年2月14日在开曼群岛依法登记的免税有限合伙公司，已于2006年12月19日在合伙公司登记处登记更名为Fursa基金公司。");

        for (String str:strings) {
            System.out.println(str);
        }
    }
}