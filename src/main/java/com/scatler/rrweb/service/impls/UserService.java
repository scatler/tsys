package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.UserDAO;
import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.User;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.interfaces.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Qualifier("userService")
public class UserService extends AbstractService<User, UserDTO> implements UserDetailsService, IRegistrationService {

    @Autowired
    public UserService(IDao<User, Integer> dao, IConverter<User, UserDTO> converter) {
        super(dao, converter);
    }

    @Override
    IDao<User, Integer> getDao() {
        return dao;
    }

    @Override
    IConverter<User, UserDTO> getConverter() {
        return converter;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = ((UserDAO) dao).findUserByLogin(login);
        UserDTO userDTO = converter.toDto(user);
        UserBuilder builder = null;
        if (userDTO != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.password(userDTO.getPassword());
            String[] authorities = userDTO.getAuthorities().toArray(new String[0]);
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    @Transactional
    @Override
    public void registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:" + accountDto.getEmail());
        }

        accountDto.setAuthorities(Arrays.asList("ROLE_USER"));
        User user = converter.toEntity(accountDto);
        dao.save(user);
    }

    @Override
    public boolean emailExists(String email) {
        User user = ((UserDAO) dao).findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
