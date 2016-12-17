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
        String[] sp = str.split("。");
        for (String s: sp) {
            if (!s.equals(" ")) res.add(s+"。");
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

}
