package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.LineDTO;
import com.scatler.rrweb.entity.Line;
import org.springframework.stereotype.Component;

@Component
public class LineConverter implements IConverter<Line, LineDTO> {
    @Override
    public LineDTO toDto(Line entity) {
        return new LineDTO(entity.getId(),entity.getName());
    }

    @Override
    public Line toEntity(LineDTO dto) {
        Line line = new Line();
        line.setId(dto.getId());
        line.setName(dto.getName());
        return line;
    }
}
