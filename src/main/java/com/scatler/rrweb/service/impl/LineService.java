package com.scatler.rrweb.service.impl;

import com.scatler.rrweb.dao.impl.LineDAO;
import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.service.converter.LineConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineService {
    @Autowired
    private LineDAO dao;
    @Autowired
    private LineConverter converter;
    @Transactional
    public List<LineDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());
    }
}
