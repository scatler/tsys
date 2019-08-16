package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.TrainDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.ViewAllTrain;
import com.scatler.rrweb.entity.Train;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.converter.TrainConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainDAO dao;

    @Autowired
    TrainConverter converter;

    @Transactional
    public void save(TrainDTO trainDTO) {
        dao.save(converter.toEntity(trainDTO));
    }

    @Transactional
    public List<ViewAllTrain> viewAllTrains() {
        return dao.viewAllTrains();
    }
}
