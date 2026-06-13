-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: productos
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Producto` varchar(45) DEFAULT NULL,
  `Precio` double DEFAULT NULL,
  `Categoria` int DEFAULT NULL,
  `Marca` int DEFAULT NULL,
  `Wifi` tinyint DEFAULT NULL,
  `Bluetooth` tinyint DEFAULT NULL,
  `NFC` tinyint DEFAULT NULL,
  `5G` tinyint DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Galaxy 25S',360,1,1,1,1,0,0,1),(2,'Idea Tab',169,2,5,1,1,1,0,0),(3,'Galaxy Tab 9',370,2,1,1,1,0,0,1),(4,'Redmi Pad 2',105,2,3,1,0,0,0,0),(5,'Galaxy Tab A11',690,2,1,1,1,1,1,0),(6,'ThinkPad T14s',1500,4,5,1,0,0,0,0),(7,'Yoga Slim 7i',2100,4,5,1,0,0,0,0),(8,'Idea Tab Pro',250,2,5,1,1,0,0,1),(9,'Think Center Neo 50q',710,3,5,0,0,0,0,1),(10,'Think Center Neo 70q',890,3,5,0,0,0,0,1),(11,'Pura Ultra 80',1490,1,4,1,1,1,1,0),(12,'Pura Pro 80',990,1,4,1,1,1,1,1),(13,'Vivobook15',540,4,6,1,0,0,0,0),(14,'Chrombook',650,4,6,1,0,0,0,1),(15,'15 N305 i3 ',400,4,2,1,0,0,0,1),(16,'Gaming Victus',780,4,2,1,0,0,0,0),(17,'kjlsdfj',140,1,4,0,0,0,0,1),(18,'Tab',360,1,3,0,0,0,0,1),(19,'Redmi 2',140,4,6,0,0,0,0,1),(20,'Prueba',150,4,5,0,0,0,0,1),(21,'Preuba3',190,2,4,0,0,0,0,1),(22,'Prueba 5',290,2,5,1,1,1,1,0),(23,'Prueba 6',560,4,6,1,1,1,1,1),(24,'prueba 6',130,4,6,1,0,0,0,1),(25,'Prueba 7',180,3,3,1,1,0,0,0),(26,'Prueba 8',190,4,4,0,0,1,1,1),(27,'Prueba 9',130,1,4,1,1,0,0,0);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-30 20:21:40
