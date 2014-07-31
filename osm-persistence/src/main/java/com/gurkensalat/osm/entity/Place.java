package com.gurkensalat.osm.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PLACES")
public class Place extends AbstractPersistable<Long>
{
    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private PlaceType type;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LON")
    private double lon;

    @Column(name = "NAME", length = 80)
    private String name;

    @Column(name = "ADDR_STREET", length = 80)
    private String addrStreet;

    @Column(name = "ADDR_NUMBER", length = 10)
    private String addrHouseNumber;

    @Column(name = "ADDR_POSTCODE", length = 10)
    private String addrPostcode;

    @Column(name = "ADDR_STATE", length = 80)
    private String addrState;

    @Column(name = "ADDR_COUNTRY", length = 80)
    private String addrCountry;

    protected Place()
    {
    }

    public  Place(String name, PlaceType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getAddrStreet()
    {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet)
    {
        this.addrStreet = addrStreet;
    }

    public String getAddrHouseNumber()
    {
        return addrHouseNumber;
    }

    public void setAddrHouseNumber(String addrHouseNumber)
    {
        this.addrHouseNumber = addrHouseNumber;
    }

    public String getAddrPostcode()
    {
        return addrPostcode;
    }

    public void setAddrPostcode(String addrPostcode)
    {
        this.addrPostcode = addrPostcode;
    }

    public String getAddrState()
    {
        return addrState;
    }

    public void setAddrState(String addrState)
    {
        this.addrState = addrState;
    }

    public String getAddrCountry()
    {
        return addrCountry;
    }

    public void setAddrCountry(String addrCountry)
    {
        this.addrCountry = addrCountry;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public PlaceType getType()
    {
        return type;
    }

    public void setType(PlaceType type)
    {
        this.type = type;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }
}
