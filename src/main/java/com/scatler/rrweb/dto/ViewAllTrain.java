package com.scatler.rrweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAllTrain implements Serializable {
    private Integer id;
    private String name;
    private Integer routeId;
    private String routeName;
    private Integer totalSeats;
    private Date dayDept;
}
