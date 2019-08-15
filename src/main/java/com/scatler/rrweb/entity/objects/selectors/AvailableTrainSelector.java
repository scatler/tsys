package com.scatler.rrweb.entity.objects.selectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableTrainSelector {

    @NotNull
    private Integer stationFrom;
    @NotNull
    private Integer stationTo;
    @NotNull
    private Date day;

}
