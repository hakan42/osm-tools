package com.gurkensalat.osm.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Address
{
    @Column(name = "ADDR_STREET", length = 80)
    private String street;

    @Column(name = "ADDR_HOUSENUMBER", length = 20)
    private String housenumber;

    @Column(name = "ADDR_POSTCODE", length = 10)
    private String postcode;

    @Column(name = "ADDR_CITY", length = 80)
    private String city;

    @Column(name = "ADDR_STATE", length = 80)
    private String state;

    @Column(name = "ADDR_COUNTRY", length = 80)
    private String country;

    public void copyTo(Address other)
    {
        other.setStreet(getStreet());
        other.setHousenumber(getHousenumber());
        other.setPostcode(getPostcode());
        other.setCity(getCity());
        other.setState(getState());
        other.setCountry(getCountry());
    }
}
