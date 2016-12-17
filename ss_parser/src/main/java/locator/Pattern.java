package locator;

public class Pattern {

	private String name;
	private String location;
	private String containTxt;
	private String referenceLocation;
	private String referenceTxt;

	public Pattern() {
		super();
	}

	public Pattern(String name, String location, String containTxt) {
		super();
		this.name = name;
		this.location = location;
		this.containTxt = containTxt;
	}

	public Pattern(String name, String location, String containTxt, String referenceLocation, String referenceTxt) {
		super();
		this.name = name;
		this.location = location;
		this.containTxt = containTxt;
		this.referenceLocation = referenceLocation;
		this.referenceTxt = referenceTxt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContainTxt() {
		return containTxt;
	}

	public void setContainTxt(String containTxt) {
		this.containTxt = containTxt;
	}

	public String getReferenceLocation() {
		return referenceLocation;
	}

	public void setReferenceLocation(String referenceLocation) {
		this.referenceLocation = referenceLocation;
	}

	public String getReferenceTxt() {
		return referenceTxt;
	}

	public void setReferenceTxt(String referenceTxt) {
		this.referenceTxt = referenceTxt;
	}

}
