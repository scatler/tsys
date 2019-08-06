package com.scatler.rrweb.entity.objects;

import org.hibernate.validator.constraints.NotEmpty;

public class TestAjaxObject {

    @NotEmpty(message="Enter first name.")
    private String firstName;
    @NotEmpty(message="Enter last name.")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
