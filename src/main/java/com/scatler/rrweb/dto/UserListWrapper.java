package com.scatler.rrweb.dto;

import com.scatler.rrweb.entity.User;

import java.util.ArrayList;

public class UserListWrapper {

    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}