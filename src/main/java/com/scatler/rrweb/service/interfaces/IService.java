package com.scatler.rrweb.service.interfaces;

import com.scatler.rrweb.dto.forms.StationTimeTable;
import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import com.scatler.rrweb.entity.User;

import java.util.Date;
import java.util.List;

public interface IService<D extends AbstractDTO, K> {
    void update(D dto);

    void remove(D dto);

    void removeById(K id);

    D get(K id);

    List<D> getAll();

    void save(D dto);

    void saveAll (List<D> dto);


}
