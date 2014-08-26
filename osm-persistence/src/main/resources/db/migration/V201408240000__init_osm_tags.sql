CREATE TABLE `osm_tags`
(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,

  `p_id` bigint(20) DEFAULT NULL,
  `p_table` varchar(80) DEFAULT NULL,
  `d_key` varchar(30) DEFAULT NULL,
  `d_val` varchar(80) DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,

  PRIMARY KEY(`id`)
)
DEFAULT CHARSET=utf8;
