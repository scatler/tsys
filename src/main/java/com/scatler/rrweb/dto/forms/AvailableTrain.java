package com.scatler.rrweb.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableTrain implements Serializable {

    private Object train;//1
    private Object trainRouteDay;//2
    private Object route;//3
    private Object station1Id;//4
    private Object station1Name;//5
    //private Object dayOfStart;//6
    private Object arrivalTimeToStation1;//7
    private Object arrivalDayToStation1;//8
    private Object arrivalTimeToStation2;//9
    private Object arrivalDayToStation2;//10
    private Object station2id;//11
    private Object station2name;//12
    private Object free_tickets;//13

}
