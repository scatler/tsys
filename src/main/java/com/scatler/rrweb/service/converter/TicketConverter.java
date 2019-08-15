package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.TrainRouteidDay;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter implements IConverter <Ticket, TicketDTO> {

    @Override
    public TicketDTO toDto(Ticket entity) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(entity.getId());
        ticketDTO.setName(entity.getName());
        ticketDTO.setSurname(entity.getSurname());
        ticketDTO.setBirthday(entity.getBirthday());
        ticketDTO.setStation1Id(entity.getStation1Id().getId());
        ticketDTO.setStation2Id(entity.getStation2Id().getId());
        ticketDTO.setTrd(entity.getTrd().getId());

        return ticketDTO;
    }

    @Override
    public Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setName(dto.getName());
        ticket.setSurname(dto.getSurname());
        ticket.setBirthday(dto.getBirthday());
        ticket.setStation1Id(new Station(dto.getStation1Id()));
        ticket.setStation2Id(new Station(dto.getStation2Id()));
        ticket.setTrd(new TrainRouteidDay(dto.getTrd()));
        return ticket;
    }
}
