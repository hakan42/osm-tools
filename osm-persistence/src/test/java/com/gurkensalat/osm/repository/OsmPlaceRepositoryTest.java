package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.SimpleConfiguration;
import com.gurkensalat.osm.entity.OsmPlace;
import com.gurkensalat.osm.entity.PlaceType;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
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
        // assertEquals(savedPlace.getId(), new Long(1));
        // id comparision does not work anymore with non-transactional tests...
    }

    @Test
    public void testQueryByBbox()
    {
        List<OsmPlace> result = osmPlaceRepository.findByBbox(1, 2, 3, 4);

        assertNotNull(result);
    }

    @Test
    // @Transactional // Must not be transactional
    @Transactional(Transactional.TxType.NEVER)
    public void testIndvalidateByCountryCode()
    {
        OsmPlace placeDE = new OsmPlace("DE", PlaceType.OSM_PLACE_OF_WORSHIP);
        placeDE.setValid(true);
        placeDE.getAddress().setCountry("DE");
        placeDE = osmPlaceRepository.save(placeDE);

        OsmPlace placeTR = new OsmPlace("TR", PlaceType.OSM_PLACE_OF_WORSHIP);
        placeTR.setValid(true);
        placeTR.getAddress().setCountry("TR");
        placeTR = osmPlaceRepository.save(placeTR);

        // TODO replace findById(..).orElse() by proper use of Optionals
        // https://stackoverflow.com/questions/44101061/missing-crudrepositoryfindone-method

        // Reload and check validity
        placeDE = osmPlaceRepository.findById(placeDE.getId()).orElse(null);
        assertTrue("Place should have been valid", placeDE.isValid());

        placeTR = osmPlaceRepository.findById(placeTR.getId()).orElse(null);
        assertTrue("Place should have been valid", placeTR.isValid());

        // Invalidate part of the entries
        osmPlaceRepository.invalidateByCountryCode("TR");

        // Reload and check validity, again...
        placeDE = osmPlaceRepository.findById(placeDE.getId()).orElse(null);
        assertTrue("Place should have been valid", placeDE.isValid());

        placeTR = osmPlaceRepository.findById(placeTR.getId()).orElse(null);
        assertFalse("Place should NOT have been valid", placeTR.isValid());
    }
}
