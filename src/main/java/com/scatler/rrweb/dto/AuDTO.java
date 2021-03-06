package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.api.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuDTO extends AbstractDTO {
    private String name;
}
