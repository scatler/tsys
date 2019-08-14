package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDTO extends AbstractDTO {
    private Integer id;
    private String name;
}
