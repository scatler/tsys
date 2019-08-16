package com.scatler.rrweb.dto.forms;

import com.scatler.rrweb.dto.ViewAllTrain;

import java.util.List;

public class ViewAllTrainsForm extends AbstractCrudForm<ViewAllTrain> {

    private String editLink = "";
    private String[] columNames = new String[]{"Train#", "Route#", "Route Name", "Total seats", "Day departure"};

    @Override
    public boolean showButtons() {
        return true;
    }

    public ViewAllTrainsForm() {
        super();
    }

    public ViewAllTrainsForm(List<ViewAllTrain> data) {
        super(data);
    }

    @Override
    protected String getId(ViewAllTrain record) {
        return String.valueOf(record.getId());
    }

    @Override
    public int getNumberOfFields() {

        return columNames.length;
    }

    @Override
    public String[] getArrayOfColumnNames() {

        return columNames;
    }

    @Override
    protected String getDataAtColumn(ViewAllTrain record, int column) {
        switch (column) {
            case 0:
                return String.valueOf(record.getId());
            case 1:
                return String.valueOf(record.getName());
            case  2:
                return String.valueOf(record.getRouteId());
            case  3:
                return String.valueOf(record.getRouteName());
            case 4:
                return String.valueOf(record.getTotalSeats());
            case 5:
                return String.valueOf(record.getDayDept());
        }
        return null;
    }

    public String getGetCreateLink() {
        return "";
    }

    public Object getGetColumns() {
        return null;
    }

    public String getEditLink(int row) {
        return editLink;
    }

    public String getUserFriendlyTypeName() {
        return null;
    }

    public String getJavaFieldNameAt(int column) {
        return "buy";
    }

}
