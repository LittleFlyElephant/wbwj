package model;

/**
 * Created by st0001 on 2016/12/21.
 */
public class ExcelModel {
    private String xmlName;
    private String ssd;
    private String location;

    public ExcelModel(String xmlName, String ssd, String location) {
        this.xmlName = xmlName;
        this.ssd = ssd;
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {

        return location;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public void setXmlName(String xmlName) {

        this.xmlName = xmlName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public String getSsd() {
        return ssd;
    }
}
