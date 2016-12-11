import org.xml.sax.SAXException;
import parser.SSDParser;
import util.TestUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Created by raychen on 2016/12/11.
 */
public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        TestUtil.testParser("民事一审", "//CMSSD/@value");
        TestUtil.testParser("民事二审", "//QSSLD/@value");
        TestUtil.testParser("行政一审", "//CMSSD/@value");
        TestUtil.testParser("行政二审", "//QSSLD/@value");
    }
}
