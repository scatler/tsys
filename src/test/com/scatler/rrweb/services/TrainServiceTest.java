package com.scatler.rrweb.services;

import com.scatler.rrweb.dao.impl.TrainDAO;
import com.scatler.rrweb.dto.StationDTO;
import com.scatler.rrweb.dto.TrainDTO;
import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.entity.Station;
import com.scatler.rrweb.entity.Train;
import com.scatler.rrweb.service.converter.TrainConverter;
import com.scatler.rrweb.service.impl.TrainService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainServiceTest {
    @InjectMocks
    private TrainService mockTrainService;
    @Mock
    private TrainDAO mockTrainDao;
    @Mock
    private TrainConverter trainConverter;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTrainsTest() {
        when(mockTrainDao.getAll()).thenReturn(new ArrayList<Train>());
        List<TrainDTO> dtos = mockTrainService.getAll();
        assertNotNull(dtos);
    }

    @Test
    void getTrainById() {
        Integer id = 1;
        when(mockTrainDao.get(id)).thenReturn(new Train(id));
        Integer idRet = mockTrainDao.get(id).getId();
        assertEquals(id, idRet);
    }
}

