package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OsmWayNodeReference
{
    @JacksonXmlProperty(localName = "ref")
    private String ref;

    private OsmNode node;
}
