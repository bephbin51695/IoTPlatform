-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: ssm
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `currentTemperature` char(10) DEFAULT NULL,
                          `ownerId` int DEFAULT NULL,
                          `room` varchar(40) DEFAULT NULL,
                          `deviceId` varchar(40) DEFAULT NULL,
                          `targetTemperature` char(10) DEFAULT NULL,
                          `create_time` datetime DEFAULT now(),
                          `update_time` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `deviceId` (`deviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,NULL,1,'bath','bsjhf78','17'),(2,NULL,2,'12','cxvv87','29'),(3,NULL,2,'32','lklk898','45'),(4,NULL,3,'kit','njcsn999','12'),(6,'30',1,'666','23u9ujffvcw','25'),(7,NULL,1,'aa','966befef','26'),(11,'30',2,'实机','esp1111','30'),(33,NULL,2,'12','55',NULL),(34,NULL,NULL,'12','11',NULL),(35,NULL,NULL,'12','22',NULL),(36,NULL,NULL,'12','33',NULL);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(20) NOT NULL,
                        `password` varchar(40) NOT NULL,
                        `email` varchar(40) DEFAULT NULL,
                        `phone` char(11) DEFAULT NULL,
                        `nickname` varchar(40) DEFAULT NULL,
                        `create_time` datetime DEFAULT now(),
                        `update_time` datetime DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','1234',NULL,'13888888888','超级管理员'),(2,'qwe','666','123','13000000000','普通用户'),(3,'------------','_cn','123@123.com',NULL,'mom'),(4,'asdhj213','12345','2327637@qq.com','12334564535','1'),(11,'mk33gds','123','123@123.com',NULL,NULL),(13,'468mm','123','123@123.com',NULL,'???'),(36,'12x','123','2133@qq.com','123141',NULL),(57,'123','123','1234','1234','chen'),(87,'213','123',NULL,NULL,'333'),(88,'2131','123',NULL,NULL,'dsd'),(92,'1232','23',NULL,NULL,'eer'),(93,'sghrevb','123','123','123','456'),(94,'qwrtyu','qerty','qwrty','asffgh','qwert'),(95,'cyhcvjbj','hccghvvu','@ghchc','g@hchc','cygchchv'),(96,'cgjdf','hccghvvu','@ghchc','g@hchc','cygchchv'),(97,'dsff','123',NULL,NULL,'12312'),(98,'sndnfn','12313',NULL,NULL,'djd');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-14 15:44:18
