package locator;

import java.util.ArrayList;

public class AdministrativeSecondLocator{

	private static AdministrativeSecondLocator locator = new AdministrativeSecondLocator();
	public static AdministrativeSecondLocator getInstance(){
		return locator;
	}
	
	private ArrayList<Pattern> patternList = new ArrayList<Pattern>();
	
	{
		patternList.add(new Pattern("本审审理段","//BSSLD/@value",""));
		
		//经阅读此部分并不是所需要提取的事实
		patternList.add(new Pattern("本审段落","//BSDL/@value",""));
	}
	
	public String getSSD(String filePath){
		MatchResult result = SSDLocator.match(patternList, filePath);

		String append = "";
		if(result.getPatternName()!=null && result.getPatternName().equals("本审段落") && result.getMatchContent().contains("事实")){
			append += "         true";
			String factor = SSDLocator.extractFactor(result.getMatchContent());
			if(factor!=null && !factor.isEmpty()){
				result.setMatchContent(factor);
				append += "         true";
			}
		}
		
//		System.out.println(filePath + "    " + result.getPatternName() + append);
		
		//if(result.getPatternName()!=null && result.getPatternName().equals("本审段落"))
		//SSDLocator.record(result.getMatchContent(), filePath.replace("/in/", "/out/").replaceAll(".xml", ".txt"));
		
		
		return result.getMatchContent();
	}
	
}
