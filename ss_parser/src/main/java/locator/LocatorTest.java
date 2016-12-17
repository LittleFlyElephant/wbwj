package locator;

import java.io.File;

import util.XMLUtil;

public class LocatorTest {
	
	public static void main(String args[]){
		LocatorTest test = new LocatorTest();
//		test.testLocator("民事一审");
//		test.testLocator("民事二审");
//		test.testLocator("行政一审");
		test.testLocator("行政二审");
		
	}
	
	public void testLocator(String filesPath){
		
        File file=new File(XMLUtil.readPath+filesPath);
        String filenames[] = file.list();
        SSDLocator locator = SSDLocator.getInstance();

        
        //读取子文件
        for (String filename: filenames) {
            if (filename.charAt(0) == '.') continue;
			String result = locator.getSSD(XMLUtil.readPath+filesPath+"/"+filename,filesPath);
            //System.out.println(result);
        }
		
	}

}
