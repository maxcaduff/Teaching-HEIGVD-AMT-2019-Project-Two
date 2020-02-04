SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `authApi`;
CREATE DATABASE IF NOT EXISTS `bookingApi`;

USE authApi;

CREATE USER `authapijpa`@`%` IDENTIFIED BY 'w0nderfulPa55';
GRANT select, insert, delete, update ON * to `authapijpa`@`%`;


DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO User (email, first_name, last_name, password) VALUES ('max@heig.ch', 'Max', 'Caduff', '1234'), ('bob@dupont.ch', 'Bob', 'Dupont', '5678');




USE bookingApi;

CREATE USER `bookingapijpa`@`%` IDENTIFIED BY 'be77erPa$$';
GRANT select, insert, delete, update ON * to `bookingapijpa`@`%`;

DROP TABLE IF EXISTS `Booking`;
DROP TABLE IF EXISTS `Activity`;
DROP TABLE IF EXISTS `Location`;

CREATE TABLE `Activity` (
  `id` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `max_places` integer NOT NULL,
  `description` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Location` (
  `id` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Booking` (
  `id` integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user` varchar(255) NOT NULL,
  `location` integer NOT NULL,
  `activity` integer NOT NULL,
  `date` date NOT NULL,
  `nb_places` integer NOT NULL,
  FOREIGN KEY (location) REFERENCES Location(id) ON DELETE CASCADE,
  FOREIGN KEY (activity) REFERENCES Activity(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `email` varchar(255) NOT NULL PRIMARY KEY
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO Activity (name, max_places, description) VALUES ( 'Rafting', 12, 'come try rafting! Bring your swimwear.'), ('Paint ball', 40, 'Paintball with all equipment provided'), ('orienteering', 50, 'be the first!');

INSERT INTO Location (name, address) VALUES ('Green Valley', 'leaf road 12, 4242 Tree City'), ('Nature sports', 'fit road 25, 1000 Main City');

INSERT INTO Admin (email) VALUES ('max@heig.ch');


COMMIT;
