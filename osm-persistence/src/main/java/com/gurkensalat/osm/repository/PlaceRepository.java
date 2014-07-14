package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.Place;
import com.gurkensalat.osm.entity.PlaceType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "place", path = "place")
public interface PlaceRepository extends PagingAndSortingRepository<Place, Long>
{
    List<Place> findByName(@Param("name") String name);

    List<Place> findByNameAndType(@Param("name") String name, @Param("type") PlaceType type);
}
