package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raychen on 2016/12/22.
 */
public class ExcelSheetModel {
    private ArrayList<ExcelRowModel> rows;
    private int number;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public ExcelSheetModel(ArrayList<ExcelRowModel> rows, int number, String name) {

        this.rows = rows;
        this.number = number;
        this.name = name;
    }

    public void setRows(ArrayList<ExcelRowModel> rows) {
        this.rows = rows;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<ExcelRowModel> getRows() {

        return rows;
    }

    public int getNumber() {
        return number;
    }
}
