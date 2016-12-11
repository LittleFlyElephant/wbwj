package model;

import java.util.ArrayList;

/**
 * Created by raychen on 2016/12/7.
 */
public class SSDModel {
    private String name;
    private String value;
    private ArrayList<String> details;

    public SSDModel(String name, String value) {
        this.name = name;
        this.value = value;
        details = new ArrayList<String>();
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

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }
}
