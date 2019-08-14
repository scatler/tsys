package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.service.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("lineService")
public class LineService extends  AbstractService <Line, LineDTO>{

    @Autowired
    public LineService(IDao<Line, Integer> dao, IConverter<Line, LineDTO> converter) {
        super(dao, converter);
    }

    @Override
    IDao<Line, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<Line, LineDTO> getConverter() {
        return converter;
    }
}
