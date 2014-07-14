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

@Component
public class OsmRepositoryImpl implements OsmRepository
{
    private final static Logger LOGGER = LoggerFactory.getLogger(OsmRepositoryImpl.class);

    public OsmRoot parse(File resourceFile)
    {
        // Just to be NPE-safe
        OsmRoot root = new OsmRoot();

        try
        {
            Digester digester = createOsmPlanetDigester();
            LOGGER.info("Digester is: {}", digester);

            // TODO use Apache IOutils here
            FileInputStream fis = new FileInputStream(resourceFile);
            root = digester.parse(fis);
            fis.close();

            LOGGER.info("Parsed Root: {}", root);
        }
        catch (IOException e)
        {
            LOGGER.error("While parsing OSM XML", e);
            LOGGER.info("  were trying to read {}", resourceFile);
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
        // digester.addSetNext("osm/node", "addNode", OsmRoot.class.getCanonicalName());
        digester.addSetNext("osm/node", "addNode");

        digester.addObjectCreate("osm/node/tag", OsmTag.class);
        digester.addSetProperties("osm/node/tag");
        // digester.addSetNext("osm/node/tag", "addTag", OsmTag.class.getCanonicalName());
        digester.addSetNext("osm/node/tag", "addTag");

        return digester;
    }
}
