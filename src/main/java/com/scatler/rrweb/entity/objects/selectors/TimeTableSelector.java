package com.scatler.rrweb.entity.objects.selectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableSelector {

    @NotNull
    private Integer id;
    @NotNull
    private Date day;

}
