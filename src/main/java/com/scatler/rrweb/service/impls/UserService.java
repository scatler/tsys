package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.AuDAO;
import com.scatler.rrweb.dao.impls.UserDAO;
import com.scatler.rrweb.dto.AuDTO;
import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.User;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import com.scatler.rrweb.service.converter.AuConverter;
import com.scatler.rrweb.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDAO dao;
    @Autowired
    UserConverter converter;
    @Autowired
    AuDAO auDAO;
    @Autowired
    AuConverter auConverter;

    @Transactional
    public void registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {
        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:" + accountDto.getEmail(), "registration");
        }
        accountDto.setAuthorities(new String[]{"ROLE_USER"});
        User user = converter.toEntity(accountDto);
        dao.save(user);
    }

    private boolean emailExists(String email) {
        User user = dao.findByEmail(email);
        return user != null;
    }

    @Transactional
    public List<UserDTO> getAll() {
        return dao.getAll().stream().map((a) -> converter.toDto(a)).collect(Collectors.toList());
    }

    @Transactional
    public void save(UserDTO userDTO) throws EmailExistsException {
        boolean userExists = userDTO.getId() != null;
        boolean emailExists = dao.findByEmail(userDTO.getEmail()) != null;
        if (emailExists && userExists) {
            dao.merge(converter.toEntity(userDTO));
        } else {
            throw new EmailExistsException("The user with same e-mail is already registered", "user-add");
        }
    }

    @Transactional
    public void removeById(int id) {
        dao.removeById(id);
    }

    @Transactional
    public UserDTO get(int id) {
        return converter.toDto(dao.get(id));
    }

    @Transactional
    public List<AuDTO> getAllAu() {
        return auDAO.getAll().stream().map((a) -> auConverter.toDto(a)).collect(Collectors.toList());
    }
}
