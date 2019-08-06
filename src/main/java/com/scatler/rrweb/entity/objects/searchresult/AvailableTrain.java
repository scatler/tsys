package com.scatler.rrweb.entity.objects.searchresult;

import java.util.Date;

public class AvailableTrain {

    private int routeId;
    private int trainId;
    private int availableSeats;
    private int totalSeats;
    private Date arrivalTime1;
    private Date arrivalTime2;
    private Date day1;
    private Date day2;

    public AvailableTrain(int routeId, int trainId, int availableSeats, int totalSeats, Date arrivalTime1, Date arrivalTime2, Date day1, Date day2, String stationName1, String stationName2) {
        this.routeId = routeId;
        this.trainId = trainId;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
        this.arrivalTime1 = arrivalTime1;
        this.arrivalTime2 = arrivalTime2;
        this.day1 = day1;
        this.day2 = day2;
        this.stationName1 = stationName1;
        this.stationName2 = stationName2;
    }

    private String stationName1;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Date getArrivalTime1() {
        return arrivalTime1;
    }

    public void setArrivalTime1(Date arrivalTime1) {
        this.arrivalTime1 = arrivalTime1;
    }

    public Date getArrivalTime2() {
        return arrivalTime2;
    }

    public void setArrivalTime2(Date arrivalTime2) {
        this.arrivalTime2 = arrivalTime2;
    }

    public Date getDay1() {
        return day1;
    }

    public void setDay1(Date day1) {
        this.day1 = day1;
    }

    public Date getDay2() {
        return day2;
    }

    public void setDay2(Date day2) {
        this.day2 = day2;
    }

    public String getStationName1() {
        return stationName1;
    }

    public void setStationName1(String stationName1) {
        this.stationName1 = stationName1;
    }

    public String getStationName2() {
        return stationName2;
    }

    public void setStationName2(String stationName2) {
        this.stationName2 = stationName2;
    }

    private String stationName2;
}
