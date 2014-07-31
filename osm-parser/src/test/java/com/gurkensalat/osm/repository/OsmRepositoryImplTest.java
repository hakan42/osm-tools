package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmRoot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OsmRepositoryImplTest
{
    OsmRepositoryImpl testable;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        testable = new OsmRepositoryImpl();
    }

    @Test
    public void parseMosqueGermeringData() throws IOException
    {
        File file = new File("src/test/resources/germany-mosque-germering.osm");
        OsmRoot root = testable.parse(file);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }

    @Test
    public void parsePlaceAnkaraData() throws IOException
    {
        File file = new File("src/test/resources/turkey-places-city-ankara.osm");
        OsmRoot root = testable.parse(file);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }
}
