package com.scatler.rrweb.service;

import com.scatler.rrweb.dao.Impls.TrainDAO;
import com.scatler.rrweb.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainService {

    @Autowired
    TrainDAO trainDAO;

    @Transactional
    public void save(Train entity) {
        trainDAO.save(entity);
    }
}
