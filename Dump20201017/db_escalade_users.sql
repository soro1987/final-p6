-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: db_escalade
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '','soro','amet','soroamt@gmail.com','1234',638468361,'soro2305',_binary '\0'),(2,_binary '','laby',NULL,'camara@gmail.com','1111',658796584,'camara',_binary '\0'),(3,_binary '','soba','baba','soroamt@yahoo.com','1234',6384683,'soso',_binary '\0'),(4,_binary '','soba','ba','soroamt@ya.com','1234',60384683,'so',_binary '\0'),(5,_binary '','soba','ba','soroamt@ya.com','1234',60384683,'Mathieu',_binary '\0'),(6,_binary '\0','soro','amet','ergr@gghgh.com','fhfhhffgfgf',638468361,'jack',_binary '\0'),(7,_binary '\0','Laby Damaro','Camara','ldamaro98@gmail.com','1234',563522750,'ldamaro98',_binary '\0'),(8,_binary '\0','Polo','toto','fpsqg2305@gmail.com','45879',638468361,'Marco',_binary '\0'),(9,_binary '\0','kiop','kiopder','fpsqg2305@gmail.com','473424',457552220,'derik',_binary '\0'),(39,_binary '\0','martinez','mathieu','mat@gmail.com','4353535353',63598754,'mathieu23',_binary '\0'),(40,_binary '\0',NULL,NULL,NULL,NULL,0,NULL,_binary '\0'),(41,_binary '\0','sorofff','ametffff','llllll@gmail.com','123456',5550,'mathieu23ffddf',_binary '\0'),(74,_binary '\0','soso','soro','soso@gmail.com','MotDePasse123',2865418,'soso23',_binary '\0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-17 21:21:36
