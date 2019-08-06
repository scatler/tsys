package com.scatler.rrweb.entity.objects.searchresult;
import java.util.Date;

/**
 * Created by alexkpc on 03.08.2019.
 */

public class StationTimeTable {

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private int trainId;
    private Date arrivalTime;

    public StationTimeTable(int trainId, Date arrivalTime) {
        this.trainId = trainId;
        this.arrivalTime = arrivalTime;
    }
}
