CREATE TABLE `osm_places`
(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,

  PRIMARY KEY(`id`)
)
DEFAULT CHARSET=utf8;


ALTER TABLE `osm_places` add `lat` double DEFAULT NULL;
ALTER TABLE `osm_places` add `lon` double DEFAULT NULL;
ALTER TABLE `osm_places` add `name` varchar(80) DEFAULT NULL;
ALTER TABLE `osm_places` add `type` varchar(20) DEFAULT NULL;
ALTER TABLE `osm_places` add `d_key` varchar(80) DEFAULT NULL;
ALTER TABLE `osm_places` add `d_code` varchar(80) DEFAULT NULL;
ALTER TABLE `osm_places` add `valid` bit(1) DEFAULT NULL;
