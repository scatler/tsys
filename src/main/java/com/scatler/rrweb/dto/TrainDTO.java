package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO extends AbstractDTO {
    private Integer id;
    @NotEmpty (message = "Name is required")
    private String name;
    @NotNull (message = "Seats is required")
    private Integer seats;
}
