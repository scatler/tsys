package com.scatler.rrweb.dto.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AvailableTrain implements Serializable {


    private Object trainId;//1
    private Object trainName;//2
    private Object trainRouteDay;//3
    private Object routeId;//4
    private Object station1Id;//5
    private Object station1Name;//6
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private Object arrivalTimeToStation1;//7
    private Object station2Id;//8
    private Object station2Name;//9
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private Object arrivalTimeToStation2;//10
    private Object freeTickets;//11
    private Object totalSeats;//12


}
