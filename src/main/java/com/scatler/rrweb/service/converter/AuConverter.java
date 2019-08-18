package com.scatler.rrweb.service.converter;

import com.scatler.rrweb.dto.AuDTO;
import com.scatler.rrweb.entity.Authorities;
import org.springframework.stereotype.Component;

@Component
public class AuConverter implements IConverter<Authorities, AuDTO> {
    @Override
    public AuDTO toDto(Authorities entity) {
        return new AuDTO(entity.getAuthority());
    }

    @Override
    public Authorities toEntity(AuDTO dto) {
        return new Authorities(dto.getName());
    }
}
