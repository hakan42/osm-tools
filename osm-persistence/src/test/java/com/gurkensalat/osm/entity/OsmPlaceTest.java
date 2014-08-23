package com.gurkensalat.osm.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OsmPlaceTest
{
    OsmNode node;

    OsmPlace testable;

    @Before
    public void setUp()
    {
    }

    @Test
    public void testCreationFromNodeGermeringMosque()
    {
        node = createGermeringMosque();
        testable = new OsmPlace(node);
        assertNotNull(testable);
        assertNotNull(testable.getAddress());
        assertEquals("Germering", testable.getAddress().getCity());
    }

    @Test
    public void testCopyToFromNodeGermeringMosque()
    {
        node = createGermeringMosque();

        OsmPlace source = new OsmPlace(node);
        OsmPlace testable = new OsmPlace();
        assertNotNull(source);

        source.copyTo(testable);

        assertNotNull(testable);
        assertNotNull(testable.getAddress());
        assertEquals("Germering", testable.getAddress().getCity());
        assertNotNull(testable.getContact());
    }

    @Test
    public void testCreationFromNodeHannoverMosque()
    {
        node = createHannoverMosque();
        testable = new OsmPlace(node);
        assertNotNull(testable);
        assertNotNull(testable.getAddress());
        assertNotNull(testable.getContact());
    }

    @Test
    public void testCopyToFromNodeHannoverMosque()
    {
        node = createHannoverMosque();

        OsmPlace source = new OsmPlace(node);
        OsmPlace testable = new OsmPlace();
        assertNotNull(source);

        source.copyTo(testable);

        assertNotNull(testable);
        assertNotNull(testable.getAddress());
        assertNotNull(testable.getContact());
        assertEquals("4711", testable.getContact().getPhone());
        assertEquals("7777", testable.getContact().getFax());
        assertEquals("http://far-far.away/", testable.getContact().getWebsite());
        assertEquals("someone@far-far.away", testable.getContact().getEmail());

    }

    @Test
    public void testCreationFromNodeAnkaraCity()
    {
        node = createAnkaraCity();
        testable = new OsmPlace(node);
        assertNotNull(testable);
        assertNotNull(testable.getAddress());
        assertEquals("Ankara", testable.getName());
        assertNotNull(testable.getContact());
    }

    @Test
    public void testCopyToFromNodeAnkaraCity()
    {
        node = createAnkaraCity();

        OsmPlace source = new OsmPlace(node);
        OsmPlace testable = new OsmPlace();
        assertNotNull(source);

        source.copyTo(testable);

        assertNotNull(testable);
        assertEquals("Ankara", testable.getName());
        assertNotNull(testable.getAddress());
        assertNotNull(testable.getContact());
    }

    private OsmNode createGermeringMosque()
    {
        OsmNode node = new OsmNode();
        node.setLat(48.1364000);
        node.setLon(11.3872928);
        node.setTags(new ArrayList<OsmNodeTag>());
        node.getTags().add(createTag("addr:city", "Germering"));
        node.getTags().add(createTag("addr:housenumber", "13a"));
        node.getTags().add(createTag("addr:postcode", "82110"));
        node.getTags().add(createTag("addr:street", "Münchener Straße"));

        return node;
    }

    private OsmNode createHannoverMosque()
    {
        OsmNode node = new OsmNode();
        node.setLat(-42);
        node.setLon(-42);
        node.setTags(new ArrayList<OsmNodeTag>());
        node.getTags().add(createTag("contact:phone", "4711"));
        node.getTags().add(createTag("contact:fax", "7777"));
        node.getTags().add(createTag("contact:website", "http://far-far.away/"));
        node.getTags().add(createTag("contact:email", "someone@far-far.away"));

        return node;
    }

    private OsmNode createAnkaraCity()
    {
        OsmNode node = new OsmNode();
        node.setLat(39.9272322);
        node.setLon(32.8519769);
        node.setTags(new ArrayList<OsmNodeTag>());
        node.getTags().add(createTag("capital", "yes"));
        node.getTags().add(createTag("is_in", "Turkey"));
        node.getTags().add(createTag("name", "Ankara"));
        node.getTags().add(createTag("place", "city"));

        return node;
    }

    private OsmNodeTag createTag(String key, String value)
    {
        OsmNodeTag tag = new OsmNodeTag();

        tag.setKey(key);
        tag.setValue(value);

        return tag;
    }
}
