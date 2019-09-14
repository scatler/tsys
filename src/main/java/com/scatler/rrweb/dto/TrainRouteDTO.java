package com.scatler.rrweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scatler.rrweb.dto.api.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainRouteDTO extends AbstractDTO {

    private Integer id;
    @NotNull
    private Integer trainId;
    @NotNull
    private Integer routeId;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+3")
    private Date day;
}
