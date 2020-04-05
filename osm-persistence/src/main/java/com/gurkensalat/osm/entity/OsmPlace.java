package com.gurkensalat.osm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OSM_PLACES")
@Getter
@Setter
@NoArgsConstructor
public class OsmPlace extends OsmPlaceBase
{
    private transient String mapUrl;

    public OsmPlace(String name, PlaceType type)
    {
        super(name, type);

        if (getCreationTime() == null)
        {
            setCreationTime(DateTime.now());
        }

        if (getModificationTime() == null)
        {
            setModificationTime(DateTime.now());
        }
    }

    public OsmPlace(OsmNode node)
    {
        super(node);
    }
}
