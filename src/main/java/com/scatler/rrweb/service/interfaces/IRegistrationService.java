package com.scatler.rrweb.service.interfaces;

import com.scatler.rrweb.dto.UserDTO;
import com.scatler.rrweb.entity.objects.exception.EmailExistsException;
import org.springframework.transaction.annotation.Transactional;

public interface IRegistrationService {


    @Transactional
    void registerNewUserAccount(UserDTO accountDto)
            throws EmailExistsException;

    boolean emailExists(String email);
}
