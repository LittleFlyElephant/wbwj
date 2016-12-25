package parser;

import model.SSDModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengrong on 2016/12/15.
 * 本周主要工作有：
 1、加入事实段定位的代码，不仅仅看查明事实段和前审审理段
 2、加入按照日期和关键字的事实段拆解方法，并测试分析
 3、修改原有按照句号分解的方法，去除空格并加入引号的判断
 4、加入拆解后的过滤器，用于去除拆解完后不属于事实的部分
 5、测试和统计结果

 */
public class SSDParserByDate extends SSDParser {
    public ArrayList<String> parseString(String str){
        ArrayList<String> res =  new ArrayList<String>();
//        String[] sp = str.split("。");
//        for (String s: sp) {
//            if (!s.equals(" ")) res.add(s+"。");
//        }
        char[] chars = str.toCharArray();
        int j = 0;//标记一个段的开头
        int i = 0;
        for(i=0;i<chars.length-1;i++){
            char a = chars[i];
            if(a == '。'){
                //看之后的字符串是否是日期,如果是则进行划分
                if(i+6 <chars.length){
                    if(isDate(str.substring(i+1,i+6))){
                        res.add(str.substring(j,i+1));
                        j = i+1;
                    }
                }
            }
        }
        if(j<i+1){
            res.add(str.substring(j,i+1));
        }
        return res;
    }
    private boolean isDate(String str){
        if(str.contains("年")){
            return true;
        }
        return false;
    }
    //拆解事实段
    public ArrayList<SSDModel> parseSSD(ArrayList<SSDModel> ssds){
        ArrayList<SSDModel> ssdModels = new ArrayList<SSDModel>();
        for (SSDModel ssd: ssds) {
            //拆解过程
            if (ssd.getValue() == null) continue;
            ssd.setDetails(parseString(ssd.getValue()));
            ssdModels.add(ssd);
        }
        return ssdModels;
    }

    public static void main(String[] args){
        SSDParser ssdParser = new SSDParserByDate();
        List<String> strings = ssdParser.parseString("经审理查明：2010年4月15日，原、被告签订了劳动合同书，合同期限，2010年4月15日至2012年12月25日，后双方于2010年10月3日正式建立了劳动关系。" +
                "2012年12月25日，原、被告经协商签订了一份解除劳动关系协议书，约定解除双方的劳动关系，劳动关系解除后，原告又在被告处继续工作至2013年3月15日，在双方劳动关系存续期间，被告每月向原告支付工资2400元。被告为原告办理并缴纳了2010年11月至2012年12月的养老保险。 另查明，原、被告解除劳动关系后，原告于2013年12月10日向甘州区劳动人事争议仲裁委员会申请仲裁，要求解除与被告的劳动合同，并要求被告为其出具解除劳动合同证明书，由被告向其支付解除劳动合同经济补偿金、支付未签订书面劳动合同和未签订无固定期限劳动合同的双倍工资、补缴2013年1-3月的社会保险费、赔偿失业保险损失。" +
                "2014年1月30日，甘州区劳动人事争议仲裁委员会作出（2013）甘区劳人裁字第200号裁决书，裁决由被告为原告出具解除劳动合同的证明、被告向原告支付解除劳动合同经济补偿金6000元、未签订书面劳动合同的双倍工资17800元、未签订无固定期限书面劳动合同的双倍工资31000元。被告某某种业公司不服此裁决书，于2014年2月12日向本院提起诉讼。本院于2014年3月27日对本案进行审理，庭审中，原告当庭认可双方已协议解除劳动关系。对解除双方劳动合同的请求不再主张，仅要求被告为其出具解除劳动合同证明。后本院于2014年10月14日作出（2014）甘民初字第1023号民事判决书，判决：一、被告为原告出具解除劳动合同的证明，并在十五日内为原告办理档案和社会保险关系转移手续；二、由被告向原告支付解除劳动关系的经济补偿金6000元（2400元／月×2．5月）；三、被告向原告支付2013年1月25日-3月15日未签订书面劳动合同的双倍工资4800元；四、驳回原告要求被告支付其2010年11月至2011年11月未签订劳动合同双倍工资、支付劳动合同期满后未续签劳动合同双倍工资、未签订无固定期限书面劳动合同的双倍工资的请求；五、驳回原告要求被告缴纳社会保险费、赔偿失业保险损失的申诉请求。判决生效后，因被告未自动履行，原告已申请本院强制执行。 再查明，2015年4月11日，原告以“被告未自动履行判决书第一项，即为原告陈某出具解除劳动合同的证明，第二项，即被告向原告支付解除劳动关系的经济补偿金6000元”为由，向甘州区劳动人事争议仲裁委员会申请仲裁，要求被告支付原告解除劳动合同经济补偿金的额外补偿金3000元。该委受理后，于2015年6月8日作出（2015）甘区劳人裁字第71号裁决书，以原告陈某的请求超过仲裁时效为由，裁决，驳回原告陈某的仲裁请求。原告不服该仲裁裁决，于同年6月23日向本院提起诉讼，要求被告支付原告解除劳动合同经济补偿金的额外补偿金3000元。");

        for (String str:strings) {
            System.out.println(str);
        }
    }
}
