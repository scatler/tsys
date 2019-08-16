package com.scatler.rrweb.dto.forms;

import java.util.List;


public class AvailableTrainForm extends AbstractCrudForm<AvailableTrain> {

    private String editLink = "buy";
    private String[] columNames = new String[]{"Train#", "Route#", "Station From", "Arrival Time", "Day", "Station To", "Arrival Time", "Day", "Free tickets"};

    @Override
    public boolean showButtons() {
        return true;
    }

    public AvailableTrainForm() {
        super();
    }

    public AvailableTrainForm(List<AvailableTrain> data) {
        super(data);
    }

    @Override
    protected String getId(AvailableTrain record) {
        return String.valueOf(record.getTrainRouteDay());
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
    protected String getDataAtColumn(AvailableTrain record, int column) {
        switch (column) {
            case 0:
                return (String) record.getTrain();
            case 1:
                return String.valueOf(record.getRoute());
            case  2:
                return (String) record.getStation1Name();
            case  3:
                return String.valueOf(record.getArrivalTimeToStation1());
            case 4:
                return String.valueOf(record.getArrivalDayToStation1());
            case 5:
                return (String) record.getStation2name();
            case 6:
                return String.valueOf(record.getArrivalTimeToStation2());
            case 7:
                return String.valueOf(record.getArrivalDayToStation2());
            case 8:
                return String.valueOf(record.getFree_tickets());

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
        return String.valueOf(data.get(row).getStation1Id());
    }

    public String getStationTo(int row) {
        return String.valueOf(data.get(row).getStation2id());
    }
}
