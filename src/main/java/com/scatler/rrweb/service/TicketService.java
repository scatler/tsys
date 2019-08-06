package com.scatler.rrweb.service;

import com.scatler.rrweb.dao.TicketDAO;
import com.scatler.rrweb.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Transactional
    public List<Train> getAvailableTrains(int station_id1, int station_id2, Date day) {
        return ticketDAO.getAvailableTrains(station_id1,station_id2,day);
    }
}
