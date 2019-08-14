package com.scatler.rrweb.service.impls;

import com.scatler.rrweb.dao.interfaces.IDao;
import com.scatler.rrweb.dto.interfaces.AbstractDTO;
import com.scatler.rrweb.entity.AbstractEntity;
import com.scatler.rrweb.service.converter.IConverter;
import com.scatler.rrweb.service.interfaces.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDTO> implements IService<D, Integer> {

    protected IDao<E, Integer> dao;
    protected IConverter<E, D> converter;

    public AbstractService() {

    }

    public AbstractService(IDao<E, Integer> dao, IConverter<E, D> converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void update(D dto) {

    }

    @Override
    public void remove(D dto) {

    }

    @Override
    public void removeById(Integer id) {

    }

    @Override
    public D get(Integer id) {
        return null;
    }

    @Transactional
    @Override
    public List<D> getAll() {
        List<D> dtos = new ArrayList<>();
        getDao().getAll().forEach(item -> dtos.add(getConverter().toDto(item)));
        return dtos;
    }

    @Override
    @Transactional
    public void save(D dto) {
        dao.save(converter.toEntity(dto));
    }

    @Override
    @Transactional
    public void saveAll(List<D> dto) {
        List<E> entities = new ArrayList<>();
        dto.forEach(item -> entities.add(getConverter().toEntity(item)));
        getDao().saveAll(entities);
    }

    abstract IDao<E, Integer> getDao();

    abstract IConverter<E, D> getConverter();
}
