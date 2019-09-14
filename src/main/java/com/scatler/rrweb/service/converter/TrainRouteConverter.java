package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.TrainRouteDTO;
import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.RouteStation;
import com.scatler.rrweb.entity.Train;
import com.scatler.rrweb.entity.TrainRouteidDay;
import org.springframework.stereotype.Component;

@Component
public class TrainRouteConverter implements IConverter<TrainRouteidDay, TrainRouteDTO> {
    @Override
    public TrainRouteDTO toDto(TrainRouteidDay entity) {
        TrainRouteDTO dto = new TrainRouteDTO();
        dto.setId(entity.getId());
        dto.setTrainId(entity.getTrainId().getId());
        dto.setRouteId(entity.getRouteId().getId());
        dto.setDay(entity.getDayDept());
        return dto;
    }

    @Override
    public TrainRouteidDay toEntity(TrainRouteDTO dto) {
        TrainRouteidDay entity = new TrainRouteidDay();
        entity.setRouteId(new Route(dto.getRouteId()));
        entity.setDayDept(dto.getDay());
        entity.setTrainId(new Train(dto.getTrainId()));
        entity.setId(dto.getId());
        return entity;
    }
}
