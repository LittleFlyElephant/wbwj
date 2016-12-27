package locator;

import java.util.ArrayList;

import model.SSDModel;

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
	
	public SSDModel getSSD(String filePath){
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
		
		//如果本审与原审事实一致则加上原审的
		patternList.clear();
		patternList.add(new Pattern("本院认定事实证据是否与原审一致","//BYRDSSZJSFYYSYZ/@value","是"));
		MatchResult result2 = SSDLocator.match(patternList, filePath);
		if(result2.getPatternName()!=null && result2.getMatchContent().contains("是")){
			patternList.clear();
			patternList.add(new Pattern("前审审理段","//QSSLD/@value",""));
			patternList.add(new Pattern("前审段落","//QSDL/@value",""));
			result = SSDLocator.match(patternList, filePath);
			//System.out.println(filePath);
		}
		
		//SSDLocator.record(result.getMatchContent(), filePath.replace("/in/", "/out/").replaceAll(".xml", ".txt"));
		
		
		
		return new SSDModel(result.getPatternName(),result.getMatchContent());
	}
	
}
