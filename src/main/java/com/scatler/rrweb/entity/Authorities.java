package com.scatler.rrweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authorities {
    @Id
    @Column(name = "auth")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    private User user;

    public Authorities(String authority) {
        this.authority = authority;
    }
}