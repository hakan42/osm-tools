ALTER TABLE `OSM_PLACES` add `ADDR_CITY`        varchar(80) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `ADDR_COUNTRY`     varchar(80) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `ADDR_HOUSENUMBER` varchar(20) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `ADDR_POSTCODE`    varchar(10) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `ADDR_STATE`       varchar(80) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `ADDR_STREET`      varchar(80) DEFAULT NULL;