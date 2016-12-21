package util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.ExcelModel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by st0001 on 2016/12/21.
 */
public class ExcelUtil {

    public static String filePath = "src/main/resources/excel/";

    public static void buildExcel(OutputStream os, ArrayList<ExcelModel> models) throws IOException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet = workbook.createSheet("事实段（人工）", 0);
        //在sheet上进行操作
        //开头
        Label wsbh = new Label(0,0,"文书编号");
        sheet.addCell(wsbh);
        Label ssdwz = new Label(1,0,"事实段位置");
        sheet.addCell(ssdwz);
        Label ssd = new Label(2,0,"事实段");
        sheet.addCell(ssd);
        //内容
        int count = 0;
        for (ExcelModel model: models) {
            count ++;
            Label bh = new Label(0, count, model.getXmlName());
            Label wz = new Label(1, count, model.getLocation());
            Label str = new Label(2, count, model.getSsd());
            sheet.addCell(bh);
            sheet.addCell(wz);
            sheet.addCell(str);
        }
        //写入文件
        workbook.write();
        workbook.close();
        os.close();
    }

    public static void main(String[] args) throws IOException, WriteException, XPathExpressionException, SAXException, ParserConfigurationException {
        TestUtil.testExcel("民事一审");
    }

}
