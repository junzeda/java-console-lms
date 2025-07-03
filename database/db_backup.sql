CREATE DATABASE  IF NOT EXISTS `library_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_db`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: library_db
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'jsaceda','jsaceda');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `author_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` varchar(100) NOT NULL,
  `nationality` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1013 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1001,'Jane','Austen','1775-12-16','British','2025-06-29 15:39:09','2025-06-29 15:39:09',0),(1002,'Mark','Twain','1835-11-30','American','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1003,'Leo','Tolstoy','1828-09-09','Russian','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1004,'George','Orwell','1903-06-25','British','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1005,'Haruki','Murakami','1949-01-12','Japanese','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1006,'Isabel','Allende','1942-08-02','Chilean','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1007,'Chinua','Achebe','1930-11-16','Nigerian','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1008,'Franz','Kafka','1883-07-03','Austrian','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1009,'J.K.','Rowling','1965-07-31','British','2025-06-29 15:39:52','2025-06-29 15:39:52',0),(1010,'Gabriel','García Márquez','1927-03-06','Colombian','2025-06-29 15:39:52','2025-06-29 07:49:04',0),(1011,'Junelvin','Saceda','1997-06-02','Filipino','2025-07-02 17:38:50','2025-07-02 17:38:50',1),(1012,'Zia','Gra','1997-06-02','Filipino','2025-07-02 17:40:40','2025-07-02 17:41:18',1);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_categories`
--

DROP TABLE IF EXISTS `book_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_categories` (
  `book_category_id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`book_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_categories`
--

LOCK TABLES `book_categories` WRITE;
/*!40000 ALTER TABLE `book_categories` DISABLE KEYS */;
INSERT INTO `book_categories` VALUES (1002,'Fiction','2025-06-29 20:03:24','2025-06-29 20:03:24'),(1003,'Non-Fiction','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1004,'Science Fiction','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1005,'Fantasy','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1006,'Biography','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1007,'Mystery','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1008,'Romance','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1009,'History','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1010,'Self-Help','2025-06-29 20:03:37','2025-06-29 20:03:37'),(1011,'Children\'s Books','2025-06-29 20:03:37','2025-06-29 20:03:37');
/*!40000 ALTER TABLE `book_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `author_id` int NOT NULL,
  `publisher_id` int NOT NULL,
  `isbn` varchar(15) NOT NULL,
  `category_id` int NOT NULL,
  `publication_year` int NOT NULL,
  `page_count` int NOT NULL,
  `language` varchar(45) NOT NULL,
  `available_copies` int NOT NULL,
  `issued_books` int NOT NULL,
  `is_available` tinyint NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`book_id`),
  KEY `author_id_idx` (`author_id`),
  KEY `publisher_id_idx` (`publisher_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `authors` (`author_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `book_categories` (`book_category_id`),
  CONSTRAINT `publisher_id` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1022 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1001,'Pride and Prejudice',1001,1002,'9780000000001',1008,1813,432,'English',12,3,1,'2025-06-29 21:02:11','2025-06-29 21:02:11',0),(1002,'Adventures of Huckleberry Finn',1002,1001,'9780000000002',1002,1884,366,'English',10,2,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1003,'War and Peace',1003,1007,'9780000000003',1009,1869,1225,'English',8,5,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1004,'1984',1004,1003,'9780000000004',1004,1949,328,'English',15,7,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1005,'Norwegian Wood',1005,1008,'9780000000005',1002,1987,296,'English',5,5,0,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1006,'The House of the Spirits',1006,1004,'9780000000006',1007,1982,433,'English',7,3,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1007,'Things Fall Apart',1007,1001,'9780000000007',1002,1958,209,'English',6,1,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1008,'The Trial',1008,1009,'9780000000008',1007,1925,255,'English',4,0,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1009,'Harry Potter and the Sorcerer\'s Stone',1009,1005,'9780000000009',1011,1997,309,'English',20,15,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1010,'One Hundred Years of Solitude',1010,1010,'9780000000010',1002,1967,417,'English',9,2,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1011,'Emma',1001,1003,'9780000000011',1008,1815,474,'English',6,1,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1012,'The Adventures of Tom Sawyer',1002,1002,'9780000000012',1011,1876,274,'English',10,0,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1013,'Ano daw',1004,1005,'9780000000013',1005,193,32,'Englihs',23,4,1,'2025-06-29 21:02:24','2025-07-02 18:06:10',1),(1014,'Animal Farm',1004,1006,'9780000000014',1002,1945,112,'English',12,6,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1015,'Kafka on the Shore',1005,1008,'9780000000015',1005,2002,505,'English',3,3,0,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1016,'Daughter of Fortune',1006,1004,'9780000000016',1002,1999,432,'English',4,1,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1017,'No Longer at Ease',1007,1007,'9780000000017',1002,1960,170,'English',8,2,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1018,'The Metamorphosis',1008,1009,'9780000000018',1004,1915,201,'English',9,4,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1019,'Harry Potter and the Chamber of Secrets',1009,1005,'9780000000019',1005,1998,341,'English',18,10,1,'2025-06-29 21:02:24','2025-06-29 21:02:24',0),(1020,'Love in the Time of Cholera',1009,1002,'9780000000020',1004,1982,300,'English',20,3,1,'2025-06-29 21:02:24','2025-06-29 17:12:57',0),(1021,'The book',1006,1005,'12309123',1009,1998,23,'English',15,0,0,'2025-07-02 18:03:58','2025-07-02 18:03:58',0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow_books`
--

DROP TABLE IF EXISTS `borrow_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow_books` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(45) NOT NULL,
  `book_id` int NOT NULL,
  `borrow_date` datetime DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `is_returned` tinyint NOT NULL DEFAULT '0',
  `is_late` tinyint NOT NULL DEFAULT '0',
  `fine_amount` double NOT NULL DEFAULT '0',
  `request_status` varchar(20) NOT NULL,
  `remarks` text,
  `requested_at` datetime NOT NULL,
  `is_archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`borrow_id`),
  KEY `student_id_idx` (`student_id`),
  KEY `book_id_idx` (`book_id`),
  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow_books`
--

LOCK TABLES `borrow_books` WRITE;
/*!40000 ALTER TABLE `borrow_books` DISABLE KEYS */;
INSERT INTO `borrow_books` VALUES (1001,'123456789',1008,'2025-07-02 01:13:16','2025-07-03 01:13:16','2025-07-03 01:13:16',0,0,0,'accepted','returned in good condition','2025-07-01 07:45:36',0),(1002,'123456789',1017,'2025-07-02 02:02:19','2025-07-04 02:02:19','2025-07-03 01:13:16',0,0,0,'accepted','returned in good condition','2025-07-02 02:01:32',0),(1003,'123456789',1012,'2025-07-02 06:06:58','2025-06-25 06:06:58','2025-07-02 06:57:34',0,0,370,'accepted','Damage','2025-07-02 06:06:22',0),(1004,'123456789',1019,'2025-07-02 06:40:28','2025-07-07 06:40:28','2025-07-02 06:57:56',0,0,600,'completed','lost','2025-07-02 06:39:57',0),(1005,'123456789',1018,'2025-07-02 18:09:49','2025-07-07 18:09:49',NULL,0,0,0,'accepted',NULL,'2025-07-02 11:08:37',0),(1006,'123456789',1020,NULL,NULL,NULL,0,0,0,'pending',NULL,'2025-07-02 14:15:58',0),(1007,'123456789',1019,NULL,NULL,NULL,0,0,0,'pending',NULL,'2025-07-02 16:47:51',0);
/*!40000 ALTER TABLE `borrow_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishers` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `address` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1001,'HarperCollins','contact@harpercollins.com','+1-212-207-7000','195 Broadway, New York, NY, USA','2025-06-29 15:43:12','2025-06-29 15:43:12',0),(1002,'Penguin Random House','info@penguinrandomhouse.com','+1-212-782-9000','1745 Broadway, New York, NY, USA','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1003,'Macmillan Publishers','support@macmillan.com','+44-20-7845-3000','4 Crinan Street, London, UK','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1004,'Simon & Schuster','help@simonandschuster.com','+1-800-223-2336','1230 Avenue of the Americas, New York, NY, USA','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1005,'Scholastic Inc.','service@scholastic.com','+1-800-724-6527','557 Broadway, New York, NY, USA','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1006,'Hachette Book Group','contact@hbgusa.com','+1-800-759-0190','1290 Avenue of the Americas, New York, NY, USA','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1007,'Oxford University Press','info@oup.com','+44-1865-556767','Great Clarendon Street, Oxford, UK','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1008,'Pearson Education','support@pearson.com','+44-20-7010-2000','80 Strand, London, UK','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1009,'Wiley','info@wiley.com','+1-877-762-2974','111 River Street, Hoboken, NJ, USA','2025-06-29 15:43:26','2025-06-29 15:43:26',0),(1010,'Springer Nature','contact@springernature.com','+49-6221-4870','Heidelberg, Germany','2025-06-29 15:43:26','2025-06-29 15:43:26',0);
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `db_id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`db_id`),
  UNIQUE KEY `student_id_UNIQUE` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'123456789','Junelvin','Saceda na','123','09087895649','2025-06-22 08:38:55','2025-07-02 16:51:36',0),(2,'123456735','Kalixta','Grazia','6735Grazia','09391235434','2025-06-23 03:28:00','2025-06-23 03:28:00',1),(3,'1234534','Bianca','Ronan','4534Ronan','09298475421','2025-06-23 08:56:42','2025-06-23 08:56:42',0),(4,'7654321','zIA','Grazia','4321Saceda','09391235438','2025-06-23 09:17:04','2025-06-23 09:30:40',1),(5,'7898234','Kal','Zia','8234Grazia','09087895649','2025-06-23 10:36:25','2025-06-26 08:52:37',1),(6,'987654321','Kalix','Grazia','4321Grazia','090878945639','2025-06-26 08:57:21','2025-06-26 08:57:41',1);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-03  9:05:05
