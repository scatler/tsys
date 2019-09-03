package com.scatler.rrweb.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "station")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s"),
        @NamedQuery(name = "Station.findById", query = "SELECT s FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.findByName", query = "SELECT s FROM Station s WHERE s.name = :name"),
        @NamedQuery(name = "Station.findByTimezone", query = "SELECT s FROM Station s WHERE s.timezone = :timezone")})
@Data
public class Station extends AbstractEntity implements Serializable {

    @NotEmpty(message = "Name is required")
    @Size(max = 45)
    @Column(name = "name")
    private String name;


    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "timezone")
    @Temporal(TemporalType.TIME)
    private Date timezone;

    @OneToMany(mappedBy = "station1Id", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    @OneToMany(mappedBy = "station2Id", fetch = FetchType.LAZY)
    private List<Ticket> ticketList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stationId", fetch = FetchType.LAZY)
    private List<RouteStation> routeStationList;


    @NotNull(message = "Select line")
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Line lineId;

    public Station() {
    }

    public Station(Integer id) {
        this.id = id;
    }

}
