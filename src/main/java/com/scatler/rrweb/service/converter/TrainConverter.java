package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.entity.Train;
import org.springframework.stereotype.Component;

@Component
public class TrainConverter implements IConverter<Train, TrainDTO> {

    @Override
    public TrainDTO toDto(Train entity) {
        return new TrainDTO(entity.getId(),entity.getName(),entity.getSeats());
    }

    @Override
    public Train toEntity(TrainDTO dto) {
        Train train = new Train();
        train.setName(dto.getName());
        train.setSeats(dto.getSeats());
        return train;
    }
}
