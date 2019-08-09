package com.scatler.rrweb.entity.objects.selectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class AvailableTrainSelector {

    @NotEmpty
    private String stationFrom;
    @NotEmpty
    private String stationTo;
    @NotEmpty
    private String day;

}
