package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.LineDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.converter.LineConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineService {

    @Autowired
    LineDAO dao;

    @Autowired
    LineConverter converter;

    IDao<Line, Integer> getDao() {
        return dao;
    }

    IConverter<Line, LineDTO> getConverter() {
        return converter;
    }

    public List<LineDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }
}
