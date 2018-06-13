show databases;
create database school;
use school;

CREATE TABLE `estudiante` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `profesor` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `curso` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `profesor_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `profesor_id` FOREIGN KEY (`profesor_id`) REFERENCES profesor (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE `alumno` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `curso_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE `cursoxestudiante` (
  `id` MEDIUMINT(10) NOT NULL AUTO_INCREMENT,
  `estudiante_id` int(10) NOT NULL,
  `curso_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `curso_id_fk` FOREIGN KEY (`curso_id`) REFERENCES curso (`id`),
  CONSTRAINT `estudiante_id_fk` FOREIGN KEY (`estudiante_id`) REFERENCES estudiante (`id`)
);

ALTER TABLE Orders ADD CONSTRAINT FK_PersonOrder FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);