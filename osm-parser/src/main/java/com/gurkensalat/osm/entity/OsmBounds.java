package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlAttribute;

public class OsmBounds
{
    double minlon;

    double minlat;

    double maxlon;

    double maxlat;

    @XmlAttribute(name = "minlon")
    public double getMinlon()
    {
        return minlon;
    }

    public void setMinlon(double minlon)
    {
        this.minlon = minlon;
    }

    @XmlAttribute(name = "minlat")
    public double getMinlat()
    {
        return minlat;
    }

    public void setMinlat(double minlat)
    {
        this.minlat = minlat;
    }

    @XmlAttribute(name = "maxlon")
    public double getMaxlon()
    {
        return maxlon;
    }

    public void setMaxlon(double maxlon)
    {
        this.maxlon = maxlon;
    }

    @XmlAttribute(name = "maxlat")
    public double getMaxlat()
    {
        return maxlat;
    }

    public void setMaxlat(double maxlat)
    {
        this.maxlat = maxlat;
    }
}
