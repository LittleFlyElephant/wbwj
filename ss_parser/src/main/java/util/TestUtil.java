package util;

import locator.Locator;
import org.xml.sax.SAXException;
import parser.SSDParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/11.
 */
public class TestUtil {

    //测试分析器
    public static void testParser(String filesPath) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        File file=new File(XMLUtil.readPath+filesPath);
        String filenames[];
        filenames = file.list();
        Locator locator = new Locator();
        SSDParser parser = new SSDParser();
        int count = 0;
        //读取子文件
        for (String filename: filenames) {
            if (filename.charAt(0) == '.') continue;
            ArrayList<String> res = locator.getSSDs(filesPath, XMLUtil.readPath+filesPath+"/"+filename);
            if (res.size() == 0) continue;
            count ++;
            XMLUtil.buildNewSSD(parser.parseSSD(res), filesPath+"/"+filename);
        }
        System.out.println("parse: "+count+"/"+filenames.length);
    }

}
