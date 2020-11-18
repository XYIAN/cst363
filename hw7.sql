-------------------------------------------------------------------------------
-- Homework 7 
--
-- Edit this file by replacing select 1; with your sql.
-- 
-------------------------------------------------------------------------------

-------------------------------------------------------------------------------
-- These questions are based on the normalized CA campaign
-- contribution data. 
-------------------------------------------------------------------------------

runscript from  'campaign-normal.sql';

-- 1. List the contributions to each candidate from contributors
-- that give 'SALINAS' as their city. 
-- Show candidate name, and total contributed amount.
-- Order the output by total contributed amount, descending then by 
-- name ascending.
select candidate.name, sum(contribution.amount) from candidate
join contribution on contribution.cand_id = CANDIDATE.CAND_ID 
join contributor on contribution.CONTBR_ID = contributor.CONTBR_ID 
where city = 'SALINAS' group by candidate.name order by sum(contribution.amount) desc, candidate.name asc;
--> Rubio, Marco 44050.00
--> Clinton, Hillary Rodham 10674.16
--> Carson, Benjamin S. 8043.00
--> Sanders, Bernard 6175.43
--> Cruz, Rafael Edward 'Ted' 5563.00
--> Bush, Jeb 5400.00
--> Fiorina, Carly 5400.00
--> Paul, Rand 4445.07
--> Huckabee, Mike 500.00
--> Kasich, John R. 500.00
--> Walker, Scott 500.00
;

-- 2. For each of the local zip codes, and each of the candidates,
-- show the number of contributions (if at least one contribution was
-- made).  Order by zip code and then number of contributions.
-- NOTE: In this and the following questions, by 'local', I mean in 
-- the zip codes that begin with 93933, 93901, 93905, 93955, or 93906.
select substr(zip,1,5) as replace, CANDIDATE.name, count(contributor.contbr_id) from candidate
join contribution on contribution.cand_id = CANDIDATE.CAND_ID 
join contributor on contribution.CONTBR_ID = contributor.CONTBR_ID 
where substr(zip,1,5) in ('93933', '93901', '93905', '93955', '93906')
group by replace, CANDIDATE.name order by replace asc, count(contributor.contbr_id) desc;
--> 93901 Clinton, Hillary Rodham 30
--> 93901 Sanders, Bernard 28
--> 93901 Rubio, Marco 25
--> 93901 Carson, Benjamin S. 15
--> 93901 Cruz, Rafael Edward 'Ted' 7
--> 93901 Fiorina, Carly 2
--> 93901 Kasich, John R. 2
--> 93905 Sanders, Bernard 7
--> 93905 Cruz, Rafael Edward 'Ted' 2
--> 93905 Clinton, Hillary Rodham 1
--> 93905 Huckabee, Mike 1
--> 93906 Sanders, Bernard 28
--> 93906 Rubio, Marco 12
--> 93906 Clinton, Hillary Rodham 9
--> 93906 Cruz, Rafael Edward 'Ted' 6
--> 93906 Carson, Benjamin S. 2
--> 93906 Paul, Rand 2
--> 93906 Walker, Scott 2
--> 93933 Sanders, Bernard 48
--> 93933 Cruz, Rafael Edward 'Ted' 27
--> 93933 Clinton, Hillary Rodham 10
--> 93933 Carson, Benjamin S. 3
--> 93955 Sanders, Bernard 56
--> 93955 Clinton, Hillary Rodham 12
--> 93955 Paul, Rand 6
--> 93955 Carson, Benjamin S. 5
--> 93955 Cruz, Rafael Edward 'Ted' 4
;

-- 3. Who are the top 10 local contributors in terms of total amount
-- contributed?  Show the contributor names and the amount they spent.
-- sort by total amount in descending order, then name ascending.
select contributor.name, sum(contribution.amount) from contributor
join contribution on contribution.CONTBR_ID = contributor.CONTBR_ID 
where contributor.zip like '93933%' or contributor.zip like '93901%' or contributor.zip like '93905%' or contributor.zip like '93955%' or contributor.zip like '93906%'
group by contributor.name
order by sum(contribution.amount) desc, contributor.name asc limit 10;
--> TAYLOR, KATHRYN HAGLER MRS. 8100.00
--> DREVER, ROBYN L. MRS. 5400.00
--> TAYLOR, KYLE MR. 5400.00
--> DREVER, MARK J. MR. 4800.00
--> HEVIA, CLAUDIA 2700.00
--> LEWIS, ANDRE 2700.00
--> PATEL, BHAVIN 2700.00
--> TAYLOR, STEVEN BRUCE MR. 2700.00
--> LESIKAR, DAVID 900.00
--> SCHAEFER, KARL 797.35
;

-- 4. Show date and amount for all contributions from 'BATTS, ERIC'.
-- Order by amount, decreasing.
select contribution.date, contribution.amount from contribution
join contributor on contribution.CONTBR_ID = contributor.CONTBR_ID 
where contributor.name = 'BATTS, ERIC' 
order by contribution.amount desc;
--> 30-JAN-16 125.00
--> 25-JAN-16 100.00
--> 28-SEP-15 76.00
--> 22-JAN-16 65.00
--> 08-OCT-15 57.00
--> 28-DEC-15 57.00
--> 20-NOV-15 57.00
--> 31-DEC-15 57.00
--> 08-OCT-15 57.00
--> 08-NOV-15 57.00
--> 28-DEC-15 57.00
--> 30-JAN-16 57.00
--> 23-JAN-16 57.00
--> 16-JAN-16 57.00
--> 20-JAN-16 57.00
--> 12-JAN-16 57.00
--> 18-JAN-16 57.00
--> 29-JAN-16 57.00
--> 27-JAN-16 57.00
--> 12-NOV-15 50.00
--> 28-JUN-15 45.00
--> 04-DEC-15 25.00
--> 24-OCT-15 25.00
--> 20-DEC-15 25.00
--> 14-AUG-15 25.00
--> 01-NOV-15 25.00
--> 15-NOV-15 25.00
--> 13-OCT-15 25.00
--> 12-OCT-15 25.00
--> 23-OCT-15 25.00
--> 25-SEP-15 25.00
--> 31-AUG-15 25.00
--> 08-JAN-16 25.00
--> 17-JAN-16 25.00
--> 06-JAN-16 25.00
--> 09-JAN-16 25.00
--> 12-JAN-16 25.00
--> 19-JAN-16 25.00
--> 16-JAN-16 25.00
--> 13-JAN-16 25.00
--> 22-SEP-15 25.00
--> 17-SEP-15 25.00
--> 12-APR-15 25.00
--> 28-JAN-16 25.00
--> 14-JAN-16 25.00
--> 24-AUG-15 25.00
--> 13-JUN-15 18.00
--> 09-JUN-15 18.00
--> 13-JUN-15 18.00
--> 30-SEP-15 15.00
--> 26-JAN-16 10.00
--> 31-JAN-16 10.00
--> 31-JAN-16 10.00
--> 30-APR-15 10.00
--> 01-MAY-15 10.00
--> 10-JUL-15 10.00
--> 01-MAY-15 10.00
--> 02-DEC-15 5.00
--> 13-SEP-15 5.00
--> 30-DEC-15 5.00
--> 07-AUG-15 5.00
--> 02-OCT-15 5.00
--> 23-OCT-15 5.00
--> 02-NOV-15 5.00
--> 07-DEC-15 5.00
--> 04-NOV-15 5.00
--> 02-JUN-15 5.00
--> 25-JUN-15 5.00
--> 05-JUN-15 5.00
--> 30-JUN-15 5.00
--> 27-JUN-15 5.00
--> 02-JUL-15 5.00
--> 27-MAY-15 5.00
--> 19-AUG-15 5.00
--> 02-SEP-15 5.00
--> 16-SEP-15 5.00
--> 02-JAN-16 5.00
--> 21-JAN-16 5.00
--> 27-JAN-16 5.00
--> 15-JAN-16 5.00
--> 19-JAN-16 5.00
--> 15-APR-15 5.00
--> 02-MAY-15 5.00
--> 15-SEP-15 5.00
--> 22-JUN-15 5.00
--> 21-JAN-16 5.00
--> 19-JAN-16 5.00
--> 21-JAN-16 5.00
--> 13-JAN-16 5.00
--> 01-JUL-15 5.00
--> 02-AUG-15 5.00
--> 16-OCT-15 3.00
--> 26-OCT-15 3.00
--> 20-DEC-15 3.00
--> 10-SEP-15 3.00
--> 08-OCT-15 3.00
--> 07-DEC-15 3.00
--> 13-OCT-15 3.00
--> 25-JUN-15 3.00
--> 29-JUN-15 3.00
--> 13-JUN-15 3.00
--> 17-JUN-15 3.00
--> 22-JAN-16 3.00
--> 30-SEP-15 3.00
--> 22-JUL-15 3.00
--> 30-JUN-15 2.00
;

-- 5. On average, how many contributions did each contributor make?
-- Give a single number, rounded to one digit to the right of the
-- decimal point.
select round((select cast(count(amount) as float) from contribution)/(select count(name) from contributor),1);
--> 2.9
;

