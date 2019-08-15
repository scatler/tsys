package com.scatler.rrweb.service.interfaces;

import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ICustomTicketService {
    @Transactional
    List<AvailableTrain> getAvailableTrains(int station_id1, int station_id2, Date day);


    @Transactional
    void registerTicket (TicketDTO ticketDTO) throws FoundSamePassengerException;

    void checkEnoughTimeBeforeDeparture(TicketDTO ticketDTO) throws NotEnoughTimeBeforeDeparture;

}
