package com.scatler.rrweb.services;

import com.scatler.rrweb.dao.impl.RouteDAO;
import com.scatler.rrweb.dao.impl.StationDAO;
import com.scatler.rrweb.dao.impl.TicketDAO;
import com.scatler.rrweb.dto.RouteDTO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.entity.Route;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NoMoreFreeSeatsException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.service.converter.RouteConverter;
import com.scatler.rrweb.service.converter.StationConverter;
import com.scatler.rrweb.service.converter.TicketConverter;
import com.scatler.rrweb.service.impl.RouteService;
import com.scatler.rrweb.service.impl.StationService;
import com.scatler.rrweb.service.impl.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    @InjectMocks
    private RouteService routeService;
    @Mock
    private RouteDAO routeDAO;
    @Mock
    private RouteConverter mockConverter;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllRoutes() {
        when(routeDAO.getAll()).thenReturn(new ArrayList<Route>());
        List<RouteDTO> dtos = routeService.getAll();
        assertNotNull(dtos);
    }

    @Test
    void getRouteByIdTest() {
        Integer id = 1;
        when(routeDAO.get(id)).thenReturn(new Route(id));
        Integer idRet = routeDAO.get(id).getId();
        assertEquals(id, idRet);
    }

    @Test
    void addOrUpdateTest () {
        RouteDTO routeDTO = new RouteDTO();
        Route route = new Route();
        when(routeDAO.addOrUpdate(route)).thenReturn(1);
        when(mockConverter.toEntity(routeDTO)).thenReturn(new Route());
        Integer id = routeService.addOrUpdate(routeDTO);
        assertNotNull(id);
    }
}

