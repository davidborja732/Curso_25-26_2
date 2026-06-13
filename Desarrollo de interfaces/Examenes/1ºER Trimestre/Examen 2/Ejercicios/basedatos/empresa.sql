-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS empresa;

-- Crear la base de datos
CREATE DATABASE empresa
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;

-- Seleccionar la base de datos
USE empresa;

SET NAMES utf8mb4;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET unique_checks = 0;

-- ------------------------------------------------------
-- Tabla: departamentos
-- ------------------------------------------------------

DROP TABLE IF EXISTS `departamentos`;
CREATE TABLE `departamentos` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Departamento` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `departamentos` (`Id`,`Departamento`) VALUES
(1,'Administración'),
(2,'Contabilidad'),
(3,'Producción'),
(4,'Comercial');

-- ------------------------------------------------------
-- Tabla: empleados
-- ------------------------------------------------------

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `FechaNac` datetime NOT NULL,
  `Sexo` tinyint NOT NULL,
  `Departamento` int NOT NULL,
  `Salario` double NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  CONSTRAINT fk_departamento FOREIGN KEY (`Departamento`) REFERENCES `departamentos`(`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `empleados` (`Id`,`Nombre`,`FechaNac`,`Sexo`,`Departamento`,`Salario`) VALUES
(1,'Marta Soler Esteban','1995-08-21 00:00:00',1,1,1500),
(2,'Pedro García Díaz','1982-05-10 00:00:00',0,2,1400),
(3,'Ignacio Pérez Brul','1995-08-21 00:00:00',0,3,1300),
(4,'Esteban Martín González','1991-06-23 00:00:00',0,4,1700);

SET foreign_key_checks = 1;
SET unique_checks = 1;
