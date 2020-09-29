
USE SuperHeroDBTEST ;


-- Data Entry For `Superpower`
INSERT INTO Superpower(name)  VALUES
("Power1"),
("Power2"),
("Power3"); 

-- Data Entry For `Superpower`
INSERT INTO Hero(name,description,Superpower_idSuperpower)  VALUES
("HeroName1","Good",1),
("HeroName2","Good",2),
("HeroName3","Good",3),
("HeroName4","Good",3); 


INSERT INTO Location(name,description,address,city,state,country,zipcode,latitude,longitude)  VALUES
("Location1","description for location1", "Address1", "City1","State1","Country1","zip01",40.744006,-73.886648),
("Location2","description for location2", "Address2", "City2","State2","Country2","zip02",40.744006,73.886648),
("Location3","description for location3", "Address3", "City3","State3","Country3","zip03",-40.744006,73.886648);

INSERT INTO Sighting(date,Hero_idHero,Location_idLocation)  VALUES
('2020-01-01',1,1),
('2020-01-02',2,2),
('2020-01-03',3,3);  

INSERT INTO Organization(name, Location_idLocation, description, contactEmail, contactPhone) VALUES
("hm store", 1, "dirty", "vv", 555),
("other", 2, "dirty", "vv", 555),
("another", 3, "dirty", "vv", 555);

INSERT INTO HeroOrganization VALUES
(1,1),
(2,2),
(3,3),
(4,3);







-- CREATE TABLE Organization (
--   idOrganization INT PRIMARY KEY AUTO_INCREMENT,
--   name VARCHAR(45) NOT NULL,
--   Location_idLocation INT NOT NULL,
--   description VARCHAR(45) NOT NULL,
--   contactEmail VARCHAR(20) NULL,
--   contactPhone INT NULL,
--     FOREIGN KEY (Location_idLocation)
--     REFERENCES Location(idLocation));




-- CREATE TABLE HeroOrganization (
--   Hero_idHero INT NOT NULL,
--   Organization_idOrganization INT NOT NULL,
 
--     FOREIGN KEY (Hero_idHero)
--     REFERENCES Hero(idHero),

--     FOREIGN KEY (Organization_idOrganization)
--     REFERENCES Organization (idOrganization));



-- use HotelSoftwareGuild; 
-- -- Data Entry For `RoomType`
-- INSERT INTO RoomType(RoomTypeID,Type,StandardOccupancy,MaximumOccupancy,Price,ExtraPerson) VALUES
-- (1,'Single',2,2,149.99,0),


-- -- Data Entry For `Amenities`
-- INSERT INTO Amenities(AmenitiesID,AmenitiesType,AmenitiesCost) VALUES
-- (1,'Microwave',0),


-- -- Data Entry For `RoomNumber`
-- INSERT INTO RoomNumber(RoomNumberID,RoomTypeID,ADAAccessible) VALUES
-- (201,2,0),
-- (202,2,1),


-- -- Data Entry For `RoomAmenities`
-- INSERT INTO RoomAmenities(RoomNumberID,AmenitiesID) VALUES
-- (201,1),
-- (201,4),
-- (202,3),



-- -- Data Entry For `Guest`
-- INSERT INTO Guest(GuestID,GuestFirstName,GuestLastName,Address,City,State,ZIP,Phone) VALUES
-- (1,'Mack','Simmer','379 Old Shore Street','Council Bluffs','IA','51501','(291) 553-0508'),
-- (2,'Bettyann','Seery','750 Wintergreen Dr.','Wasilla','AK','99654','(478) 277-9632'),


-- -- Data Entry For `Reservation`
-- INSERT INTO Reservation(ReservationID,StartDate,EndDate,NumberOfAdults,NumberOfChildren,TotalRoomCost,GuestID) VALUES
-- (1,'2023-02-02','2023-02-04',1,0,299.98,1),
-- (2,'2023-02-05','2023-02-10',2,1,999.95,2),




-- -- Data Entry For `RoomReservation`
-- INSERT INTO RoomReservation(ReservationID,RoomNumberID)  VALUES
-- (1,308),
-- (2,203),



-- -- Delete Jeremiah Pendergrass 
-- SET SQL_SAFE_UPDATES = 0;

-- -- remove data from RoomReservation
-- Delete FROM RoomReservation
-- where RoomReservation.ReservationID in
-- (select Reservation.ReservationID
-- from Guest
-- inner join Reservation on 
-- Reservation.GuestID = Guest.GuestID 
-- and  Guest.GuestFirstName = 'Jeremiah' and 
-- Guest.GuestLastName = 'Pendergrass' ); 

-- -- remove from Reservation
-- Delete FROM Reservation
-- where Reservation.GuestID in
-- (select Guest.GuestID
-- from Guest
-- where Guest.GuestFirstName = 'Jeremiah' and 
-- Guest.GuestLastName = 'Pendergrass' ); 

-- -- -- remove from Guest
-- Delete FROM Guest
-- where Guest.GuestFirstName = 'Jeremiah' and 
-- Guest.GuestLastName = 'Pendergrass'; 

-- SET SQL_SAFE_UPDATES = 1;