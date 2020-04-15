package com.gurkensalat.osm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
