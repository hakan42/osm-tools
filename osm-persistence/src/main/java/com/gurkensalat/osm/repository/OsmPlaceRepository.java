package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmPlace;
import com.gurkensalat.osm.entity.PlaceType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "place", path = "place")
public interface OsmPlaceRepository extends PagingAndSortingRepository<OsmPlace, Long>
{
    List<OsmPlace> findByKey(@Param("key") String key);

    List<OsmPlace> findByName(@Param("name") String name);

    List<OsmPlace> findByNameAndType(@Param("name") String name, @Param("type") PlaceType type);
}
