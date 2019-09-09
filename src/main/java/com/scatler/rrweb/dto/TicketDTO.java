package com.scatler.rrweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+3")
    private Date birthday;
    private Integer station1Id;
    private Integer station2Id;
    private Integer trd;

}
