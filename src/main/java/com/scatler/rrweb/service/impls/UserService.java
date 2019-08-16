package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.UserDAO;
import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.User;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
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



    @Transactional
    public void registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:" + accountDto.getEmail());
        }

        accountDto.setAuthorities(Arrays.asList("ROLE_USER"));
        User user = converter.toEntity(accountDto);
        dao.save(user);
    }

    private boolean emailExists(String email) {
        User user = ((UserDAO) dao).findByEmail(email);
        return user != null;
    }

    public List<UserDTO> getAll() {
        return dao.getAll().stream().map((a)->converter.toDto(a)).collect(Collectors.toList());

    }

    public void save(UserDTO userDTO) {
        dao.save(converter.toEntity(userDTO));
    }

    public void removeById(int id) {
        dao.removeById(id);
    }

    public UserDTO get(int id) {
        return converter.toDto(dao.get(id));
    }
}
