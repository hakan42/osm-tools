package com.gurkensalat.osm.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address
{
    @Column(name = "ADDR_STREET", length = 80)
    private String street;

    @Column(name = "ADDR_NUMBER", length = 10)
    private String housenumber;

    @Column(name = "ADDR_POSTCODE", length = 10)
    private String postcode;

    @Column(name = "ADDR_CITY", length = 80)
    private String city;

    @Column(name = "ADDR_STATE", length = 80)
    private String state;

    @Column(name = "ADDR_COUNTRY", length = 80)
    private String country;

    public Address()
    {
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHousenumber()
    {
        return housenumber;
    }

    public void setHousenumber(String housenumber)
    {
        this.housenumber = housenumber;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String toString()
    {
        return (new ToStringBuilder(this))
                .append("street", street)
                .append("housenumber", housenumber)
                .append("postcode", postcode)
                .append("city", city)
                .append("state", state)
                .append("country", country)
                .toString();
    }
}
