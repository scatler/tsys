package com.scatler.rrweb.dto.forms;

import com.scatler.rrweb.dto.RouteStationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteStationForm {
    private Integer routeId;
    private ArrayList<RouteStationDTO> rs;

    public void prepareData() {
        rs = (ArrayList<RouteStationDTO>) rs.stream().filter((a)-> !(a.getIsDeleted())).collect(Collectors.toList());
        rs.forEach(item -> item.setRouteId(routeId));
    }
}