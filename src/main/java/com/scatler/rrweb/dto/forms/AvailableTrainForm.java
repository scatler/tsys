package com.scatler.rrweb.dto.forms;

import java.util.List;


public class AvailableTrainForm extends AbstractCrudForm<AvailableTrain> {

    private String editLink = "buy";

    public AvailableTrainForm(List<AvailableTrain> data) {
        super(data);
    }


    @Override
    protected String getId(AvailableTrain record) {
        return String.valueOf(record.getTrainRouteDay());
    }

    @Override
    public int getNumberOfFields() {
        //TODO enhance
        return 3;
    }

    @Override
    public String[] getArrayOfColumnNames() {
/*        return new String[] {"Train#","Route#","Station From","Arrival Time","Day","Station To", "Arrival Time", "Day", "Free tickets"};*/
        return new String[] {"Train#","Route#","Station From"};
    }

    @Override
    protected String getDataAtColumn(AvailableTrain record, int column) {
        switch (column) {
            case 0:
                return record.getTrain().getName();
            case 1:
                return record.getRoute().getName();
            case  2:
                return ;
            case  3:
                return "ToDo add station to query";

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
        return editLink+"?id="+getId(row)+"&stationFrom="+getStationFrom(row)+"&stationTo=" + getStationTo(row);
    }

    public String getUserFriendlyTypeName() {
        return null;
    }

    public String getJavaFieldNameAt(int column) {
        return "buy";
    }

    public String getStationFrom(int row) {
        return String.valueOf(data.get(row).getStation1().getId());
    }

    public String getStationTo(int row) {
        return String.valueOf(data.get(row).getStation2().getId());
    }
}
