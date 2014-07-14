package com.gurkensalat.osm.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PLACES")
public class Place extends AbstractPersistable<Long>
{
    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LON")
    private double lon;

    @Column(name = "NAME", length = 40)
    private String name;

    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private PlaceType type;

    protected Place()
    {
    }

    public  Place(String name, PlaceType type)
    {
        this.name = name;
        this.type = type;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }

    public String getName()
    {
        return name;
    }

    public PlaceType getType()
    {
        return type;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(PlaceType type)
    {
        this.type = type;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }
}
