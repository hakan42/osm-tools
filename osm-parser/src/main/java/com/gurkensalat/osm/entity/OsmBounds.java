package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
