-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: ventas
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
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `Nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Categoría` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `PrecioUnidad` double NOT NULL,
  `CantidadVendida` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES ('Café arabia','Cafés',3,30),('Café Colombia','Cafés',2,28),('Queso Gruyere','Quesos',3,39),('Queso azul','Quesos',1,25),('Queso Idaizabal','Quesos',4,19),('Galletas Doradas','Dulces',1,59),('Bombones','Dulces',8,34),('Barquillos','Dulces',0,32),('Arroz Salvaje','Arroces',2,32),('Arroz redondo','Arroces',1,45),('Rosquillas','Dulces',1.8,32),('Coquitos','Dulces',2.3,31),('Atún','Conservas',3.5,18),('Sardinas','Conservas',3.75,36),('Navajas','Conservas',4.7,30),('Boquerones','Conservas',4.8,35),('Café arabia','Cafés',3,30),('Café Colombia','Cafés',2,28),('Queso Gruyere','Quesos',3,39),('Queso azul','Quesos',1,25),('Queso Idaizabal','Quesos',4,19),('Galletas Doradas','Dulces',1,59),('Bombones','Dulces',8,34),('Barquillos','Dulces',0,32),('Arroz Salvaje','Arroces',2,32),('Arroz redondo','Arroces',1,45),('Rosquillas','Dulces',1.8,32),('Coquitos','Dulces',2.3,31),('Atún','Conservas',3.5,18),('Sardinas','Conservas',3.75,36),('Navajas','Conservas',4.7,30),('Boquerones','Conservas',4.8,35);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-30 10:18:52
