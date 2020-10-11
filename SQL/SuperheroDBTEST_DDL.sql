

DROP DATABASE IF EXISTS SuperHeroDBTEST;

CREATE DATABASE SuperHeroDBTEST;
USE SuperHeroDBTEST;
	
CREATE TABLE Superpower (	
  idSuperpower INT PRIMARY KEY AUTO_INCREMENT,	
  name VARCHAR(45) NOT NULL);	
  	
  CREATE TABLE Location (	
  idLocation INT PRIMARY KEY AUTO_INCREMENT,	
  name VARCHAR(45) NOT NULL,	
  description VARCHAR(280) NOT NULL,	
  address VARCHAR(45) NOT NULL,	
  city VARCHAR(45) NOT NULL,	
  state CHAR(2) NULL,	
  country VARCHAR(30) NOT NULL,	
  zipcode CHAR(5) NULL,	
  latitude DECIMAL(8,6) NOT NULL,	
  longitude DECIMAL(9,6) NOT NULL	
);	
CREATE TABLE Hero (	
  idHero INT PRIMARY KEY AUTO_INCREMENT,	
  name VARCHAR(45) NOT NULL,	
  description VARCHAR(280) NOT NULL,	
  Superpower_idSuperpower INT NOT NULL,	
  photoFilename VARCHAR(255),	
    FOREIGN KEY (Superpower_idSuperpower)	
    REFERENCES Superpower(idSuperpower));	
CREATE TABLE Organization (	
  idOrganization INT PRIMARY KEY AUTO_INCREMENT,	
  name VARCHAR(45) NOT NULL,	
  Location_idLocation INT NOT NULL,	
  description VARCHAR(280) NOT NULL,	
  contactEmail VARCHAR(65) NULL,	
  contactPhone VARCHAR(15) NULL,	
    FOREIGN KEY (Location_idLocation)	
    REFERENCES Location(idLocation));	
CREATE TABLE Sighting (	
  idSighting INT PRIMARY KEY AUTO_INCREMENT,	
  date DATETIME NOT NULL,	
Hero_idHero INT NOT NULL,	
  Location_idLocation INT NOT NULL,	
    FOREIGN KEY (Hero_idHero)	
    REFERENCES Hero(idHero),	
    FOREIGN KEY (Location_idLocation)	
    REFERENCES Location(idLocation));	
CREATE TABLE HeroOrganization (	
  Hero_idHero INT NOT NULL,	
  Organization_idOrganization INT NOT NULL,	
 	
    FOREIGN KEY (Hero_idHero)	
    REFERENCES Hero(idHero),	
    FOREIGN KEY (Organization_idOrganization)	
    REFERENCES Organization (idOrganization));