package locator;

import java.util.ArrayList;

public class AdministrativeFirstLocator{

	private static AdministrativeFirstLocator locator = new AdministrativeFirstLocator();
	public static AdministrativeFirstLocator getInstance(){
		return locator;
	}
	
	private ArrayList<Pattern> patternList = new ArrayList<Pattern>();
	
	{
		patternList.add(new Pattern("查明事实段","//CMSSD/@value",""));
		patternList.add(new Pattern("案件基本情况","//AJJBQK/@value",""));
	}
	
	public String getSSD(String filePath){
		MatchResult result = SSDLocator.match(patternList, filePath);
		
		String append = "";
		if(result.getPatternName()!=null && result.getPatternName().equals("案件基本情况") && result.getMatchContent().contains("事实")){
			append += "         true";
			String factor = SSDLocator.extractFactor(result.getMatchContent());
			if(factor!=null && !factor.isEmpty()){
				result.setMatchContent(factor);
				append += "         true";
			}
		}
		
//		System.out.println(filePath + "    " + result.getPatternName() + append);
		
		//SSDLocator.record(result.getMatchContent(), filePath.replace("/in/", "/out/").replaceAll(".xml", ".txt"));
		
		return result.getMatchContent();
	}
	
}
