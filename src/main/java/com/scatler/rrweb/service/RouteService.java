package com.scatler.rrweb.service;

import com.scatler.rrweb.dao.RouteDAO;
import com.scatler.rrweb.entity.TrainRouteidDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RouteService {

    @Autowired
    RouteDAO routeDAO;

    @Transactional
    public TrainRouteidDay getTrainRouteidDay(int id) {

        return routeDAO.getTrainRouteidDay(id);
    }

}
