package com.scatler.rrweb.util.crudformabstract.forms;

import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.util.crudformabstract.AbstractCrudForm;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
                return "ToDo add station to query";
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

    public String getEditLink() {
        return editLink;
    }



    public String getUserFriendlyTypeName() {
        return null;
    }


    public String getJavaFieldNameAt(int column) {
        return "buy";
    }

    public String getStationFrom(int row) {
        return getDataAt(row,4);
    }

    public String getStationTo(int row) {
        return getDataAt(row,9);
    }
}
