package com.gurkensalat.osm.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class OsmPlaceBase extends AbstractPersistable<Long>
{
    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "VALID")
    private boolean valid;

    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private PlaceType type;

    @Column(name = "D_KEY", length = 80)
    private String key;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LON")
    private double lon;

    @Column(name = "NAME", length = 80)
    private String name;

    private Address address;

    private Contact contact;

    protected OsmPlaceBase()
    {
        this.setAddress(new Address());
        this.setContact(new Contact());
    }

    public OsmPlaceBase(String name, PlaceType type)
    {
        this.setName(name);
        this.setType(type);
        this.setAddress(new Address());
        this.setContact(new Contact());
    }

    public OsmPlaceBase(OsmNode node)
    {
        this.setLat(node.getLat());
        this.setLon(node.getLon());
        this.setAddress(new Address());
        this.setContact(new Contact());

        for (OsmTag tag : node.getTags())
        {
            String key = tag.getKey().toLowerCase();
            String val = tag.getValue();

            if ("name".equals(key))
            {
                this.setName(val);
            }
            else if ("addr:street".equals(key))
            {
                this.getAddress().setStreet(val);
            }
            else if ("addr:housenumber".equals(key))
            {
                this.getAddress().setHousenumber(val);
            }
            else if ("addr:postcode".equals(key))
            {
                this.getAddress().setPostcode(val);
            }
            else if ("addr:city".equals(key))
            {
                this.getAddress().setCity(val);
            }
            else if ("contact:phone".equals(key) || "phone".equals(key))
            {
                this.getContact().setPhone(val);
            }
            else if ("contact:fax".equals(key) || "fax".equals(key))
            {
                this.getContact().setFax(val);
            }
            else if ("contact:website".equals(key) || "website".equals(key))
            {
                this.getContact().setWebsite(val);
            }
            else if ("contact:email".equals(key) || "email".equals(key))
            {
                this.getContact().setEmail(val);
            }
        }
    }


    public void copyTo(OsmPlace other)
    {
        other.setLon(getLon());
        other.setLat(getLat());
        other.setName(getName());
        other.setType(getType());
        other.setKey(getKey());
        other.setValid(isValid());

        if (getAddress() == null)
        {
            setAddress(new Address());
        }

        if (other.getAddress() == null)
        {
            other.setAddress(new Address());
        }

        getAddress().copyTo(other.getAddress());

        if (getContact() == null)
        {
            setContact(new Contact());
        }

        if (other.getContact() == null)
        {
            other.setContact(new Contact());
        }

        getContact().copyTo(other.getContact());
    }

    public boolean isValid()
    {
        return valid;
    }

    public void setValid(boolean valid)
    {
        this.valid = valid;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Contact getContact()
    {
        return contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
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

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
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


    public String toString()
    {
        return (new ToStringBuilder(this))
                .append("lat", lat)
                .append("lon", lon)
                .append("name", name)
                .append("type", type)
                .append("address", address)
                .append("contact", contact)
                .toString();
    }
}
