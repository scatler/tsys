package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.api.AbstractDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TicketDTO extends AbstractDTO {

    private Integer id;
    private String name;
    private String surname;
    private Date birthday;
    private Integer station1Id;
    private Integer station2Id;
    private Integer trd;

}
