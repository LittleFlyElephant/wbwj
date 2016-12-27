package util;

import filter.BaseFilter;
import jxl.write.WriteException;
import locator.Locator;
import model.ExcelRowModel;
import model.ExcelSheetModel;
import model.SSDModel;
import model.SSModel;
import org.xml.sax.SAXException;
import parser.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/11.
 */
public class TestUtil {

    public static void getKeyInSS(ArrayList<SSDModel> ssdModels){
        //增加提取关键词的类
        SSParser parserWhere = new SSWhereParser();
        SSParser parserWhat = new SSWhatParser();
        for (SSDModel ssd: ssdModels) {
            for (SSModel ss: ssd.getSsModels()) {
                //设置关键词
                parserWhere.setKeyInSS(ss);
//                parserWhat.setKeyInSS(ss);
            }
        }
    }

    //测试分析器
    public static void testParser(String filesPath) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        FileUtil.delAllFile(XMLUtil.outPath+filesPath);
        File file=new File(XMLUtil.readPath+filesPath);
        String filenames[];
        filenames = file.list();
        Locator locator = new Locator();
        SSDParser parser = new SSDParser();
        BaseFilter filter = new BaseFilter();
        int count = 0;
        //读取子文件
        for (String filename: filenames) {
            if (filename.charAt(0) == '.') continue;
            //获得事实段
            ArrayList<SSDModel> res = locator.getSSDs(filesPath, XMLUtil.readPath+filesPath+"/"+filename);
            if (res.size() == 0) continue;
            //分解事实段
            ArrayList<SSDModel> parseRes = parser.parseSSD(res);
            //过滤事实
            ArrayList<SSDModel> filteredRes = filter.filter(parseRes);
            //去除null
            if (filteredRes.size() == 0) continue;
            //设置事实关键词
            getKeyInSS(filteredRes);
            count ++;
            //生成xml
            XMLUtil.buildNewSSD(filteredRes, filesPath+"/"+filename);
        }
        System.out.println("parse: "+count+"/"+filenames.length);
    }

    //测试导出excel
    public static void testExcel() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, WriteException {
        File newFile = new File(ExcelUtil.filePath+"事实段表格.xls");
        if (!newFile.exists()) newFile.createNewFile();
        OutputStream os = new FileOutputStream(newFile);
        //读取xml
        File file_out=new File(XMLUtil.readPath);
        String fileout_names[] = file_out.list();
        ArrayList<ExcelSheetModel> sheetModels = new ArrayList<ExcelSheetModel>();
        int count = 0;
        for (String fileout_name: fileout_names) {
            if (fileout_name.charAt(0) == '.') continue;
            ArrayList<ExcelRowModel> models = new ArrayList<ExcelRowModel>();
            //内层
            File file = new File(XMLUtil.readPath+fileout_name);
            String filenames[] = file.list();
            for (String filename: filenames) {
                if (filename.charAt(0) == '.') continue;
                String x = fileout_name.contains("一审")?"CMSSD":"QSSLD";
                ArrayList<String> results = XMLUtil.getNodes(XMLUtil.readPath+fileout_name+"/"+filename, "//"+x+"/@value");
                //存在事实段，插入表格
                if (results.size() > 0){
                    for (String str: results) {
                        ExcelRowModel model = new ExcelRowModel(filename, str, x.equals("CMSSD")?"查明事实段":"前审审理段", fileout_name);
                        models.add(model);
                    }
                }
            }
            sheetModels.add(new ExcelSheetModel(models, count++, fileout_name));
        }
        ExcelUtil.buildExcel(os, sheetModels);
    }
}
