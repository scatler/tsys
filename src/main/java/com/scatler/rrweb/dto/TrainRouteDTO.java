package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainRouteDTO extends AbstractDTO {
    @NotNull
    private Integer trainId;
    @NotNull
    private Integer routeId;
    @NotNull
    private Date day;
}
