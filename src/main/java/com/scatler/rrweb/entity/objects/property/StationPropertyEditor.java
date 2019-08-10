package com.scatler.rrweb.entity.objects.property;

import com.scatler.rrweb.entity.Line;
import com.scatler.rrweb.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;

public class StationPropertyEditor {
    @Autowired
    StationService stationService;

    public void setAsText(String incomming){
        Line b = stationService.findById( Integer.parseInt(incomming));
        setValue(b);
    }
    public String getAsText(){
        return ((Breakfast)getValue()).getId();
    }
}
