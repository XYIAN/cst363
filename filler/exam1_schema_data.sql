create table student(
 studentid int primary key,
 lastname char(25),
 firstname char(25),
 gpa numeric(5, 2),
 major char(25),
 totalcredits int);
 
create table course(
 courseid int primary key,
 name char(25),
 section char(4),
 department char(25),
 instructor char(25));
 
 create table enrollment(
  studentid int,
  courseid int,
  semester char(25),
  grade char(5),
  units int);
  
INSERT INTO STUDENT VALUES (5430,'Last0','First0',2.17,'MATH',29),
 (1287,'Last1','First1',3.1,'MATH',29),
 (1970,'Last2','First2',3.62,'MATH',29),
 (2928,'Last3','First3',2.21,'MATH',29),
 (5209,'Last4','First4',2.14,'MATH',29),
 (104,'Last5','First5',2.59,'MATH',29),
 (7460,'Last6','First6',3.66,'MATH',29),
 (6848,'Last7','First7',3.03,'MATH',29),
 (4456,'Last8','First8',2.66,'MATH',29),
 (3729,'Last9','First9',2.28,'MATH',29),
 (6324,'Last10','First10',2.25,'CS',28),
 (4106,'Last11','First11',2.61,'CS',28),
 (3214,'Last12','First12',2.36,'CS',28),
 (535,'Last13','First13',2.79,'CS',28),
 (7830,'Last14','First14',3.61,'CS',28),
 (9179,'Last15','First15',3.0,'CS',28),
 (8265,'Last16','First16',2.71,'CS',28),
 (9563,'Last17','First17',2.89,'CS',28),
 (8013,'Last18','First18',3.46,'CS',28),
 (565,'Last19','First19',3.68,'CS',28),
 (6240,'Last20','First20',3.13,'Biology',30),
 (2453,'Last21','First21',1.43,'Biology',30),
 (557,'Last22','First22',2.8,'Biology',30),
 (4263,'Last23','First23',3.4,'Biology',30),
 (9524,'Last24','First24',3.63,'Biology',30),
 (8843,'Last25','First25',2.0,'Biology',30),
 (4914,'Last26','First26',3.57,'Biology',30),
 (7262,'Last27','First27',3.33,'Biology',30),
 (8113,'Last28','First28',3.73,'Biology',30),
 (2966,'Last29','First29',3.2,'Biology',30),
 (8531,'Last30','First30',3.47,'Biology',30),
 (9955,'Last31','First31',2.07,'Biology',30),
 (5734,'Last32','First32',3.27,'Biology',30),
 (4078,'Last33','First33',3.03,'Biology',30),
 (7680,'Last34','First34',1.97,'Biology',30);
 
INSERT INTO COURSE VALUES 
(40123,'Calculus 1','1','MATH','Smith'),
 (40125,'Calculus 1','2','MATH','Lupin'),
 (40126,'Calculus 1','3','MATH','Fenwick'),
 (40130,'Calculus 2','1','MATH','Strong'),
 (40140,'Intro Prog','1','CS','Suesse'),
 (40240,'Data Structures','1','CS','Suesse'),
 (40500,'Cell Biology','1','Biology','Lynn'),
 (40501,'Cell Biology Lab','1','Biology','Lynn'),
 (40502,'Ecology','1','Biology','Finne'),
 (40503,'Ecology Lab','1','Biology','Dimmer'),
 (50100,'Chemistry 1','1','Chemistry','Lyon'),
 (50110,'Chemistry 2','1','Chemistry','Lyon'),
 (50500,'Politics','1','Social Science','Hanson'),
 (50510,'Economics','1','Social Science','Snivley'),
 (50520,'Technology & Society','1','Social Science','Lyon'),
 (50530,'Communication','1','HCOM','Patrick'),
 (50540,'Philosopy & Ethics','1','Social Science','Patrick'),
 (60200,'Health and Well Being','1','Kinesioloy','Ford'),
 (40132,'Discrete Math','1','MATH','Lupin'),
 (40128,'Geometry','1','MATH','Fenwick'),
 (50112,'Bio-Chemistry','1','Chemistry','Lyon');
 
 
 insert into enrollment values 
(5430,40123,'Fall2016','B',4),
 (5430,40130,'Spring2016','D',4),
 (5430,40132,'Spring2017','D',4),
 (5430,50510,'Fall2016','C',3),
 (5430,50520,'Fall2016','B',3),
 (5430,50530,'Spring2017','A',3),
 (5430,60200,'Spring2017','C',3),
 (5430,40500,'Fall2016','C',4),
 (5430,40501,'Fall2016','C',1),
 (1287,40125,'Fall2016','A',4),
 (1287,40130,'Spring2016','C',4),
 (1287,40132,'Spring2017','B',4),
 (1287,50510,'Fall2016','B',3),
 (1287,50520,'Fall2016','A',3),
 (1287,50530,'Spring2017','B',3),
 (1287,60200,'Spring2017','C',3),
 (1287,40500,'Fall2016','A',4),
 (1287,40501,'Fall2016','C',1),
 (1970,40126,'Fall2016','C',4),
 (1970,40130,'Spring2016','A',4),
 (1970,40132,'Spring2017','A',4),
 (1970,50510,'Fall2016','A',3),
 (1970,50520,'Fall2016','B',3),
 (1970,50530,'Spring2017','A',3),
 (1970,60200,'Spring2017','A',3),
 (1970,40500,'Fall2016','A',4),
 (1970,40501,'Fall2016','A',1),
 (2928,40123,'Fall2016','B',4),
 (2928,40130,'Spring2016','D',4),
 (2928,40132,'Spring2017','A',4),
 (2928,50510,'Fall2016','C',3),
 (2928,50520,'Fall2016','C',3),
 (2928,50530,'Spring2017','D',3),
 (2928,60200,'Spring2017','C',3),
 (2928,40500,'Fall2016','C',4),
 (2928,40501,'Fall2016','B',1),
 (5209,40125,'Fall2016','C',4),
 (5209,40130,'Spring2016','D',4),
 (5209,40132,'Spring2017','D',4),
 (5209,50510,'Fall2016','A',3),
 (5209,50520,'Fall2016','D',3),
 (5209,50530,'Spring2017','B',3),
 (5209,60200,'Spring2017','D',3),
 (5209,40500,'Fall2016','A',4),
 (5209,40501,'Fall2016','B',1),
 (104,40123,'Fall2016','C',4),
 (104,40130,'Spring2016','A',4),
 (104,40132,'Spring2017','A',4),
 (104,50510,'Fall2016','D',3),
 (104,50520,'Fall2016','D',3),
 (104,50530,'Spring2017','A',3),
 (104,60200,'Spring2017','D',3),
 (104,40500,'Fall2016','B',4),
 (104,40501,'Fall2016','C',1),
 (7460,40123,'Fall2016','A',4),
 (7460,40130,'Spring2016','B',4),
 (7460,40132,'Spring2017','A',4),
 (7460,50510,'Fall2016','C',3),
 (7460,50520,'Fall2016','A',3),
 (7460,50530,'Spring2017','A',3),
 (7460,60200,'Spring2017','A',3),
 (7460,40500,'Fall2016','A',4),
 (7460,40501,'Fall2016','A',1),
 (6848,40125,'Fall2016','D',4),
 (6848,40130,'Spring2016','B',4),
 (6848,40132,'Spring2017','A',4),
 (6848,50510,'Fall2016','D',3),
 (6848,50520,'Fall2016','A',3),
 (6848,50530,'Spring2017','B',3),
 (6848,60200,'Spring2017','A',3),
 (6848,40500,'Fall2016','A',4),
 (6848,40501,'Fall2016','A',1),
 (4456,40123,'Fall2016','C',4),
 (4456,40130,'Spring2016','C',4),
 (4456,40132,'Spring2017','C',4),
 (4456,50510,'Fall2016','B',3),
 (4456,50520,'Fall2016','B',3),
 (4456,50530,'Spring2017','C',3),
 (4456,60200,'Spring2017','B',3),
 (4456,40500,'Fall2016','A',4),
 (4456,40501,'Fall2016','A',1),
 (3729,40123,'Fall2016','A',4),
 (3729,40130,'Spring2016','D',4),
 (3729,40132,'Spring2017','B',4),
 (3729,50510,'Fall2016','D',3),
 (3729,50520,'Fall2016','B',3),
 (3729,50530,'Spring2017','D',3),
 (3729,60200,'Spring2017','D',3),
 (3729,40500,'Fall2016','B',4),
 (3729,40501,'Fall2016','A',1),
 (6324,40126,'Fall2016','C',4),
 (6324,40140,'Fall2016','D',4),
 (6324,40240,'Spring2017','B',4),
 (6324,40132,'Spring2017','B',4),
 (6324,50510,'Fall2016','D',3),
 (6324,50520,'Fall2016','D',3),
 (6324,50530,'Spring2017','B',3),
 (6324,60200,'Spring2017','A',3),
 (4106,40123,'Fall2016','A',4),
 (4106,40140,'Fall2016','B',4),
 (4106,40240,'Spring2017','C',4),
 (4106,40132,'Spring2017','D',4),
 (4106,50510,'Fall2016','A',3),
 (4106,50520,'Fall2016','B',3),
 (4106,50530,'Spring2017','C',3),
 (4106,60200,'Spring2017','C',3),
 (3214,40123,'Fall2016','B',4),
 (3214,40140,'Fall2016','D',4),
 (3214,40240,'Spring2017','C',4),
 (3214,40132,'Spring2017','B',4),
 (3214,50510,'Fall2016','C',3),
 (3214,50520,'Fall2016','D',3),
 (3214,50530,'Spring2017','A',3),
 (3214,60200,'Spring2017','B',3),
 (535,40123,'Fall2016','B',4),
 (535,40140,'Fall2016','A',4),
 (535,40240,'Spring2017','D',4),
 (535,40132,'Spring2017','D',4),
 (535,50510,'Fall2016','C',3),
 (535,50520,'Fall2016','A',3),
 (535,50530,'Spring2017','A',3),
 (535,60200,'Spring2017','A',3),
 (7830,40123,'Fall2016','A',4),
 (7830,40140,'Fall2016','A',4),
 (7830,40240,'Spring2017','B',4),
 (7830,40132,'Spring2017','B',4),
 (7830,50510,'Fall2016','B',3),
 (7830,50520,'Fall2016','A',3),
 (7830,50530,'Spring2017','A',3),
 (7830,60200,'Spring2017','A',3),
 (9179,40126,'Fall2016','B',4),
 (9179,40140,'Fall2016','B',4),
 (9179,40240,'Spring2017','B',4),
 (9179,40132,'Spring2017','B',4),
 (9179,50510,'Fall2016','B',3),
 (9179,50520,'Fall2016','C',3),
 (9179,50530,'Spring2017','A',3),
 (9179,60200,'Spring2017','B',3),
 (8265,40125,'Fall2016','A',4),
 (8265,40140,'Fall2016','A',4),
 (8265,40240,'Spring2017','C',4),
 (8265,40132,'Spring2017','B',4),
 (8265,50510,'Fall2016','C',3),
 (8265,50520,'Fall2016','C',3),
 (8265,50530,'Spring2017','C',3),
 (8265,60200,'Spring2017','C',3),
 (9563,40123,'Fall2016','D',4),
 (9563,40140,'Fall2016','B',4),
 (9563,40240,'Spring2017','A',4),
 (9563,40132,'Spring2017','A',4),
 (9563,50510,'Fall2016','C',3),
 (9563,50520,'Fall2016','A',3),
 (9563,50530,'Spring2017','C',3),
 (9563,60200,'Spring2017','B',3),
 (8013,40126,'Fall2016','B',4),
 (8013,40140,'Fall2016','A',4),
 (8013,40240,'Spring2017','B',4),
 (8013,40132,'Spring2017','B',4),
 (8013,50510,'Fall2016','A',3),
 (8013,50520,'Fall2016','B',3),
 (8013,50530,'Spring2017','A',3),
 (8013,60200,'Spring2017','A',3),
 (565,40126,'Fall2016','A',4),
 (565,40140,'Fall2016','A',4),
 (565,40240,'Spring2017','A',4),
 (565,40132,'Spring2017','A',4),
 (565,50510,'Fall2016','C',3),
 (565,50520,'Fall2016','A',3),
 (565,50530,'Spring2017','B',3),
 (565,60200,'Spring2017','A',3),
 (6240,40126,'Fall2016','B',4),
 (6240,40130,'Spring2017','A',4),
 (6240,50520,'Fall2016','B',3),
 (6240,50530,'Spring2017','A',3),
 (6240,60200,'Spring2017','B',3),
 (6240,40500,'Fall2016','A',4),
 (6240,40501,'Fall2016','A',1),
 (6240,50100,'Fall2016','C',4),
 (6240,50110,'Spring2017','C',4),
 (2453,40123,'Fall2016','C',4),
 (2453,40130,'Spring2017','C',4),
 (2453,50520,'Fall2016','D',3),
 (2453,50530,'Spring2017','D',3),
 (2453,60200,'Spring2017','D',3),
 (2453,40500,'Fall2016','C',4),
 (2453,40501,'Fall2016','C',1),
 (2453,50100,'Fall2016','D',4),
 (2453,50110,'Spring2017','D',4),
 (557,40125,'Fall2016','A',4),
 (557,40130,'Spring2017','B',4),
 (557,50520,'Fall2016','C',3),
 (557,50530,'Spring2017','B',3),
 (557,60200,'Spring2017','C',3),
 (557,40500,'Fall2016','C',4),
 (557,40501,'Fall2016','B',1),
 (557,50100,'Fall2016','B',4),
 (557,50110,'Spring2017','B',4),
 (4263,40123,'Fall2016','A',4),
 (4263,40130,'Spring2017','A',4),
 (4263,50520,'Fall2016','C',3),
 (4263,50530,'Spring2017','A',3),
 (4263,60200,'Spring2017','A',3),
 (4263,40500,'Fall2016','A',4),
 (4263,40501,'Fall2016','A',1),
 (4263,50100,'Fall2016','C',4),
 (4263,50110,'Spring2017','B',4),
 (9524,40126,'Fall2016','B',4),
 (9524,40130,'Spring2017','B',4),
 (9524,50520,'Fall2016','B',3),
 (9524,50530,'Spring2017','A',3),
 (9524,60200,'Spring2017','A',3),
 (9524,40500,'Fall2016','A',4),
 (9524,40501,'Fall2016','A',1),
 (9524,50100,'Fall2016','A',4),
 (9524,50110,'Spring2017','A',4),
 (8843,40123,'Fall2016','C',4),
 (8843,40130,'Spring2017','D',4),
 (8843,50520,'Fall2016','B',3),
 (8843,50530,'Spring2017','D',3),
 (8843,60200,'Spring2017','D',3),
 (8843,40500,'Fall2016','C',4),
 (8843,40501,'Fall2016','D',1),
 (8843,50100,'Fall2016','B',4),
 (8843,50110,'Spring2017','B',4),
 (4914,40123,'Fall2016','A',4),
 (4914,40130,'Spring2017','A',4),
 (4914,50520,'Fall2016','C',3),
 (4914,50530,'Spring2017','A',3),
 (4914,60200,'Spring2017','A',3),
 (4914,40500,'Fall2016','B',4),
 (4914,40501,'Fall2016','D',1),
 (4914,50100,'Fall2016','A',4),
 (4914,50110,'Spring2017','A',4),
 (7262,40125,'Fall2016','A',4),
 (7262,40130,'Spring2017','A',4),
 (7262,50520,'Fall2016','C',3),
 (7262,50530,'Spring2017','C',3),
 (7262,60200,'Spring2017','A',3),
 (7262,40500,'Fall2016','C',4),
 (7262,40501,'Fall2016','A',1),
 (7262,50100,'Fall2016','A',4),
 (7262,50110,'Spring2017','A',4),
 (8113,40123,'Fall2016','A',4),
 (8113,40130,'Spring2017','A',4),
 (8113,50520,'Fall2016','A',3),
 (8113,50530,'Spring2017','B',3),
 (8113,60200,'Spring2017','A',3),
 (8113,40500,'Fall2016','A',4),
 (8113,40501,'Fall2016','B',1),
 (8113,50100,'Fall2016','B',4),
 (8113,50110,'Spring2017','A',4),
 (2966,40123,'Fall2016','A',4),
 (2966,40130,'Spring2017','C',4),
 (2966,50520,'Fall2016','A',3),
 (2966,50530,'Spring2017','C',3),
 (2966,60200,'Spring2017','A',3),
 (2966,40500,'Fall2016','A',4),
 (2966,40501,'Fall2016','C',1),
 (2966,50100,'Fall2016','C',4),
 (2966,50110,'Spring2017','A',4),
 (8531,40126,'Fall2016','B',4),
 (8531,40130,'Spring2017','A',4),
 (8531,50520,'Fall2016','B',3),
 (8531,50530,'Spring2017','B',3),
 (8531,60200,'Spring2017','A',3),
 (8531,40500,'Fall2016','A',4),
 (8531,40501,'Fall2016','C',1),
 (8531,50100,'Fall2016','B',4),
 (8531,50110,'Spring2017','A',4),
 (9955,40123,'Fall2016','B',4),
 (9955,40130,'Spring2017','D',4),
 (9955,50520,'Fall2016','B',3),
 (9955,50530,'Spring2017','D',3),
 (9955,60200,'Spring2017','B',3),
 (9955,40500,'Fall2016','C',4),
 (9955,40501,'Fall2016','D',1),
 (9955,50100,'Fall2016','D',4),
 (9955,50110,'Spring2017','B',4),
 (5734,40126,'Fall2016','A',4),
 (5734,40130,'Spring2017','A',4),
 (5734,50520,'Fall2016','B',3),
 (5734,50530,'Spring2017','C',3),
 (5734,60200,'Spring2017','B',3),
 (5734,40500,'Fall2016','A',4),
 (5734,40501,'Fall2016','C',1),
 (5734,50100,'Fall2016','B',4),
 (5734,50110,'Spring2017','B',4),
 (4078,40126,'Fall2016','C',4),
 (4078,40130,'Spring2017','A',4),
 (4078,50520,'Fall2016','B',3),
 (4078,50530,'Spring2017','A',3),
 (4078,60200,'Spring2017','A',3),
 (4078,40500,'Fall2016','A',4),
 (4078,40501,'Fall2016','C',1),
 (4078,50100,'Fall2016','C',4),
 (4078,50110,'Spring2017','C',4),
 (7680,40123,'Fall2016','A',4),
 (7680,40130,'Spring2017','C',4),
 (7680,50520,'Fall2016','C',3),
 (7680,50530,'Spring2017','D',3),
 (7680,60200,'Spring2017','D',3),
 (7680,40500,'Fall2016','B',4),
 (7680,40501,'Fall2016','B',1),
 (7680,50100,'Fall2016','D',4),
 (7680,50110,'Spring2017','D',4);

create table product (
maker char(5) not null, 
model char(4) primary key,
type  char(10) not null
);

create table pc (
model char(4) primary key, 
speed decimal(8,2) not null,
ram int not null,
hd int not null, 
price int
);

create table laptop (
model char(4) primary key,
speed decimal(8,2) not null,
ram int not null, 
hd int not null,
screen decimal(8,2) not null,
price int not null
);

create table printer (
model char(4) primary key,
color boolean not null, 
type char(10) not null, 
price int not null
);

insert into product values 
('A', 1001, 'pc'),
 ('A', 1002, 'pc'),
 ('A', 1003, 'pc'),
 ('A', 2004, 'laptop'),
 ('A', 2005, 'laptop'),
 ('A', 2006, 'laptop'),
 ('B', 1004, 'pc'),
 ('B', 1005, 'pc'),
 ('B', 1006, 'pc'),
 ('B', 2007, 'laptop'),
 ('C', 1007, 'pc'),
 ('D', 1008, 'pc'),
 ('D', 1009, 'pc'),
 ('D', 1010, 'pc'),
 ('D', 3004, 'printer'),
 ('D', 3005, 'printer'),
 ('E', 1011, 'pc'),
 ('E', 1012, 'pc'),
 ('E', 1013, 'pc'),
 ('E', 2001, 'laptop'),
 ('E', 2002, 'laptop'),
 ('E', 2003, 'laptop'),
 ('E', 3001, 'printer'),
 ('E', 3002, 'printer'),
 ('E', 3003, 'printer'),
 ('F', 2008, 'laptop'),
 ('F', 2009, 'laptop'),
 ('G', 2010, 'laptop'),
 ('H', 3006, 'printer'),
 ('H', 3007, 'printer');

insert into pc values 
(1001, 2.66, 1024, 250, 2114),
 (1002, 2.10, 512, 250, 995),
 (1003, 1.42, 512, 80, 478),
 (1004, 2.80, 1024, 250, 649),
 (1005, 3.20, 512, 250, 630),
 (1006, 3.20, 1024, 320, 1049),
 (1007, 2.20, 1024, 200, 510),
 (1008, 2.20, 2048, 250, 770),
 (1009, 2.00, 1024, 250, 650),
 (1010, 2.80, 2048, 300, 770),
 (1011, 1.86, 2048, 160, 959),
 (1012, 2.80, 1024, 160, 649),
 (1013, 3.00, 512, 80, 529);

insert into laptop 
values(2001, 2.00, 2048, 240, 20.1, 3673),
(2002, 1.73, 1024, 80, 17.0, 949),
(2003, 1.80, 512, 60, 15.4, 549),
(2004, 2.00, 512, 60, 13.3, 1150),
(2005, 2.16, 1024, 120, 17.0, 2500),
(2006, 2.00, 2048, 80, 15.4, 1700),
(2007, 1.83, 1024, 120, 13.3, 1429),
(2008, 1.60, 1024, 100, 15.4, 900),
(2009, 1.60, 512, 80, 14.1, 680),
(2010, 2.00, 2048, 160, 15.4, 2300);

insert into printer 
values(3001, true, 'ink-jet', 99),
(3002, false, 'laser', 239),
(3003, true, 'laser', 899),
(3004, true, 'ink-jet', 120),
(3005, false, 'laser', 120),
(3006, true, 'ink-jet', 100),
(3007, true, 'laser', 200);