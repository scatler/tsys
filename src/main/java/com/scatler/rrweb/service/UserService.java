package com.scatler.rrweb.service;

import com.scatler.rrweb.entity.User;

import java.util.List;

/**
 * Created by alexkpc on 27.07.2019.
 */
public interface UserService {

    List<User> getUsers();
    void saveUser (User user);
    User getUser (int id);
    void deleteUser (int id);

}
