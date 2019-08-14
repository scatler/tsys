package com.scatler.rrweb.dto.forms;

import com.scatler.rrweb.dto.RouteStationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteStationForm {

    private Integer routeId;
    private ArrayList<RouteStationDTO> rs;

    public void prepareData () {
        rs.forEach(item -> item.setRouteId(routeId));
    }
}