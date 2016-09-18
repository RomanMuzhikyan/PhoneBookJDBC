/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.14-log : Database - phonebook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`phonebook` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `phonebook`;

/*Table structure for table `friends` */

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `friends_ibfk_1` (`user_id`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `friends` */

insert  into `friends`(`id`,`name`,`user_id`) values (9,'Levon',14);

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(25) COLLATE utf8_bin NOT NULL,
  `discription` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `messages` */

insert  into `messages`(`id`,`message`,`discription`) values (1,'welcome','Welcome PhoneBook Application\\n\r\nfor Sign Up \"1\" or Sign In \"2\"'),(2,'invalid.command','Invalid Command choose \"1\" for creating accaunt\\n\"2\" for Sign In\\n'),(3,'class.not.found','Class Not Found\\n'),(4,'provide.firstName','Provide Your FirstName\\n'),(5,'provide.lastName','Provide Your LastName\\n'),(6,'provide.email','Provide Your Email like this - \"mujikyan@gmail.com\"\\n'),(7,'account.created','Your Accaunt Succesfully created\\n\"1\" Sign Up new or \"2\" Sign In\\n'),(8,'insertion.error','Insertion Error\\n'),(9,'logged.error','No Such Accaunt\\n'),(10,'update.message','Your Updating is Done\\n'),(11,'update.error','Update Error No Mutching'),(12,'sign.in.up','Please write down one of this commands for Sign Up-\"a\" or Sign In \"b\"\\n'),(13,'wrong.add','wrong firstName, lastName or email\\nTry again\\n\r\n'),(14,'logged.successfully','You are successfully logged in\\n'),(15,'commands.number','Enter \"1\" for \"Add Tel. Number Enter \"2\" for \"Show Tel. Numbers\" Enter \"3\" for for \"Add Friend\"\r\nEnter \"4\" \"Show friends\"\\n>>Enter \"5\" for \"Delete Friend\" Enter \"6\" for \"Delete Number \r\nEnter \"7\" \"Delete User\" Enter \"8\" for \"Update User\" Enter \"9\" for \"Update Number\"\r\nEnter \"10\" \"Sign out\" Enter \"e\" for \"exit\" enter \"h\" help \r\n'),(16,'wrong.user.ans','Your username or password is incorrect please \"Sign In\" again\\n'),(17,'lock.account','Please try again later. You have been locked.\\n'),(18,'help.text','\\\"Sign Up\\\" - \\\"Create new account\"\\n\\\"Sign In\\\" - \\\"Log in with your account\"\\n\\\r\n  \\\"Add Tel. Number\\\" - \\\"Add new telephone number\"\\n\\\r\n  \\\"Show Tel. Numbers\\\" - \\\"Show your telephone numbers list\"\\n\\\r\n  \\\"Sign Out\\\" - \\\"Log out from your account\"\\n\\\r\n  \\\"Exit\\\" - \\\"Exit program\"\\n\\\r\n  \\\"Help\\\" - \\\"Commands description\"\\n'),(19,'exit.mes','Exit Message\\n'),(20,'sign.out','Sign Out/n'),(21,'provide.friend','Provide Friend name\\n'),(22,'no.friend','No such friendName\\n'),(23,'friend.created','Your Friend created successfully\\n'),(25,'bot.message','You are a robot\r\n'),(26,'no.friend.name','This User have not Friends'),(27,'provide.friend.to.delete','Provide friend name for deleting\r\n'),(28,'friend.delete','is delete successfuly\r\n'),(29,'incorrect.friend.name','No such friend Name is 0 or name is incorrect'),(30,'user.delete','User successfuly deleted'),(31,'provide.new.firstName','Provide new firstName'),(32,'provide.new.lastName','Provide new lastName'),(33,'provide.new.email','Provide new email'),(34,'provide.new.friendName','Provide new friendName'),(35,'edit.incorrect','Edit not created'),(36,'edit.created','Your User updated successfully'),(37,'provide.phone.number','Provide phoneNumber'),(38,'tel.created','Number succesfully created'),(39,'tel.no.created','Telephone no created'),(40,'no.number.list','User have not Number\r\n'),(41,'number.deleted','Number successfully deleted'),(42,'provide.number.to.delete','Provide number to delete'),(43,'provide.new.telNum','Provide new Number'),(44,'provide.old.telNum','Provide what number to update\r\n'),(45,'number.edited','Number successfully edited');

/*Table structure for table `phonenumbers` */

DROP TABLE IF EXISTS `phonenumbers`;

CREATE TABLE `phonenumbers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_type` enum('Home','Mobile') COLLATE utf8_bin NOT NULL,
  `number` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `phonenumbers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `phonenumbers` */

insert  into `phonenumbers`(`id`,`phone_type`,`number`,`user_id`) values (2,'Mobile','+374 94101012',3);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `last_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`first_name`,`last_name`,`email`) values (2,'Artur','Ayvazyan','artur@gmail.com'),(3,'Roman','Muzhikyan','mujikyan@gmail.com'),(4,'gev','tamazyan','gev@tamaz.com'),(5,'Narek','Nazaryan','nazaryan@gmail.com'),(6,'Sevak','Nazaryan','sevomessi@gmail.com'),(7,'gev','babayan','gev@mail.ru'),(8,'Levon','Babayan','lev@mail.ru'),(9,'Nar','sev','f@mail.ru'),(10,'Artyom','Agajanyan','tyom@rambler.ru'),(11,'Hovo','Vardanyan','hov@mail.com'),(12,'Hayko','ghazaryan','hayk@mail.ru'),(13,'Axas','Enoqyan','enoq@mail.ru'),(14,'aa','aa','aa'),(15,'gev','tam','kam');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
