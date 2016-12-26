package model;

/**
 * Created by raychen on 2016/12/26.
 */
public class SSModel {
    private String value;
    private String where;
    private String when;
    private String what;
    private String who;
    private String how_much;

    public SSModel(String value) {
        this.value = value;
        this.what = "暂无";
        this.when = "暂无";
        this.where = "暂无";
        this.who = "暂无";
        this.how_much = "暂无";
    }

    public String getValue() {
        return value;
    }

    public String getWhere() {
        return where;
    }

    public String getWhen() {
        return when;
    }

    public String getWhat() {
        return what;
    }

    public String getWho() {
        return who;
    }

    public String getHow_much() {
        return how_much;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setHow_much(String how_much) {
        this.how_much = how_much;
    }
}
