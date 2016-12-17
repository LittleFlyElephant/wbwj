package locator;

public class MatchResult {

	private String patternName;
	private String matchContent;

	public MatchResult() {
		super();
	}

	public MatchResult(String patternName, String matchContent) {
		super();
		this.patternName = patternName;
		this.matchContent = matchContent;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public String getMatchContent() {
		return matchContent;
	}

	public void setMatchContent(String matchContent) {
		this.matchContent = matchContent;
	}

}
