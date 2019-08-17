package com.scatler.rrweb.dto.forms;

import java.util.List;


public class StationTimeTableForm extends AbstractCrudForm<StationTimeTable> {

    private String editLink = "";
    private String[] columNames = new String[]{"Train#", "Route#","Route name",  "Arrival Time to this station",  };

    public StationTimeTableForm() {
        super();
    }

    public StationTimeTableForm(List<StationTimeTable> list) {
        super(list);
    }



    @Override
    public boolean showButtons() {
        return false;
    }

    @Override
    protected String getId(StationTimeTable record) {
        return String.valueOf(record.getTrainId());
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
    protected String getDataAtColumn(StationTimeTable record, int column) {
        switch (column) {
            case 0:
                return String.valueOf(record.getTrainId());
            case 1:
                return String.valueOf(record.getRouteId());
            case  2:
                return record.getRouteName();
            case  3:
                return String.valueOf(record.getArrivalTime());
        }
        return null;
    }

    public String getGetCreateLink() {
        //todo replace
        return "";
    }

    public Object getGetColumns() {
        return null;
    }

    public String getEditLink(int row) {
        return "";
    }

    public String getUserFriendlyTypeName() {
        return null;
    }

    public String getJavaFieldNameAt(int column) {
        return "buy";
    }



}
