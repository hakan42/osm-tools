package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmPlace;
import com.gurkensalat.osm.entity.PlaceType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "place", path = "place")
public interface OsmPlaceRepository extends PagingAndSortingRepository<OsmPlace, Long>
{
    List<OsmPlace> findByKey(@Param("key") String key);

    List<OsmPlace> findByName(@Param("name") String name);

    List<OsmPlace> findByNameAndType(@Param("name") String name, @Param("type") PlaceType type);

    @Query("SELECT p FROM OsmPlace p WHERE :min_lon <= p.lon and p.lon < :max_lon and :min_lat <= p.lat and p.lat <= :max_lat")
    List<OsmPlace> findByBbox(@Param("min_lon") double minLongitude,
                              @Param("min_lat") double minLatitude,
                              @Param("max_lon") double maxLongitude,
                              @Param("max_lat") double maxLatitude);

    @Modifying
    @Transactional
    @Query("update OsmPlace set valid = 'f'")
    void invalidateAll();

    @Modifying
    @Transactional
    @Query("delete from OsmPlace where valid = 'f'")
    void deleteAllInvalid();
}
