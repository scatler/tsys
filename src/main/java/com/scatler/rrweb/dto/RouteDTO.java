package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO  extends AbstractDTO {
    private Integer id;
    private String name;
}
