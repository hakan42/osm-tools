package com.gurkensalat.osm.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
// @NoArgsConstructor
public abstract class OsmPlaceBase extends AbstractPersistable<Long>
{
    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "VALID")
    private boolean valid;

    @Column(name = "CREATION_TIME", nullable = false)
    @CreatedDate
    private LocalDateTime creationTime;

    @Column(name = "MODIFICATION_TIME", nullable = false)
    @LastModifiedDate
    private LocalDateTime modificationTime;

    @Column(name = "TYPE", length = 8)
    @Enumerated(EnumType.STRING)
    private OsmEntityType type;

    @Column(name = "PLACE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Column(name = "D_KEY", length = 80)
    private String key;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LON")
    private double lon;

    @Column(name = "NAME", length = 80)
    private String name;

    @Column(name = "IMAGE", length = 200)
    private String image;

    private Address address;

    private Contact contact;

    protected OsmPlaceBase()
    {
        this.setAddress(new Address());
        this.setContact(new Contact());
    }

    public OsmPlaceBase(String name, PlaceType placeType)
    {
        this.setPlaceType(placeType);
        this.setName(name);
        this.setAddress(new Address());
        this.setContact(new Contact());
    }

    public OsmPlaceBase(OsmNode node)
    {
        this.setLat(node.getLat());
        this.setLon(node.getLon());
        this.setAddress(new Address());
        this.setContact(new Contact());

        for (OsmNodeTag tag : node.getTags())
        {
            String key = tag.getKey().toLowerCase();
            String val = tag.getValue();

            useTag(key, val);
        }
    }

    public OsmPlaceBase(OsmWay way)
    {
        this.setLat(way.getLat());
        this.setLon(way.getLon());
        this.setAddress(new Address());
        this.setContact(new Contact());

        for (OsmNodeTag tag : way.getTags())
        {
            String key = tag.getKey().toLowerCase();
            String val = tag.getValue();

            useTag(key, val);
        }
    }

    private void useTag(String key, String val)
    {
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
        else if ("addr:country".equals(key))
        {
            this.getAddress().setCountry(val);
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
        else if ("image".equals(key))
        {
            this.setImage(val);
        }
    }

    public void copyTo(OsmPlace other)
    {
        other.setLon(getLon());
        other.setLat(getLat());
        other.setName(getName());
        other.setImage(getImage());
        other.setType(getType());
        other.setPlaceType(getPlaceType());
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

    public void setValid(boolean valid)
    {
        this.valid = valid;
        this.setModificationTime(LocalDateTime.now());
    }
}
