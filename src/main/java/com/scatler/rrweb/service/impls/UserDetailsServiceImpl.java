package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.impls.UserDAO;
import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.User;
import com.scatler.rrweb.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDAO dao;
    @Autowired
    UserConverter converter;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = dao.findUserByLogin(login);
        UserDTO userDTO = converter.toDto(user);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (userDTO != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.password(userDTO.getPassword());
            String[] authorities = userDTO.getAuthorities();
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
