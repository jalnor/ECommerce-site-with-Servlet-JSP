/************************************************
 * Written by: Jarrod Norris					*
 * Class	 : ITIS 4166-UOL					*
 * Date		 : 3 Nov. 2017						*
 ************************************************/
 
/*Drops jmansales db if exists then creates it.*/
DROP DATABASE IF EXISTS jmansales;
CREATE DATABASE jmansales;
/*Declares use jmansales db.*/
USE jmansales;
/*Drops users table if exists then creates it.*/
DROP TABLE IF EXISTS users;
CREATE TABLE users 
(
	userID		INT(11)				NOT NULL 	AUTO_INCREMENT		PRIMARY KEY,
    uname		VARCHAR(25)			NOT NULL,
    pWord		VARCHAR(25)			NOT NULL,
    lastName	VARCHAR(50)			DEFAULT NULL,
    firstName	VARCHAR(50)			DEFAULT NULL,
    email		VARCHAR(50)			DEFAULT NULL,
    addr1		VARCHAR(50)			DEFAULT NULL,
    addr2		VARCHAR(50)			DEFAULT NULL,
    city		VARCHAR(50)			DEFAULT NULL,
    state 		VARCHAR(50)			DEFAULT NULL,
    zip			INT(10)				DEFAULT NULL,
    country		VARCHAR(50)			DEFAULT NULL,
    roleName	VARCHAR(25)			DEFAULT NULL
);
/*Drops favorite table if exists then creates it.*/
DROP TABLE IF EXISTS favorites;
CREATE TABLE favorites
(
	userID		INT(11)				NOT NULL,
    question	VARCHAR(100)		NOT NULL,
    answer		VARCHAR(100)		NOT NULL,
    FOREIGN KEY (userID) REFERENCES users (userID)
);
/*Drops product table if exists then creates it.*/
DROP TABLE IF EXISTS product;
CREATE TABLE product 
(
    productCode		VARCHAR(6)		NOT NULL	PRIMARY KEY,
    productName		VARCHAR(50)		NOT NULL,
    catalogCategory	VARCHAR(50) 	NOT NULL,
    descript		VARCHAR(255) 	DEFAULT NULL,
    price			FLOAT(11,2)		DEFAULT NULL,
    imageURL		VARCHAR(50)		DEFAULT NULL
);
/*DROPS order if exists then creates it.*/
DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
	orderNumber		INT(50)			NOT NULL 	AUTO_INCREMENT,
    dateNow			TIMESTAMP		NOT NULL,
    userID			INT(11)			NOT NULL,
    taxRate			float(11, 2) 	NOT NULL,
    totalCost		DOUBLE(50, 2)	NOT NULL,
    paid			BIT(1)			NOT NULL,
    PRIMARY KEY (orderNumber),
    FOREIGN KEY (userID) REFERENCES users (userID)
);
/*Drops OrderItem if exists then creates it.*/
DROP TABLE IF EXISTS orderItem;
CREATE TABLE orderItem 
(
	orderNumber		INT(50)		NOT NULL,
    productCode		VARCHAR(50)	NOT NULL,
    quantity		INT(11)		NOT NULL,
    PRIMARY KEY(orderNumber, productCode)
);
/*Drops payment if exists then creates it.*/
DROP TABLE IF EXISTS payment;
CREATE TABLE payment
(
	userID				int(11)			NOT NULL,
    creditCardType		VARCHAR(25)		NOT NULL,
    cardNumber			VARCHAR(16)		NOT NULL,
    expireMonth			VARCHAR(2)		NOT NULL,
    expireYear			VARCHAR(4)		NOT NULL,
    cvv					VARCHAR(3)		NOT NULL,
    PRIMARY KEY (cardNumber, cvv),
    FOREIGN KEY (userID) REFERENCES users (userID)
);
    

/*Inserts data into the users table.*/
INSERT INTO users(uname, pWord, lastName, firstName, email, addr1, addr2, city, state, zip, country, roleName)
	VALUES('jman', 'opensesame', 'Man', 'J', 'jman@someemail.com', '1101 Mary Alexander Rd', 'Apt E', 'Charlotte', 'NC', 28262, 'United States', 'administrator'),
		  ('ssmith', 'canary', 'Smith', 'Sarah', 'ssmith@aomeemail.com', '1102 Mary Alexander Rd', 'Apt A', 'Charlotte', 'NC', 28262, 'United States', 'customer');
          
/*Inserts ata into favorites table.*/
INSERT INTO favorites(userID, question, answer) 
	VALUES(1, 'wyfp', 'Galifray'),
		  (2, 'wyfc', 'Violet');

/*Inserts data into the product table.*/
INSERT INTO product(productCode, productName, catalogCategory, descript, price, imageURL)
	VALUES('AA1100', 'ASUS Power Adapter', 'Printers & Accessories', 'This ASUS power adapter is designed for ROG (Republic of Gamers) laptops and will deliver the power needed for you to stay in the game.', 40.00, '/jmansales/images/asusPA.png'),
		  ('MA1100', 'Mac Power Adapter', 'Printers & Accessories', 'The Original MacBook Pro power adapter is buit to last and deliver the power you need to finish your projects.', 40.00, '/jmansales/images/macPA.png'),
          ('EP1100', 'Epson Workforce Printer WF-3650', 'Printers & Accessories', 'Likes its name says, Workforce. This printer can handle heavy jobs at 1000 ppm black and 999 ppm color. It features a 2""X3"" display, wifi capability, and a usb for port. This printer will go the mile and deliver quality prints.', 250.00, '/jmansales/images/epsonPrinter.png'),
		  ('KF1100', 'Kindle Fire 8"', 'Laptops & Tablets', 'The Kindle Fire tablet is an excellent device for storing your favorite books. But it also has a color screen and plenty of space for apps. It features an  display and 16Gb of storage.', 100.00, '/jmansales/images/kindleFire.png'),
		  ('AS1100', 'ASUS Gaming Laptop', 'Laptops & Tablets', 'The ASUS Gaming Laptop is a powerful gaming computer. It comes with the latest NVidia graphics card and is loaded with features. This includes the ROG key that allows you to set gaming profiles. You will spend hours having fun with this laptop.', 2999.99, '/jmansales/images/ASUS.png'),
		  ('MB1100', 'MacBookPro', 'Laptops & Tablets', 'The MacBook Pro is a powerful, comprehensive business computer. It comes with the latest Mac OSx operating system and is loaded with features. This includes SIRI, you very own digital assistant. It''s like having a secretary with you where ever you are.', 3999.99, '/jmansales/images/MacBookPro.png');

        
/*rops user then recreates user and grants permissions.*/
DROP USER IF EXISTS jman@localhost; 
CREATE USER jman@localhost IDENTIFIED BY 'opensesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON jmansales.*
TO jman@localhost;           