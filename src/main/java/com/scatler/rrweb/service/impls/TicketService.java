package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.TicketDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.dto.forms.AvailableTrain;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.interfaces.ICustomTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Qualifier("ticketService")
public class TicketService extends AbstractService<Ticket, TicketDTO> implements ICustomTicketService {

    @Autowired
    public TicketService(IDao<Ticket, Integer> dao, IConverter<Ticket, TicketDTO> converter) {
        super(dao, converter);
    }

    @Override
    protected IDao<Ticket, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<Ticket, TicketDTO> getConverter() {
        return converter;
    }

    @Override
    @Transactional
    public List<AvailableTrain> getAvailableTrains(int station_id1, int station_id2, Date day) {
        return ((TicketDAO) dao).getAvailableTrains(station_id1,station_id2,day);
    }

    @Override
    @Transactional
    public void registerTicket (TicketDTO ticketDTO) throws FoundSamePassengerException {

        if(findSamePassenger(ticketDTO))throw new FoundSamePassengerException("Same passenger found");
        dao.save(converter.toEntity(ticketDTO));
    }

    @Override
    public void checkEnoughTimeBeforeDeparture(TicketDTO ticketDTO) throws NotEnoughTimeBeforeDeparture {
        ((TicketDAO) dao).checkEnoughTimeBeforeDeparture(converter.toEntity(ticketDTO));
    }


    @Transactional
    public boolean findSamePassenger(TicketDTO ticketDTO) {
        return ((TicketDAO) dao).findSamePassenger(converter.toEntity(ticketDTO));
    }
}
