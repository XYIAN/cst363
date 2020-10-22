-------------------------------------------------------------------------------
-- Homework 6
-- CST 363, Intro to Databases
--
-- Instructions:
-- o Edit this file by adding your SQL below each question (including
--   the select statement that helps to mark the boundaries in the
--   output.)
-------------------------------------------------------------------------------

-- The problems are based on the normalized CA campaign
-- contribution data. 

runscript from 'campaign-normal.sql';

-- 1. Create a view 'c_summary' summarizing campaign contributions,
-- with four attributes: cand_name, contbr_name, amount, and zip.  You
-- may use this view in following problems.
create view c_summary as select candidate.name as cand_name, contributor.name as contr_name, amount , zip from candidate
join contribution on candidate.cand_id = contribution.cand_id
join contributor on contributor.contbr_id = contribution.contbr_id; 

-- 2. For each of the occupations 'STUDENT', 'TEACHER', and 'LAWYER',
-- show the occupation, and average size (in dollars) of contribution
-- from contributors with that occupation.
select occupation, round(avg(amount),0) from contributor
join contribution on contributor.contbr_id = contribution.contbr_id 
where OCCUPATION = 'STUDENT' or occupation = 'TEACHER' or occupation = 'LAWYER' 
group by occupation;
--> LAWYER 564
--> STUDENT 538
--> TEACHER 105
;

-- 3. Let's focus on lawyers.  For each candidate, show the candidate
-- name and total dollar amount of contributions to that candidate,
-- but only for contributions from those with occupation 'LAWYER' and
-- zip codes that start with 939.
select candidate.name, sum(amount) from candidate
join contribution on candidate.cand_id = contribution.cand_id
join contributor on contributor.contbr_id = contribution.contbr_id
where CONTRIBUTOR.OCCUPATION = 'LAWYER' and contributor.zip like '939%' group by candidate.name;
--> Clinton, Hillary Rodham 1350.00
--> Cruz, Rafael Edward 'Ted' 125.00
;

-- 4. Do lawyers list their occupations in lots of different ways?
-- List the distinct occupations that contain 'LAWYER' within them?
select distinct(occupation) from contributor where occupation like '%LAWYER%';
--> AUTHOR/LAWYER
--> BUSINESS OWNER / LAWYER
--> BUSINESSMAN, LAWYER
--> ENTERTAINMENT LAWYER
--> ENVIRONMENTAL LAWYER
--> HUMAN RIGHTS LAWYER
--> LAWYER
--> LAWYER (CRIMINAL DEFENSE)
--> LAWYER / PHILANTHROPIST
--> LAWYER FOR THE OPPRESSED
--> LAWYER MEDIATOR/ARBITRATOR
--> LAWYER/ DOCTOR
--> LAWYER/'FARMER
--> LAWYER/CITY COUNCILMEMBER
--> LAWYER/ENGINEER
--> LAWYER/MEDIATOR
--> LAWYER/NON-PROFIT EXECUTIVE
--> LAWYER/PARTNER
--> LAWYER/PROFESSOR
--> LAWYER/PROFESSOR/ ENTREPRENEUR
--> LAWYER/TEACHER
--> LEGAL AID LAWYER
--> MANAGER, LAWYER
--> PARTNER/LAWYER
--> PATENT LAWYER
--> RETIRED LAWYER
--> TAX LAWYER
--> TRIAL LAWYER
;

-- 5. How many contributors have occupation 'LAWYER'?  Give just the count.
select count(occupation) from contributor where occupation = 'LAWYER';
--> 553
;

-- 6. How many contributors have an occupation that contains 'LAWYER'?
-- Give just the count.
select count(occupation) from contributor
where occupation like '%LAWYER%';
--> 592
;

-- 7. Give occupation and number of contributors with that occupation for
-- all occupations that contain the string 'LAWYER'.  Order by decreasing
-- count.
select OCCUPATION , count(CONTBR_ID ) from CONTRIBUTOR where OCCUPATION like '%LAWYER%' group by occupation order by count(CONTBR_ID ) desc;
--> LAWYER 553
--> TRIAL LAWYER 8
--> ENTERTAINMENT LAWYER 2
--> LAWYER FOR THE OPPRESSED 2
--> PATENT LAWYER 2
--> RETIRED LAWYER 2
--> TAX LAWYER 2
--> AUTHOR/LAWYER 1
--> BUSINESS OWNER / LAWYER 1
--> BUSINESSMAN, LAWYER 1
--> ENVIRONMENTAL LAWYER 1
--> HUMAN RIGHTS LAWYER 1
--> LAWYER (CRIMINAL DEFENSE) 1
--> LAWYER / PHILANTHROPIST 1
--> LAWYER MEDIATOR/ARBITRATOR 1
--> LAWYER/ DOCTOR 1
--> LAWYER/'FARMER 1
--> LAWYER/CITY COUNCILMEMBER 1
--> LAWYER/ENGINEER 1
--> LAWYER/MEDIATOR 1
--> LAWYER/NON-PROFIT EXECUTIVE 1
--> LAWYER/PARTNER 1
--> LAWYER/PROFESSOR 1
--> LAWYER/PROFESSOR/ ENTREPRENEUR 1
--> LAWYER/TEACHER 1
--> LEGAL AID LAWYER 1
--> MANAGER, LAWYER 1
--> PARTNER/LAWYER 1
;

-- 8. The occupation 'LAWYER FOR THE OPPRESSED' has an unusual name.
-- Look at all fields of the contributor table for contributors who
-- list their occupation this way.
select * from contributor where occupation = 'LAWYER FOR THE OPPRESSED';
--> 6317 GEARHEART, MARK MORAGA CA 945561857 SELF LAWYER FOR THE OPPRESSED
--> 22050 GEARHEART, MARK MORAGA CA 945561857 MR. LAWYER FOR THE OPPRESSED
;

-- 9. What is the average number of contributions per zip code?  Use only
-- the first five digits of the zip code.  Your result should be a single number.
select (select (count(CONTB_ID )) from contribution)/(select (count(distinct(substring(zip,1,5)))) from contributor);
--> 95
;
       
-- 10. Looking at only the first five digits of the zip code, show the 20
-- zip codes with the highest number of contributors (not contributions).
-- Give the five-digit form of the zip code plus the number of contributors
-- with that zip.  Order by descending number of contributors and the 
-- zip code ascending.
select substring(zip,1,5), count(contbr_id) from contributor group by substring(zip,1,5) order by count(contbr_id) desc, substring(zip,1,5) asc limit 20;
--> 90049 612
--> 90210 550
--> 94114 449
--> 94110 448
--> 90272 445
--> 94301 373
--> 90024 365
--> 90069 365
--> 94025 352
--> 92660 348
--> 94611 348
--> 92037 341
--> 94118 329
--> 94062 326
--> 94115 311
--> 90046 308
--> 94022 290
--> 94117 290
--> 90402 289
--> 94010 286
;

-- 11. For each distinct last name/zip combination, list the last name,
-- zip, and number of contributors with that last name/zip
-- combination, but only for last name/zip combinations that occur more
-- 6 times. List by decreasing count.  HINT: use 'subtr' and
-- 'instr' to get the last name from the name field.
select distinct substring(name,1,instr(name, ',') - 1) as first, zip, count(*) from CONTRIBUTOR group by first, zip having count(*) > 6  order by count(*) desc;
--> HARMON 956773906 14
--> SHAPIRO 914232758 10
--> FARGHER 950515373 8
--> MANGIONE 926603299 8
--> PECK 920670829 8
--> APPLBAUM 946102446 7
--> BOYCE 940287620 7
--> BOYD 905044622 7
--> HARRIS 926604275 7
--> HARRISON 930070144 7
--> LOVELACE 951182303 7
--> MANN 916043855 7
--> SWANSON 945620148 7
;

-- 12. For each contributor that made more than 75 contributions,
-- show the contributor name, total dollar amount contributed,
-- and number of contributions.
select name, sum(amount), count(*) from contributor 
join contribution on contribution.contbr_id = contributor.contbr_id
group by name having count(*) > 75 order by count(*) desc;
--> BHOOMA, PRAMOD MR. 2471.72 171
--> IMEOKPARIA, OSI 2528.80 125
--> MERRELL, KATHERINE 2560.00 109
--> BATTS, ERIC 2294.00 106
--> TRAWICK, MARGARET 2700.00 101
--> HABEL, DANIEL 2804.00 87
--> MAGGLOS, LINDA 15260.00 84
--> BROWN, CHRISTOPHER 2486.00 83
--> ROSSON, MARIAN B 615.04 78
--> DAVIDSON, LISA 2895.34 77
;


-- 13. For each candidate, what fraction of all (positive) contributions
-- came from contributions > $2500?   Show candidate name and fraction.
select t1.name, round(top/bottom * 100,1) from 
(select name, sum(amount) as top from contribution join candidate on candidate.cand_id = contribution.cand_id where amount > 2500 group by name) as t1
natural join
(select name, sum(amount) as bottom from contribution join candidate on candidate.cand_id = contribution.cand_id where amount > 0 group by name) as t2
order by round(top/bottom * 100,1) desc
;
--> Christie, Christopher J. 85.4
--> Bush, Jeb 82.7
--> Graham, Lindsey O. 78.9
--> Perry, James R. (Rick) 78.1
--> Clinton, Hillary Rodham 70.6
--> Santorum, Richard J. 67.3
--> Kasich, John R. 64.9
--> Rubio, Marco 63.6
--> O'Malley, Martin Joseph 63.5
--> Pataki, George E. 62.1
--> Walker, Scott 61.1
--> Jindal, Bobby 58.1
--> Webb, James Henry Jr. 56.0
--> Lessig, Lawrence 51.5
--> Huckabee, Mike 45.3
--> Fiorina, Carly 44.9
--> Trump, Donald J. 38.0
--> Paul, Rand 34.7
--> Cruz, Rafael Edward 'Ted' 34.3
--> Carson, Benjamin S. 21.5
--> Sanders, Bernard 6.3
;