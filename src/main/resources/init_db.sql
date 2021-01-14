CREATE DATABASE `dao3_db`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

CREATE TABLE `manufacturer` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  `country` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE=InnoDB
AUTO_INCREMENT=10 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'
;


INSERT INTO `manufacturer`
(NAME, COUNTRY) VALUES ("Siat","Italy"), ("WW", "Germany"),("Honda", "Japan");

