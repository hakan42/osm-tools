package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmNode;
import com.gurkensalat.osm.entity.OsmRoot;
import com.gurkensalat.osm.entity.OsmTag;
import org.apache.commons.digester3.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.closeQuietly;

@Component
public class OsmRepositoryImpl implements OsmRepository
{
    private final static Logger LOGGER = LoggerFactory.getLogger(OsmRepositoryImpl.class);

    public OsmRoot parse(File resourceFile)
    {
        OsmRoot root = new OsmRoot();

        try
        {
            FileInputStream fis = new FileInputStream(resourceFile);
            try
            {
                root = parse(fis);
            }
            finally
            {
                closeQuietly(fis);
            }

            LOGGER.info("Parsed Root: {}", root);
        }
        catch (IOException e)
        {
            LOGGER.error("While parsing OSM XML", e);
            LOGGER.info("  were trying to read {}", resourceFile);
        }

        return root;
    }

    public OsmRoot parse(InputStream inputStream)
    {
        OsmRoot root = new OsmRoot();
        try
        {
            Digester digester = createOsmPlanetDigester();

            root = digester.parse(inputStream);
        }
        catch (IOException e)
        {
            LOGGER.error("While parsing OSM XML", e);
        }
        catch (SAXException e)
        {
            LOGGER.error("While parsing OSM XML", e);
        }

        return root;
    }

    private static Digester createOsmPlanetDigester()
    {
        Digester digester = new Digester();

        digester.addObjectCreate("osm", OsmRoot.class);

        digester.addObjectCreate("osm/node", OsmNode.class);
        digester.addSetProperties("osm/node");
        digester.addSetNext("osm/node", "addNode");

        digester.addObjectCreate("osm/node/tag", OsmTag.class);
        digester.addSetProperties("osm/node/tag");
        digester.addSetNext("osm/node/tag", "addTag");

        return digester;
    }
}
