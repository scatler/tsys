package com.scatler.rrweb.entity.objects.validator;

import com.scatler.rrweb.dto.RouteStationDTO;
import com.scatler.rrweb.dto.forms.RouteStationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddRouteStationFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RouteStationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        RouteStationForm rsForm = (RouteStationForm) obj;
        if (rsForm.getRs() == null) {
            return;
        }

        if (rsForm.getRouteId()==null) {
            errors.rejectValue("routeId","required","Required");
            return;
        }

        for (int i = 0; i < rsForm.getRs().size(); i++) {
            RouteStationDTO dto = rsForm.getRs().get(i);
            if (dto.getArrivalTime()==null)
                errors.rejectValue("rs[" + i + "].arrivalTime", "required.field","Required");
            if (dto.getDay()==null)
                errors.rejectValue("rs[" + i + "].day", "required.field","Required");
            if (dto.getStationId()==null)
                errors.rejectValue("rs[" + i + "].stationId", "required.field", "Required");
            if (dto.getStopMin()==null)
                errors.rejectValue("rs[" + i + "].stopMin", "required.field","Required");
        }
    }
}
