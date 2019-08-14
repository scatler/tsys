package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import lombok.Data;
import org.springframework.beans.PropertyValues;

import java.util.List;
import java.util.Set;


@Data
public class UserDTO extends AbstractDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String type;
    private List<String> Authorities;
}
