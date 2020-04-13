package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OsmBounds
{
    @JacksonXmlProperty(localName = "minlon")
    double minlon;

    @JacksonXmlProperty(localName = "minlat")
    double minlat;

    @JacksonXmlProperty(localName = "maxlon")
    double maxlon;

    @JacksonXmlProperty(localName = "maxlat")
    double maxlat;

    public double getMinlon()
    {
        return minlon;
    }

    public void setMinlon(double minlon)
    {
        this.minlon = minlon;
    }

    public double getMinlat()
    {
        return minlat;
    }

    public void setMinlat(double minlat)
    {
        this.minlat = minlat;
    }

    public double getMaxlon()
    {
        return maxlon;
    }

    public void setMaxlon(double maxlon)
    {
        this.maxlon = maxlon;
    }

    public double getMaxlat()
    {
        return maxlat;
    }

    public void setMaxlat(double maxlat)
    {
        this.maxlat = maxlat;
    }
}
