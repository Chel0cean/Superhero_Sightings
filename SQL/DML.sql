 USE SuperHeroDB;
 
INSERT INTO Superpower(idSuperpower, name) VALUES 
    (1, "Shapeshifting"),
    (2, "Flight"),
    (3, "Elemental control/manipulation"),
    (4, "Pyrokinesis"),
    (5, "Telekinesis"),
    (6, "Teleportation"),
    (7, "Thermal control/manipulation"),
    (8, "Superspeed"),
    (9, "Hydrokinesis");
    
        INSERT INTO Location(name,description,address,city,state,country,zipcode,latitude,longitude)  VALUES
    ("Del Taco","fast food restaurant", "822 E College Pkwy", "Carson City,","NV","US","89706",39.190640,-119.758200),
	("Central Linn High School","public secondary school", "32433 OR-228,", "Halsey","OR","US","97348",44.388882,-123.081070),
	("Lockheed Martin Aeronautics Co"," aeronautical development with military ties/funding and weaponry.", "1011 Lockheed Way,", "Palmdale","CA","US","93599",34.620312,-118.120628),
	("Tacos Y Jugos Genesis","Mexican restaurant", "7804", "Woodside Av, Queens","NY","US","11373",40.743695,-73.886648),
	("Unisphere","Spherical stainless steel globe orbited by three rings & commissioned for the 1964 World’s Fair", "Avenue of the Americas", "Flushing","NY","US","11354",40.746373,-73.845051),
	("Montauk Lighthouse Museum","Scenic views & museum at historic 1796 lighthouse authorized under President George Washington.", "2000 Montauk Hwy", "Montauk","NY","US","11954",41.070794,-71.857239),
    ("The Fisher Building In Detroit","Land Mark Sky Scraper", "3011 W Grand Blvd,", "Detroit","MI","US","48202",42.369160,-83.077011),
	("Grand Central Terminal","public subway", "89 E 42nd St,", "New York City","NY","US","10017",42.369160,-73.977310),
	("Flat Iron Building","building", "175 5th Ave,", "New York City","NY","US","10010",40.727760,-73.259956),
	("Baxter Building"," Fantastic Four headquarters", "1 Vanderbilt Ave,", "New York City","NY","US","10017",40.752441,-73.978330),
	("Stark Tower","Home Of Avengers", " 1472 Broadway, ", "New York City","NY","US","10036",40.752441,-73.978330),
	("Batman’s House","Batman lives on top of the freedom tower.", "285 Fulton Street,", "New York City", "NY","US","10006",40.713122, -74.013363);



    INSERT INTO Hero(name,description,Superpower_idSuperpower,photoFilename)  VALUES
	("FlyGuy","Can control all tectonic plates within range, causing earthquakes. Alias Nick Shaker",1,
"images/uploads/HerosPicture/hero1.jpg"),
	("Tecton-Nick","Good Guy",2,
"images/uploads/HerosPicture/hero9.jpg"),
    ("Spider Pig","Morphs into a giant hybrid pig-spider, alias is Homer Simpson",3,
"images/uploads/HerosPicture/hero11.jpg"),
	("Flamethrower","Can cause extreme fear, and breathe Fire alias Russell Russ",4,
"images/uploads/HerosPicture/hero4.jpg"),
    ("The InvisibleEntity","Can turn invisible anytime. Alias Chucker",5,
"images/uploads/HerosPicture/hero5.jpg"),
    ("Zen","Can read and control people’s minds. Alias Mandy",6,
"images/uploads/HerosPicture/hero6.jpg"),
    ("Iron Man","Tony Stark has a suit of armor that he uses to save the world",7,
"images/uploads/HerosPicture/hero7.jpg"),
    ("Mr.Fantastic"," Reed Richards can stretch any part of his body to save the day.",8,
"images/uploads/HerosPicture/hero8.jpg");
    
    INSERT INTO Organization(name, Location_idLocation, description, contactEmail, contactPhone) VALUES
    ("The Southwest Center for Bad-doing", 1, "A large obsidian glass building that reflects the southwest sun into the eyes of all that pass by. Its mere existence has been the cause of many car accidents.", "badDoer@evil.com", 12125552112),
    ("The Thermopolis House of Integrity", 2, "Hidden underground, rumored to have a secret rocket launch pad.", "integrity@thermoplis.com", 16461235689),
	("Virtual Reality Simulation", 3, "A portal gun allows you to visit the Virtual Reality Simulation. Located in a secret location called 404", "vr@portal.com", 19178987878),
	("Fire Nation", 4, "Located in an island called Fire Island.", "fireNation@avatar.com", 17186549876),
	("The Post", 5, " A lotus looking building that is made of steel. Finger prints needed to enter.", "post@lotus.net", 13125661234),
    ("The Way", 6, "Far away in the middle of BadLands, SandHouse blends right into the Environment.", "theway@badlands.com", 15983598649),
    ("Fantastic Four", 7, "A family dedicated to saving the planet.", "fantastic@four.com", 14568956321),
    ("Doomers", 8, "Earth’s worst supervillain team located under Penn Station.", "doom@evil.net", 13694235612);
    
    
    INSERT INTO HeroOrganization VALUES
	(1,1),
	(2,2),
	(3,3),
	(4,4),
    (5,5),
    (6,6),
    (7,7),
    (8,8);
    
    
    INSERT INTO Sighting(date,Hero_idHero,Location_idLocation)  VALUES
	('2020-01-01',1,1),
	('2020-01-02',2,1),
	('2020-04-03',3,2),
	('2020-05-01',4,3),
	('2020-03-02',5,3),
	('2020-07-03',6,3),
    ('2020-08-01',7,4),
	('2020-04-02',8,5),
	('2020-05-03',1,6),
    ('2020-03-01',1,6),
	('2020-01-02',2,7),
	('2020-01-03',3,7),
    ('2020-07-01',4,8),
	('2020-01-02',4,9),
	('2020-01-03',5,10),
    ('2020-09-01',5,11),
	('2020-01-02',5,12),
	('2020-03-03',6,12),
    ('2020-01-01',7,12),
	('2020-02-02',8,1),
	('2020-01-03',8,2),
    ('2020-08-01',8,2),
	('2020-04-02',8,4),
	('2020-04-03',7,5);
    
    
    
    
    