package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.SimpleConfiguration;
import com.gurkensalat.osm.entity.OsmPlace;
import com.gurkensalat.osm.entity.PlaceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = SimpleConfiguration.class)
public class OsmPlaceRepositoryTest
{
    @Autowired
    OsmPlaceRepository osmPlaceRepository;

    private OsmPlace place;

    @Before
    public void setUp()
    {

    }

    @Test
    public void savePlace()
    {
        OsmPlace place = new OsmPlace("test", PlaceType.OSM_CITY);

        assertTrue(place.isNew());

        OsmPlace savedPlace = osmPlaceRepository.save(place);
        assertNotNull(savedPlace);
        assertFalse(place.isNew());
        assertEquals(savedPlace.getId(), new Long(1));
    }

    @Test
    public void testQueryByBbox()
    {
        List<OsmPlace> result = osmPlaceRepository.findByBbox(1, 2, 3, 4);

        assertNotNull(result);
    }
}
