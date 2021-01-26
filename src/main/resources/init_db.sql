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

CREATE TABLE `drivers` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  `license_number` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  `login` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  `password` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE KEY `id` USING BTREE (`id`)
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'
;

CREATE TABLE `cars` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(225) COLLATE utf8_general_ci NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  `manufacturer_id` BIGINT(11) NOT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE KEY `id` USING BTREE (`id`),
  KEY `manufacturer_id` USING BTREE (`manufacturer_id`),
  CONSTRAINT `manufacturer_id` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`)
) ENGINE=InnoDB
AUTO_INCREMENT=1 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'
;

CREATE TABLE `cars_drivers` (
  `driver_id` BIGINT(11) NOT NULL,
  `car_id` BIGINT(11) NOT NULL,
  KEY `driver_id` USING BTREE (`driver_id`),
  KEY `car_id` USING BTREE (`car_id`),
  CONSTRAINT `car_id` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
  CONSTRAINT `driver_id` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`)
) ENGINE=InnoDB
CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'
;
