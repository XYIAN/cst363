----------------------------------------------------------------------------
-- Homework 4
-- CST 363, Intro to Databases
--
-- Instructions:
-- o Enter your SQL below each numbered question below.
-- o Do not delete or modify any lines in the file!  Only add new lines.

-- This homework has four parts:
-- 1. Write queries using the campaign data table.
-- 2. Split the campaign data into three tables.
-- 3. Write queries using the three new campaign data tables.
----------------------------------------------------------------------------


runscript from 'campaign-ca-2016.sql';

----------------------------------------------------------------------------
-- Part 1 - write queries using the campaign data table
----------------------------------------------------------------------------

-- 1. how many contributions are contained in the data?

select count(contbr_nm) from campaign;
--> 180478
;

-- 2. show the min and maximum amounts of all contributions

select min(contb_receipt_amt), max(contb_receipt_amt) from campaign;
--> -10000.00 10800.00
;

-- 3. list the (distinct) ids and names of all the candidates in the data.
-- Order by name.

select distinct cand_id, cand_nm  from campaign order by cand_nm;
--> P60008059 Bush, Jeb
--> P60005915 Carson, Benjamin S.
--> P60008521 Christie, Christopher J.
--> P00003392 Clinton, Hillary Rodham
--> P60006111 Cruz, Rafael Edward 'Ted'
--> P60007242 Fiorina, Carly
--> P60007697 Graham, Lindsey O.
--> P80003478 Huckabee, Mike
--> P60008398 Jindal, Bobby
--> P60003670 Kasich, John R.
--> P60009685 Lessig, Lawrence
--> P60007671 O'Malley, Martin Joseph
--> P60007572 Pataki, George E.
--> P40003576 Paul, Rand
--> P20003281 Perry, James R. (Rick)
--> P60006723 Rubio, Marco
--> P60007168 Sanders, Bernard
--> P20002721 Santorum, Richard J.
--> P20003984 Stein, Jill
--> P80001571 Trump, Donald J.
--> P60006046 Walker, Scott
--> P60008885 Webb, James Henry Jr.
;

-- 4. show the candidate name and number of contributions, for each candidate
-- Order by decreasing number of contributions.

select cand_nm, count(cand_nm) from campaign group by(cand_nm) order by count(cand_nm) desc;
--> Sanders, Bernard 72179
--> Clinton, Hillary Rodham 42063
--> Cruz, Rafael Edward 'Ted' 21645
--> Carson, Benjamin S. 21045
--> Rubio, Marco 7994
--> Fiorina, Carly 4426
--> Paul, Rand 4117
--> Bush, Jeb 2762
--> Kasich, John R. 701
--> Walker, Scott 670
--> Trump, Donald J. 590
--> Huckabee, Mike 447
--> O'Malley, Martin Joseph 383
--> Lessig, Lawrence 372
--> Graham, Lindsey O. 331
--> Christie, Christopher J. 316
--> Perry, James R. (Rick) 116
--> Webb, James Henry Jr. 106
--> Stein, Jill 85
--> Santorum, Richard J. 79
--> Jindal, Bobby 31
--> Pataki, George E. 20
;

-- 5. show the candidate name and average contribution amount for each candidate, 
-- looking at positive contributions only
-- Order by decreasing average contribution amount.

select cand_nm, round(avg(contb_receipt_amt),0) from campaign where contb_receipt_amt > 0 group by(cand_nm) order by avg(contb_receipt_amt) desc;
--> Perry, James R. (Rick) 2061
--> Christie, Christopher J. 1529
--> Pataki, George E. 1523
--> Graham, Lindsey O. 1513
--> Bush, Jeb 1286
--> Walker, Scott 1047
--> Kasich, John R. 1033
--> O'Malley, Martin Joseph 766
--> Jindal, Bobby 749
--> Webb, James Henry Jr. 722
--> Rubio, Marco 717
--> Huckabee, Mike 547
--> Clinton, Hillary Rodham 537
--> Lessig, Lawrence 509
--> Santorum, Richard J. 457
--> Trump, Donald J. 365
--> Fiorina, Carly 356
--> Paul, Rand 206
--> Cruz, Rafael Edward 'Ted' 161
--> Stein, Jill 156
--> Carson, Benjamin S. 142
--> Sanders, Bernard 88
;

-- 6. show the candidate name and the total amount received by each candidate.
-- Order the output by total amount received.

select cand_nm, sum(contb_receipt_amt) from campaign group by(cand_nm) order by sum(contb_receipt_amt) desc;
--> Clinton, Hillary Rodham 21899488.66
--> Sanders, Bernard 6109590.10
--> Rubio, Marco 4562345.79
--> Bush, Jeb 3333245.23
--> Cruz, Rafael Edward 'Ted' 3133010.53
--> Carson, Benjamin S. 2741477.22
--> Fiorina, Carly 1452619.42
--> Paul, Rand 814093.72
--> Kasich, John R. 708585.37
--> Walker, Scott 461479.24
--> Christie, Christopher J. 449491.00
--> Graham, Lindsey O. 400495.00
--> O'Malley, Martin Joseph 290230.16
--> Huckabee, Mike 225351.60
--> Trump, Donald J. 208891.34
--> Perry, James R. (Rick) 208400.00
--> Lessig, Lawrence 186144.50
--> Webb, James Henry Jr. 76568.16
--> Santorum, Richard J. 36104.88
--> Pataki, George E. 30450.00
--> Jindal, Bobby 23231.26
--> Stein, Jill 13265.00
;

----------------------------------------------------------------------------
-- Part 2 - split the campaign data into three tables.
----------------------------------------------------------------------------

-- Please read all of the following:

-- The campaign contribution data is contained in a single big table,
-- but this approach leads to much redundancy.  For example, Hillary
-- Clinton's name appears thousands of times in the data.  Similarly,
-- if a contributor makes multiple contributions, there are multiple
-- rows of the table with the contributor's details, such as address,
-- occupation, etc.

-- The following relation schemas show an alternative way to organize
-- the data that avoids this kind of redundancy.  For example, in the
-- candidate table we give the candidate names and ids, with only one
-- row in the table for each candidate.

-- The connection between the attributes of the three new tables and
-- the attributes of the campaign table are shown in comments in the
-- table create statements below.  For example, attribute name of
-- table contributor comes from field contbr_nm of the campaign table.

create table candidate (
  cand_id varchar(12) primary key,	-- cand_id
  name	  varchar(40)			-- cand_nm 
);

create table contributor (
  contbr_id  integer primary key auto_increment,
  name	     varchar(50),			-- contbr_nm
  city       varchar(40),			-- contbr_city
  state	     varchar(40),			-- contbr_st
  zip	     varchar(20),			-- contbr_zip
  employer   varchar(60),			-- contbr_employer
  occupation varchar(40)			-- contbr_occupation
);

create index contributor_name on contributor (name);

create table contribution (
  contb_id      integer primary key auto_increment,
  cand_id       varchar(12),			-- cand_id
  contbr_id     varchar(12),			-- contbr_id
  amount        numeric(8,2),			-- contb_receipt_amt
  date	        varchar(20),			-- contb_receipt_dt
  election_type varchar(20),			-- election_tp
  tran_id       varchar(20),			-- tran_id
  foreign key (cand_id) references candidate,
  foreign key (contbr_id) references contributor
);

-- 7. Write an insert statement to fill the candidate table.  The
-- candidate table, after it is filled, should contain one row
-- for each candidate (there are 22 candidates in the campaign table).
-- Hint: use the kind of insert statement in which you list the
-- fields to be filled in parentheses after the table name.

insert into candidate(cand_id, name) select distinct cand_id, cand_nm from campaign;
-- 8. Write an insert statement to fill the contributor table.
-- The contributor table, after it is filled, should contain one row
-- for each unique combination of name, city, state, zip, employer,
-- and occupation. 
-- Hint: you do not need to supply a value for the contbr_id
-- field; it is an integer primary key and SQLite will provide a value
-- automatically for you.

   insert into contributor (name, city, state, zip, employer, occupation) select distinct contbr_nm, CONTBR_CITY, CONTBR_ST, CONTBR_ZIP, CONTBR_EMPLOYER, CONTBR_OCCUPATION from campaign;

-- 9. Write an insert statement to fill the contribution table.
-- The contribution table, after it is filled, should contain one row
-- for each row of the campaign table.
-- Hint: You do not need to suppy a value for the contb_id field; SQLite will
-- supply one for you automatically.
-- Hint: The select statement inside your insert statement will need to
-- mention the contributor table, since that is where the contbr_id is defined.

-- The database supplies the integer primary key automatically
insert into contribution (cand_id, contbr_id, amount, date, election_type, tran_id) select CAND_ID, contbr_id, CONTB_RECEIPT_AMT, CONTB_RECEIPT_DT,ELECTION_TP, TRAN_ID from campaign join contributor 
on campaign.CONTBR_NM = contributor.name and campaign.contbr_city = contributor.city and campaign.contbr_st = contributor.state and campaign.contbr_zip = contributor.zip and campaign.contbr_employer = contributor.employer and campaign.contbr_occupation = contributor.occupation;

-- 10. delete the campaign table

drop table campaign;

----------------------------------------------------------------------------
-- Part 3 - write SQL queries on the three new tables
----------------------------------------------------------------------------

-- 11. Show occupation, and total contribution amount for all occupations.
-- Order output by decreasing total contribution amount, and limit output to
-- 20 rows.

select CONTRIBUTOR.OCCUPATION, sum(CONTRIBUTION.AMOUNT) from CONTRIBUTION join 
CONTRIBUTOR on CONTRIBUTION.CONTBR_ID = CONTRIBUTOR.CONTBR_ID group by CONTRIBUTOR.OCCUPATION order by(sum(CONTRIBUTION.AMOUNT)) desc limit 20;
--> RETIRED 5955813.94
--> ATTORNEY 2733836.26
--> HOMEMAKER 2474060.37
--> NOT EMPLOYED 1941893.59
--> CEO 1588570.15
--> PRESIDENT 1243184.01
--> INFORMATION REQUESTED PER BEST EFFORTS 988278.06
--> INFORMATION REQUESTED 854468.98
--> EXECUTIVE 779429.71
--> CONSULTANT 759514.25
--> PHYSICIAN 754393.78
--> INVESTOR 654487.59
--> LAWYER 649561.13
--> OWNER 628849.03
--> REAL ESTATE 586102.81
--> ENGINEER 428027.92
--> STUDENT 407543.57
--> WRITER 405030.18
--> MANAGER 386150.81
--> PROFESSOR 340579.10
;

-- 12. Show the month, year, and sum of all contributions for that month and year,
-- for all month, year combinations found in the data.
-- Order your output by total contributions.  Round total contributions to nearest dollar.
-- Hint: month and year are substrings of the contribution date

select SUBSTRING(DATE,4,3) as month, SUBSTRING(DATE, 8,2) as year, round(sum(AMOUNT),0) as total from CONTRIBUTION group by month, year order by total asc;
--> NOV 13 0
--> JUL 14 1300
--> OCT 14 1500
--> JAN 15 3045
--> AUG 14 3050
--> NOV 14 7200
--> FEB 15 24768
--> SEP 14 30893
--> DEC 14 56553
--> MAR 15 375565
--> MAY 15 3158356
--> NOV 15 3328037
--> AUG 15 3499678
--> JUL 15 3705854
--> APR 15 4165913
--> OCT 15 4818410
--> JAN 16 5635937
--> JUN 15 6027387
--> SEP 15 6128706
--> DEC 15 6392405
;

-- 13. Show the candidate name and sum of contributions for all candidates.
-- Order by total contribution amount in decreasing order, round total contributions
-- to nearest dollar.

select CANDIDATE.NAME, round(SUM(CONTRIBUTION.AMOUNT),0) from CANDIDATE join CONTRIBUTION on CONTRIBUTION.CAND_ID = CANDIDATE.CAND_ID group by(CANDIDATE.NAME) order by round(SUM(CONTRIBUTION.AMOUNT),0) desc;
--> Clinton, Hillary Rodham 21899489
--> Sanders, Bernard 6109590
--> Rubio, Marco 4562346
--> Bush, Jeb 3333245
--> Cruz, Rafael Edward 'Ted' 3133011
--> Carson, Benjamin S. 2741477
--> Fiorina, Carly 1452619
--> Paul, Rand 814094
--> Kasich, John R. 708585
--> Walker, Scott 461479
--> Christie, Christopher J. 449491
--> Graham, Lindsey O. 400495
--> O'Malley, Martin Joseph 290230
--> Huckabee, Mike 225352
--> Trump, Donald J. 208891
--> Perry, James R. (Rick) 208400
--> Lessig, Lawrence 186145
--> Webb, James Henry Jr. 76568
--> Santorum, Richard J. 36105
--> Pataki, George E. 30450
--> Jindal, Bobby 23231
--> Stein, Jill 13265
;

-- 14. Show the city and number of contributions of more than $5,000  for the 10 cities with the
-- most contributions > $5,000. Order output by number of > $5,000 contributions, decreasing and city ascending.

select CONTRIBUTOR.CITY, count(CONTRIBUTION.AMOUNT) from CONTRIBUTOR join CONTRIBUTION  on CONTRIBUTION.CONTBR_ID = CONTRIBUTOR.CONTBR_ID where CONTRIBUTION.AMOUNT > 5000 group by(CONTRIBUTOR.CITY) order by count(CONTRIBUTION.AMOUNT) desc, CONTRIBUTOR.CITY asc limit 10;
--> LOS ANGELES 55
--> BEVERLY HILLS 20
--> SAN FRANCISCO 18
--> SHERMAN OAKS 14
--> NEWPORT BEACH 13
--> IRVINE 11
--> SANTA MONICA 10
--> PALO ALTO 8
--> NEWPORT COAST 7
--> BISHOP 6
;

-- Extra Credit question!
-- Did any contributors contribute to more than one candidate?
-- Show the contributor ID, and the number of candidates to which the
-- contributor made contributions, for all contributors who
-- contributed to multiple candidates.  Give output by number of
-- candidates contributed to, in decreasing order, and contributer id ascending.  
-- Limit your output to 20 rows.

select CONTBR_ID, count(distinct(CAND_ID)) as count from CONTRIBUTION group by CONTBR_ID having count > 1 order by count desc, CONTBR_ID limit 20;
--> 57557 5
--> 56263 4
--> 58451 4
--> 10491 3
--> 13265 3
--> 16505 3
--> 20022 3
--> 2037 3
--> 22214 3
--> 244 3
--> 25282 3
--> 2739 3
--> 28691 3
--> 34642 3
--> 37048 3
--> 38217 3
--> 38265 3
--> 38772 3
--> 42585 3
--> 43004 3
;





;
