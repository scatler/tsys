package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.entity.Train;
import com.scatler.rrweb.service.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService extends AbstractService<Train, TrainDTO> {

    @Autowired
    public TrainService(IDao<Train, Integer> dao, IConverter<Train, TrainDTO> converter) {
        super(dao, converter);
    }

    @Override
    IDao<Train, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<Train, TrainDTO> getConverter() {
        return converter;
    }


}
