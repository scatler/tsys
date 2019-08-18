package com.scatler.rrweb.dto;

import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import com.scatler.rrweb.entity.objects.validator.PasswordMatches;
import com.scatler.rrweb.entity.objects.validator.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDTO extends AbstractDTO {
    private Integer id;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String matchingPassword;
    private String type;
    private String[] Authorities;
}
