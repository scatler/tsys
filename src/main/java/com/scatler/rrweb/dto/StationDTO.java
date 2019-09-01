package com.scatler.rrweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scatler.rrweb.dto.api.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO extends AbstractDTO {
    private Integer id;
    @NotNull
    private Integer lineId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date timezone;
}
