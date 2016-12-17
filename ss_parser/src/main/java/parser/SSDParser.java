package parser;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import model.SSDModel;
import org.xml.sax.SAXException;
import util.XMLUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/7.
 */
public class SSDParser {

    //具体对事实段内容的分析过程, ** 要写更多的划分方式, 主要需要写的地方
    protected ArrayList<String> parseString(String str){
        ArrayList<String> res =  new ArrayList<String>();
        //去掉空格
        str = str.trim();
        //标识当前句号是否可以分割
        int splitable = 0;
        String newString = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //去空格
            if (c == ' ') continue;
            //注意""中的。不要分割
            if ((c != '。') || splitable != 0) newString+=c;
            else {
                res.add(newString);
                newString = "";
            }
            if (c == '“') splitable --;
            if (c == '”') splitable ++;
        }
        return res;
    }

    //拆解事实段
    public ArrayList<SSDModel> parseSSD(ArrayList<String> ssds){
        ArrayList<SSDModel> ssdModels = new ArrayList<SSDModel>();
        for (String ssd: ssds) {
            SSDModel ssdModel = new SSDModel("事实段", ssd);
            //拆解过程
            ssdModel.setDetails(parseString(ssd));
            ssdModels.add(ssdModel);
        }
        return ssdModels;
    }

    public static void main(String[] args) {
        SSDParser parser = new SSDParser();
        ArrayList<String> ans = parser.parseString("经审理查明：2013年5月26日，原告张亮向被告崇川公安分局申请公开“2009年5月22日被打报警后，1．打人者姓名及其工作单位；2．处理结果；3．打人者为什么在派出所还能限制张亮夫妇人身自由；4．当日在派出所进行调查的监控录像”的政府信息。同年6月4日，被告崇川公安分局作出《答复》，答复内容为：“关于‘处理结果’，我局在之前的回复中已向你说明，鉴于目前的调查情况，本案中认定违法事实的证据不够充分，现未有处理结果。关于其他申请事项，我局认为不属于信息公开的范围。”原告张亮不服，于7月2日向南通市人民政府申请行政复议。8月29日，南通市人民政府作出通政复决［2013］157号《行政复议决定书》，维持被告崇川公安分局作出的被诉答复。原告张亮仍不服，向本院提起行政诉讼。 本案审理过程中，经本院依职权向南通市人民政府法制办公室、南通市中级人民法院调查，认定以下事实：据不完全统计，2012年至2015年期间，原告张亮及其妻子曹胡萍以生活需要为由，分别向南通市人民政府、南通市城乡建设局、南通市发展和改革委员会、南通市环境保护局、南通市住房保障和房产管理局、南通市规划局、南通市国土资源局、南通市公安局及其崇川分局、南通市崇川区人民政府、南通市崇川区城东街道办事处等行政机关共提起至少224次政府信息公开申请，要求公开以下大量信息：1．“南通市的住宅设计标准、南通市城市房屋拆迁补偿安置工作流程”等政府主动公开的信息；2．“通房党组（2012）19号文件、北街小学及周边地块改造的可行性研究报告”等内部文件；3．“崇川区是哪位领导负责‘十公开’工作、‘十公开’监督的流程、进展、结果，上岗人员名单、照片、上岗证号、廉洁自律承诺、‘补空情况’，北街小学及周边地块拆迁过程中是怎样去做的，市年终考核情况如何，传唤证的经办人是谁、是谁审查的、部门领导是谁，在派出所接受询问时值班领导姓名、工号”等内部管理性信息；4．“报督查后是如何处置的，监督的流程、进展、结果。督查员是谁，案件的执法依据、流程、进展、结果”等检举性信息；5．“拆迁公司在（2003）年和（2009）年度的从业人员名单、合同证明以及缴纳养老保险证明、何时撤销南通百昌房屋征收服务有限公司的证书”等涉及他人的信息；6．“土地拍卖属于哪一项建设，北街小学及周边地块是属哪一种土地类型的储备、什么时间作为垃圾场的，建围墙是谁批的、建围墙的资金是谁出的，拆迁许可证的发放要具备哪些条件，应该由谁审批签字，申请办理房屋征收服务公司要具备哪些条件，有哪些文件规定，如违反了规定会怎样处理”等咨询性信息。此外，原告张亮及其妻子曹胡萍还针对政府信息公开答复书提出申请公开该答复书的“经办人是谁、是谁审查的、部门领导是谁”等信息，并循环申请公开答复书的“附件的事实和法律依据”等信息。 原告张亮及其妻子曹胡萍在收到行政机关作出的《政府信息公开申请答复》后，分别向江苏省人民政府、江苏省住房和城乡建设厅、江苏省国土资源厅、南通市人民政府、南通市崇川区人民政府等复议机关共提起至少37次行政复议。在经过行政复议程序之后，二人分别以政府信息公开申请答复“没有发文机关标志、标题不完整、发文字号形式错误，违反《党政机关公文处理工作条例》的规定，属形式违法；答复内容不符合申请的要求，未注明救济途径，属程序违法”等为由向南通市中级人民法院和本院提起政府信息公开之诉至少21次。 ");
        System.out.println(ans.size());
        for (String s: ans) {
            System.out.println("*" + s);
        }
    }
}
