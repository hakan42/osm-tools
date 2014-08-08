package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmRoot;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.closeQuietly;
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
        InputStream is = getClass().getClassLoader().getResourceAsStream("germany-mosque-germering.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }

    @Test
    public void parsePlaceAnkaraData() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("turkey-places-city-ankara.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }
}
