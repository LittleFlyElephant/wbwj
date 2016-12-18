package util;

import model.SSDModel;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/7.
 */
public class XMLUtil {

    public static String readPath = "src/main/resources/in/";
    public static String outPath = "src/main/resources/out/";

    //获取xml中节点
    public static ArrayList<String> getNodes(String path, String x) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        ArrayList<String> result = new ArrayList<String>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        File f = new File(path);
        //编译xml
        Document doc = builder.parse(f);
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        //x是xml中的节点，例如“YGSCD”, 格式://YGSCD/@value
        XPathExpression expr = xpath.compile(x);
        //doc中读取expr节点内容
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() > 0) {
            for (int i = 0; i < nodes.getLength(); i++) {
                result.add(nodes.item(i).getNodeValue());// 满足正则的XML中的那段文字复制到result中
            }
        }
        return result;
    }

    //生成xml文件, 这里主要是生成划分好的事实段
    public static void buildNewSSD(ArrayList<SSDModel> ssdModels, String fileName){
        Element root = new Element("SYSSD").setAttribute("nameCN","所有事实段");
        root.setAttribute("value", "事实段出现的各个节点");
        // 将根节点添加到文档中；
        org.jdom.Document doc = new org.jdom.Document(root);

        //将拆解好的事实插入文档
        for (SSDModel ssdModel: ssdModels) {
            Element e = new Element("SSD").setAttribute("nameCN", ssdModel.getName());
            e.setAttribute("value", ssdModel.getValue());
            root.addContent(e);
            for (String s: ssdModel.getDetails()) {
                Element e2 = new Element("SS").setAttribute("nameCN", "事实");
                e2.setAttribute("value", s);
                e.addContent(e2);
            }
        }

        // 使xml文件 缩进效果
        Format format = Format.getPrettyFormat();
        XMLOutputter XMLOut = new XMLOutputter(format);
        try {
            XMLOut.output(doc, new FileOutputStream(outPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
//        String file = XMLUtil.readPath+"4.xml";
        File file=new File(XMLUtil.readPath);
        String filenames[];
        filenames = file.list();
        for (String filename: filenames) {
            ArrayList<String> res = XMLUtil.getNodes(XMLUtil.readPath+filename, "//YSF/@value");
            for (String s: res) {
                System.out.println(s);
            }
        }
//        buildNewSSD("测试一下", null, "test");
    }

}
