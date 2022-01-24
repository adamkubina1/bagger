# Bagger
Javafx application with database for project issues managment.
App includes login system, different teamroles with various privileges and various operations (insertion, update, deletion) of projects, issues and comments.

Created as final assignment for Software engineering course at VSE.

DDL for MySQL database which is this app based on.

```
/* ---------------------------------------------------- */ /* Generated by Enterprise Architect Version 15.1 */ /* Created On : 19-lis-2021 21:17:24 */ /* DBMS : MySql */ /* ---------------------------------------------------- */ SET FOREIGN_KEY_CHECKS=0 ; /* Drop Tables */ DROP TABLE IF EXISTS `Comment` CASCADE ; DROP TABLE IF EXISTS `Employee` CASCADE ; DROP TABLE IF EXISTS `Issue` CASCADE ; DROP TABLE IF EXISTS `Login_Credentials` CASCADE ; DROP TABLE IF EXISTS `Project` CASCADE ; DROP TABLE IF EXISTS `Team` CASCADE ; DROP TABLE IF EXISTS `Team_Project_Relationship` CASCADE ; /* Create Tables */ CREATE TABLE `Comment`( `Id_Comment` INT NOT NULL AUTO_INCREMENT, `Id_Employee` INT NOT NULL, `Id_Issue` INT NOT NULL, `Message` VARCHAR(255) NOT NULL, CONSTRAINT `PK_Comment` PRIMARY KEY (`Id_Comment` ASC)) ; CREATE TABLE `Employee` ( `Id_Employee` INT NOT NULL AUTO_INCREMENT, `Id_Team` INT NOT NULL, `Id_Login_Credentials` INT NOT NULL, `Name` VARCHAR(50) NOT NULL, `Surname` VARCHAR(50) NOT NULL, `Position` VARCHAR(255) NULL, CONSTRAINT `PK_Employee` PRIMARY KEY (`Id_Employee` ASC) ) ; CREATE TABLE `Issue` ( `Id_Issue` INT NOT NULL AUTO_INCREMENT, `Id_Project` INT NOT NULL, `Id_Creater` INT NOT NULL, `Id_Closer` INT NOT NULL, `Issue_Title` VARCHAR(50) NOT NULL, `Issue_Description` VARCHAR(255) NOT NULL, `Start_Date` DATE NULL, `End_Date` DATE NULL, `Importance` INT NULL COMMENT '0 - Irrelevant bug 1 - Cosmetic bug 2 - Minor functional bug 3 - Problematic functional bug 4 - Major problem', CONSTRAINT `PK_Issue` PRIMARY KEY (`Id_Issue` ASC) ) ; CREATE TABLE `Login_Credentials` ( `Id_Login_Credentials` INT NOT NULL AUTO_INCREMENT, `Login_Name` VARCHAR(50) NOT NULL, `Password` VARCHAR(255) NOT NULL, CONSTRAINT `PK_Login_Credentials` PRIMARY KEY (`Id_Login_Credentials` ASC) ) ; CREATE TABLE `Project` ( `Id_Project` INT NOT NULL AUTO_INCREMENT, `Project_Name` VARCHAR(100) NOT NULL, CONSTRAINT `PK_Project` PRIMARY KEY (`Id_Project` ASC) ) ; CREATE TABLE `Team` ( `Id_Team` INT NOT NULL AUTO_INCREMENT, `Id_Leader` INT NOT NULL, `Team_Name` VARCHAR(50) NOT NULL, CONSTRAINT `PK_Team` PRIMARY KEY (`Id_Team` ASC) ) ; CREATE TABLE `Team_Project_Relationship` ( `Id_Team` INT NOT NULL, `Id_Project` INT NOT NULL, CONSTRAINT `PK_Team_Project_Relationship` PRIMARY KEY (`Id_Team` ASC, `Id_Project` ASC) ) ; /* Create Primary Keys, Indexes, Uniques, Checks */ ALTER TABLE `Comment` ADD INDEX `IXFK_Comment_Employee` (`Id_Employee` ASC) ; ALTER TABLE `Comment` ADD INDEX `IXFK_Comment_Issue` (`Id_Issue` ASC) ; ALTER TABLE `Employee` ADD INDEX `IXFK_Employee_Login_Credentials` (`Id_Login_Credentials` ASC) ; ALTER TABLE `Employee` ADD INDEX `IXFK_Employee_Team` (`Id_Team` ASC) ; ALTER TABLE `Issue` ADD INDEX `IXFK_Issue_Employee` (`Id_Creater` ASC) ; ALTER TABLE `Issue` ADD INDEX `IXFK_Issue_Employee_02` (`Id_Closer` ASC) ; ALTER TABLE `Issue` ADD INDEX `IXFK_Issue_Project` (`Id_Project` ASC) ; ALTER TABLE `Team` ADD INDEX `IXFK_Team_Employee` (`Id_Leader` ASC) ; ALTER TABLE `Team_Project_Relationship` ADD INDEX `IXFK_Team_Project_Relationship_Project` (`Id_Project` ASC) ; ALTER TABLE `Team_Project_Relationship` ADD INDEX `IXFK_Team_Project_Relationship_Team` (`Id_Team` ASC) ; /* Create Foreign Key Constraints */ ALTER TABLE `Comment` ADD CONSTRAINT `FK_Comment_Employee` FOREIGN KEY (`Id_Employee`) REFERENCES `Employee` (`Id_Employee`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Comment` ADD CONSTRAINT `FK_Comment_Issue` FOREIGN KEY (`Id_Issue`) REFERENCES `Issue` (`Id_Issue`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Employee` ADD CONSTRAINT `FK_Employee_Login_Credentials` FOREIGN KEY (`Id_Login_Credentials`) REFERENCES `Login_Credentials` (`Id_Login_Credentials`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Employee` ADD CONSTRAINT `FK_Employee_Team` FOREIGN KEY (`Id_Team`) REFERENCES `Team` (`Id_Team`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Issue` ADD CONSTRAINT `FK_Issue_Employee` FOREIGN KEY (`Id_Creater`) REFERENCES `Employee` (`Id_Employee`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Issue` ADD CONSTRAINT `FK_Issue_Employee_02` FOREIGN KEY (`Id_Closer`) REFERENCES `Employee` (`Id_Employee`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Issue` ADD CONSTRAINT `FK_Issue_Project` FOREIGN KEY (`Id_Project`) REFERENCES `Project` (`Id_Project`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Team` ADD CONSTRAINT `FK_Team_Employee` FOREIGN KEY (`Id_Leader`) REFERENCES `Employee` (`Id_Employee`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Team_Project_Relationship` ADD CONSTRAINT `FK_Team_Project_Relationship_Project` FOREIGN KEY (`Id_Project`) REFERENCES `Project` (`Id_Project`) ON DELETE Restrict ON UPDATE Restrict ; ALTER TABLE `Team_Project_Relationship` ADD CONSTRAINT `FK_Team_Project_Relationship_Team` FOREIGN KEY (`Id_Team`) REFERENCES `Team` (`Id_Team`) ON DELETE Restrict ON UPDATE Restrict ; SET FOREIGN_KEY_CHECKS=1 ;
```
