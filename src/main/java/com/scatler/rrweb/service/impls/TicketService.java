package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.TicketDAO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.AllPassengersDTO;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.service.converter.TicketConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private TicketConverter ticketConverter;

    @Transactional
    public List<AvailableTrain> getAvailableTrains(int station_id1, int station_id2, Date day) {
        return ticketDAO.getAvailableTrains(station_id1, station_id2, day);
    }

    @Transactional
    public void registerTicket(TicketDTO ticketDTO) throws FoundSamePassengerException, NotEnoughTimeBeforeDeparture {
        if (checkEnoughTimeBeforeDeparture(ticketDTO)){
            throw new NotEnoughTimeBeforeDeparture("Not enough time before train departure");
        }
        if (findSamePassenger(ticketDTO)) {
            throw new FoundSamePassengerException("Same passenger found");
        }
        ticketDAO.save(ticketConverter.toEntity(ticketDTO));
    }

    public boolean checkEnoughTimeBeforeDeparture(TicketDTO ticketDTO) {
      return ticketDAO.checkEnoughTimeBeforeDeparture(ticketConverter.toEntity(ticketDTO));
    }

    @Transactional
    public boolean findSamePassenger(TicketDTO ticketDTO) {
        return ticketDAO.findSamePassenger(ticketConverter.toEntity(ticketDTO));
    }

    @Transactional
    public List<AllPassengersDTO> getAllPassengers(Integer id, Date day) {
        return ticketDAO.getAllPassengers(id,day);
    }
}
