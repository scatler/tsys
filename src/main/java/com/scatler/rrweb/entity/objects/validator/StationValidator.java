package com.scatler.rrweb.entity.objects.validator;

import com.scatler.rrweb.entity.Station;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Station.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Station station = (Station) target;
        if (station.getLineId().getId()==null) {

           errors.rejectValue("lineId.id","Line id is empty","Select line");
        }

    }
}
