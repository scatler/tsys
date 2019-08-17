package com.scatler.rrweb.dto.forms;

import com.scatler.rrweb.dto.AllPassengersDTO;

import java.util.List;

public class ViewAllPassengersForm extends AbstractCrudForm<AllPassengersDTO> {

    private String editLink = "";
    private String[] columNames = new String[]{"Name", "Surname", "Birthday", "Station From", "Station To"};

    public ViewAllPassengersForm() {
        super();
    }

    public ViewAllPassengersForm(List<AllPassengersDTO> list) {
        super(list);
    }


    @Override
    public boolean showButtons() {
        return false;
    }

    @Override
    protected String getId(AllPassengersDTO record) {
        return String.valueOf(0);
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
    protected String getDataAtColumn(AllPassengersDTO record, int column) {
        switch (column) {
            case 0:
                return record.getName();
            case 1:
                return record.getSurname();
            case 2:
                return String.valueOf(record.getBirthday());
            case 3:
                return record.getStationFrom();
            case 4:
                return record.getStationTo();
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

