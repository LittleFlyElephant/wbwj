import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import util.TestUtil;

/**
 * Created by raychen on 2016/12/11.
 */
public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        TestUtil.testParser("民事一审");
//        TestUtil.testParser("民事二审");
//        TestUtil.testParser("行政一审");
//        TestUtil.testParser("行政二审");
    }
}
