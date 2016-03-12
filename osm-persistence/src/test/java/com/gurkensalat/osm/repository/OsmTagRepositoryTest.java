package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.SimpleConfiguration;
import com.gurkensalat.osm.entity.OsmTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = SimpleConfiguration.class)
public class OsmTagRepositoryTest
{
    @Autowired
    OsmTagRepository osmTagRepository;

    private OsmTag tag;

    @Before
    public void setUp()
    {

    }

    @Test
    public void saveTag()
    {
        OsmTag Tag = new OsmTag();

        assertTrue(Tag.isNew());

        OsmTag savedTag = osmTagRepository.save(Tag);
        assertNotNull(savedTag);
        assertFalse(Tag.isNew());
        // assertEquals(Long.valueOf(2), savedTag.getId());
        // id comparision does not work anymore with non-transactional tests...
    }

    @Test
    public void testFindByParentTableAndParentId()
    {
        Iterable<OsmTag> tags = osmTagRepository.findByParentTableAndParentId("OSM_PLACES", Long.valueOf(42));

        assertNotNull(tags);
    }

    @Test
    public void testDeleteByParentTableAndParentId()
    {
        osmTagRepository.deleteByParentTableAndParentId("OSM_PLACES", Long.valueOf(42));
    }
}
