package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RouteStationDTO extends AbstractDTO {
    private Integer id;
    private Date arrivalTime;
    private Date stopMin;
    private Integer day;
    private Integer routeId;
    private Integer stationId;
}

