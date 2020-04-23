package com.gurkensalat.osm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "OSM_PLACES")
@NoArgsConstructor
public class OsmPlace extends OsmPlaceBase
{
    private transient String mapUrl;

    public OsmPlace(String name, PlaceType type)
    {
        super(name, type);

        if (getCreationTime() == null)
        {
            setCreationTime(LocalDateTime.now());
        }

        if (getModificationTime() == null)
        {
            setModificationTime(LocalDateTime.now());
        }
    }

    public OsmPlace(OsmNode node)
    {
        super(node);
    }
}
