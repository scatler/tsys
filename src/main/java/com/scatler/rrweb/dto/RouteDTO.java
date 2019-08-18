package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.api.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO  extends AbstractDTO {

    private Integer id;
    @NotNull
    @NotEmpty
    private String name;
}
