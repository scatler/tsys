package com.scatler.rrweb.service.converter;


import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import com.scatler.rrweb.entity.AbstractEntity;

public interface IConverter<E extends AbstractEntity, D extends AbstractDTO> {
    D toDto(E entity);
    E toEntity(D dto);
}