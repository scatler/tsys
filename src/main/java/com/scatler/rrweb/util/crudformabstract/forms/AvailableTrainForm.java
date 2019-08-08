package com.scatler.rrweb.util.crudformabstract.forms;

import com.scatler.rrweb.entity.objects.searchresult.AvailableTrain;
import com.scatler.rrweb.util.crudformabstract.AbstractCrudForm;

import java.util.List;

public class AvailableTrainForm extends AbstractCrudForm<AvailableTrain> {

    private String editLink = "edit-link";
    private String createLink = "create-link";
    private String deleteLink = "delete-link";

    public AvailableTrainForm(List<AvailableTrain> data) {
        super(data);
    }

    public String getDeleteLink() {
        return deleteLink;
    }

    public void setDeleteLink(String deleteLink) {
        this.deleteLink = deleteLink;
    }

    @Override
    protected String getId(AvailableTrain record) {
        return null;
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

    public void setEditLink(String editLink) {
        this.editLink = editLink;
    }

    public String getCreateLink() {
        return createLink;
    }

    public void setCreateLink(String createLink) {
        this.createLink = createLink;
    }

    public String getUserFriendlyTypeName() {
        return null;
    }

    public String getGetJavaFieldNameAt(Object o) {

        return "testJavaFieldNameAt";
    }


}
