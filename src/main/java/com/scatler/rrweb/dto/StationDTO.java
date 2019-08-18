package com.scatler.rrweb.dto;

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
    private Date timezone;
}
