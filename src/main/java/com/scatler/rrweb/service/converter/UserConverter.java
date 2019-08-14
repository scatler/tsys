package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.Authorities;
import com.scatler.rrweb.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter implements IConverter<User, UserDTO> {
    @Override
    public UserDTO toDto(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setEmail(entity.getEmail());
        userDTO.setLogin(entity.getLogin());
        userDTO.setType(entity.getType());
        userDTO.setPassword(entity.getPassword());
        userDTO.setAuthorities(entity
                .getAuthorities()
                .stream()
                .map(Authorities::getAuthority)
                .collect(Collectors.toList()));
        return userDTO;

    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setType(dto.getType());
        user.setPassword(dto.getPassword());
        user.setAuthorities(dto.getAuthorities()
                .stream()
                .map(Authorities::new)
                .collect(Collectors.toList()));
        return user;
    }
}
