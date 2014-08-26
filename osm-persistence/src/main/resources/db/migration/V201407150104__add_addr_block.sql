ALTER TABLE `osm_places` add `addr_city`        varchar(80) DEFAULT NULL;
ALTER TABLE `osm_places` add `addr_country`     varchar(80) DEFAULT NULL;
ALTER TABLE `osm_places` add `addr_housenumber` varchar(20) DEFAULT NULL;
ALTER TABLE `osm_places` add `addr_postcode`    varchar(10) DEFAULT NULL;
ALTER TABLE `osm_places` add `addr_state`       varchar(80) DEFAULT NULL;
ALTER TABLE `osm_places` add `addr_street`      varchar(80) DEFAULT NULL;
