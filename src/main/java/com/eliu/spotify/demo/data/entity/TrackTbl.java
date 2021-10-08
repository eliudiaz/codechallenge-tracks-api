package com.eliu.spotify.demo.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class TrackTbl {
    @Id
    private String isrc;
    private Integer durationMs;
    private Boolean explicit;
    private String name;
}
