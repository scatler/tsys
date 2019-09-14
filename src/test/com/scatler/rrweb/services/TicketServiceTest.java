package com.scatler.rrweb.services;

import com.scatler.rrweb.dao.impl.RouteDAO;
import com.scatler.rrweb.dao.impl.TicketDAO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NoMoreFreeSeatsException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.service.converter.TicketConverter;
import com.scatler.rrweb.service.impl.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @InjectMocks
    private TicketService mockTicketService;
    @Mock
    private TicketDAO mockTicketDAO;
    @Mock
    private TicketConverter mockConverter;
    @Mock
    private RouteDAO mockRouteDao;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void notEnoughTimeTest() {
        TicketDTO ticketDTO = new TicketDTO();
        Ticket ticket = mockConverter.toEntity(ticketDTO);
        ticketDTO.setBirthday(new Date());
        when(mockTicketDAO.checkEnoughTimeBeforeDeparture(ticket)).thenReturn(true);
        assertThrows(NotEnoughTimeBeforeDeparture.class, () -> mockTicketService
                .registerTicket(ticketDTO));
        verify(mockTicketDAO, times(1)).checkEnoughTimeBeforeDeparture(ticket);
        verify(mockTicketDAO, times(0)).findSamePassenger(ticket);
        verifyNoMoreInteractions(mockTicketDAO);
        verifyZeroInteractions(mockRouteDao);
    }

    @Test
    void samePassengerTest() {
        TicketDTO ticketDTO = new TicketDTO();
        Ticket ticket = mockConverter.toEntity(ticketDTO);
        ticketDTO.setBirthday(new Date());
        when(mockTicketDAO.findSamePassenger(ticket)).thenReturn(true);
        assertThrows(FoundSamePassengerException.class, () -> mockTicketService
                .registerTicket(ticketDTO));
        verify(mockTicketDAO, times(1)).checkEnoughTimeBeforeDeparture(ticket);
        verify(mockTicketDAO, times(1)).findSamePassenger(ticket);
        verifyNoMoreInteractions(mockTicketDAO);
        verifyZeroInteractions(mockRouteDao);


    }

    @Test
    void noMoreFreeSeats() {
        TicketDTO ticketDTO = new TicketDTO();
        Ticket ticket = mockConverter.toEntity(ticketDTO);
        ticketDTO.setBirthday(new Date());
        when(mockTicketDAO.checkForFreeSeats(ticket)).thenReturn(true);
        assertThrows(NoMoreFreeSeatsException.class, () -> mockTicketService
                .registerTicket(ticketDTO));
        verify(mockTicketDAO, times(1)).checkEnoughTimeBeforeDeparture(ticket);
        verify(mockTicketDAO, times(1)).findSamePassenger(ticket);
        verify(mockTicketDAO, times(1)).checkForFreeSeats(ticket);
        verifyNoMoreInteractions(mockTicketDAO);
        verifyZeroInteractions(mockRouteDao);


    }
}

