package com.scatler.rrweb.dao.impls;

import com.scatler.rrweb.entity.Train;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDAO extends AbstractDAO<Train> {

    public TrainDAO() {
        super(Train.class);
    }

}
