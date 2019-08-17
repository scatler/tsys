package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.entity.Station;
import org.springframework.stereotype.Component;

@Component
public class StationConverter implements IConverter<Station, StationDTO > {
    @Override
    public StationDTO toDto(Station entity) {
        return new StationDTO(
                entity.getId(),
                entity.getLineId().getId(),
                entity.getName(),
                entity.getTimezone());
    }

    @Override
    public Station toEntity(StationDTO dto) {
        Station station = new Station();
        station.setId(dto.getId());
        station.setName(dto.getName());
        station.setLineId(new Line(dto.getLineId()));
        station.setTimezone(dto.getTimezone());
        return station;
    }
}
