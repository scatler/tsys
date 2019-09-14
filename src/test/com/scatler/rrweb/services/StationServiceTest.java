package com.scatler.rrweb.services;

import com.scatler.rrweb.dao.impl.RouteDAO;
import com.scatler.rrweb.dao.impl.StationDAO;
import com.scatler.rrweb.dao.impl.TicketDAO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.TicketDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Ticket;
import com.scatler.rrweb.entity.objects.exception.FoundSamePassengerException;
import com.scatler.rrweb.entity.objects.exception.NoMoreFreeSeatsException;
import com.scatler.rrweb.entity.objects.exception.NotEnoughTimeBeforeDeparture;
import com.scatler.rrweb.service.converter.StationConverter;
import com.scatler.rrweb.service.converter.TicketConverter;
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
class StationServiceTest {
    @InjectMocks
    private StationService stationService;
    @Mock
    private StationDAO stationDAO;
    @Mock
    private StationConverter mockConverter;
    @Mock
    private RouteDAO mockRouteDao;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllStationsTest() {
        when(stationDAO.getAll()).thenReturn(new ArrayList<Station>());
        List<StationDTO> dtos = stationService.getAll();
        assertNotNull(dtos);
    }

    @Test
    void getStationScheduleTest() {
        Integer statioinId = 1;
        Date today = new Date();
        when(stationDAO.getStationSchedule(statioinId,today)).thenReturn(new ArrayList<StationTimeTable>());
        List<StationTimeTable> dtos = stationService.getStationSchedule(statioinId,today);
        assertNotNull(dtos);
    }

    @Test
    void getStationTest () {
        Integer id = 1;
        when(stationDAO.get(id)).thenReturn(new Station(id));
        Integer idRet = stationDAO.get(id).getId();
        assertEquals(id,idRet);
    }

    @Test
    void addOrUpdateTest () {
        StationDTO stationDto = new StationDTO();
        Station station = new Station();
        when(stationDAO.addOrUpdate(station)).thenReturn(1);
        when(mockConverter.toEntity(stationDto)).thenReturn(new Station());
        Integer id = stationService.addOrUpdate(stationDto);
        assertNotNull(id);
    }


}

