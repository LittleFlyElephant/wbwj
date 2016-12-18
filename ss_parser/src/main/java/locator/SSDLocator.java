package locator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.SSDModel;
import util.XMLUtil;

public class SSDLocator {
	
	private static SSDLocator locator = new SSDLocator();
	
	public static SSDLocator getInstance(){
		return locator;
	}
	
	public SSDModel getSSD(String filePath,String classfication){
		if(classfication.equals("民事一审")){
			return new SSDModel("事实段",CivilFirstLocator.getInstance().getSSD(filePath));
		}
		else if(classfication.equals("民事二审")){
			return new SSDModel("事实段",CivilSecondLocator.getInstance().getSSD(filePath));
		}
		else if(classfication.equals("行政一审")){
			return new SSDModel("事实段",AdministrativeFirstLocator.getInstance().getSSD(filePath));
		}
		else if(classfication.equals("行政二审")){
			return new SSDModel("事实段",AdministrativeSecondLocator.getInstance().getSSD(filePath));
		}
		else{
			System.out.println("error in SSDLocatolr.getSSD() ： unexpected classfication");
			return null;
		}
		
	}
	
	/**
	 * @param patternList
	 * @param filePath
	 * @return result will never be null
	 */
	public static MatchResult match(ArrayList<Pattern> patternList,String filePath){
		
		MatchResult result = new MatchResult();
		for(Pattern pattern:patternList){
			try {
				ArrayList<String> nodeList = XMLUtil.getNodes(filePath, pattern.getLocation());
				if(nodeList == null || nodeList.isEmpty()){
					continue;
				}
				String content = nodeList.get(0);
				if(!content.contains(pattern.getContainTxt())){
					continue;
				}
				if(pattern.getReferenceLocation() != null && !pattern.getReferenceLocation().isEmpty()){
					nodeList = XMLUtil.getNodes(filePath, pattern.getReferenceLocation());
					if(nodeList == null || nodeList.isEmpty()){
						continue;
					}
					String referenceContent = XMLUtil.getNodes(filePath, pattern.getReferenceLocation()).get(0);
					if(!referenceContent.contains(pattern.getReferenceTxt())){
						continue;
					}
				}
				result.setPatternName(pattern.getName());
				result.setMatchContent(content);
				break;
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public static void record(String content,String filePath){
		if(content == null){
			return ;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "GBK"));  
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//有些案件基本情况实际上包含查明事实但是xml中没有解析出来
	//此方法读取案件基本情况输出查明事实段
	//return null if factor not found
	public static String extractFactor(String content){
		
		String[] keywordList = {"认定以下事实",
				"确认如下事实",
				"认定如下事实",
				"确认如下案件事实",
				"查明以下案件事实",
				"查明以下事实",
				"确定以下事实",
				"对以下证据作如下确认",
				"认定本案的事实如下",
				"经审理查明",
				"经审查",
				"本院予以确认",
				"事实和证据均予以确认"};
		
		String[] contents = content.split("。");
		StringBuilder builder = new StringBuilder();
		boolean isFactor = false;
		for(String token:contents){
			boolean isContains = false;
			if(!isFactor){
				for(String keyword:keywordList){
					if(token.contains(keyword)){
						isContains = true;
						break;
					}
				}
			}
			
			if(isContains){
				isFactor = true;
			}
			
			if(isFactor){
				builder.append(token);
			}
		}
		return builder.toString();
	}
	
}
