package model;

import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/7.
 */
public class SSDModel {
    private String name;
    private String value;
    private ArrayList<String> details;
    private ArrayList<SSModel> ssModels;

    public SSDModel(String name, String value) {
        this.name = name;
        this.value = value;
        details = new ArrayList<String>();
        ssModels = new ArrayList<SSModel>();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<SSModel> getSsModels() {
        return ssModels;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
        for (String s: details) {
            SSModel model = new SSModel(s);
            ssModels.add(model);
        }

    }
}
