-------------------------------------------------------------------------------
-- Homework 3 SQL questions
--
-- Edit this file by adding your SQL below each question.
-- Do not put "limit 10" in your queries unless specifically requested.
-------------------------------------------------------------------------------
runscript from '1994-census-summary.sql';
-------------------------------------------------------------
-- The following queries are based on the 1994 census data. 
-------------------------------------------------------------

-- 1. what is the average age of people from China?
select AVG(age) from census where native_country = 'China';
--> 42
;

-- 2. what is the average age of people from Taiwan?
select AVG(age) from census where native_country = 'Taiwan';
--> 33
;

-- 3. which native countries have 'land' in their name?
select distinct native_country from census where native_country like '%land%';
--> England
--> Holand_Netherlands
--> Ireland
--> Poland
--> Scotland
--> Thailand
;

-- 4. what values of the attribute 'marital_status' appear in the data set? Don't show duplicates.
select distinct marital_status from census;
--> Divorced
--> Married_AF_spouse
--> Married_civ_spouse
--> Married_spouse_absent
--> Never_married
--> Separated
--> Widowed
;

-- 5. what is the average hours per week of work for people from France between 18 and 25? (inclusive)
select AVG(hours_per_week) from census where native_country = 'France' and (age < 25 and age > 18);
--> 27
;

-- 6. which people are from Laos, Cuba, or Peru?  Show only 10 lines.
select * from census where native_country = 'Laos' or native_country = 'Cuba' or native_country = 'Peru' limit 10;
--> 5 28 Private 338409 Bachelors 13 Married_civ_spouse Prof_specialty Wife Black Female 0 0 40 Cuba <=50K
--> 82 52 Private 276515 Bachelors 13 Married_civ_spouse Other_service Husband White Male 0 0 40 Cuba <=50K
--> 305 24 Private 303296 Some_college 10 Married_civ_spouse Adm_clerical Wife Asian_Pac_Islander Female 0 0 40 Laos <=50K
--> 639 47 Self_emp_inc 248145 5th_6th 3 Married_civ_spouse Transport_moving Husband White Male 0 0 50 Cuba <=50K
--> 702 31 Private 243605 Bachelors 13 Widowed Sales Unmarried White Female 0 1380 40 Cuba <=50K
--> 1238 41 Self_emp_not_inc 209344 HS_grad 9 Married_civ_spouse Sales Other_relative White Female 0 0 40 Cuba <=50K
--> 1431 49 Private 147322 Assoc_voc 11 Married_civ_spouse Machine_op_inspct Wife White Female 0 0 40 Peru <=50K
--> 1664 62 Private 273454 7th_8th 4 Married_civ_spouse Protective_serv Husband White Male 0 0 40 Cuba <=50K
--> 2019 39 Private 505119 Masters 14 Married_civ_spouse Exec_managerial Husband White Male 0 0 40 Cuba >50K
--> 2230 51 Private 224763 7th_8th 4 Married_civ_spouse Transport_moving Husband White Male 0 0 40 Cuba <=50K
;

-- 7. what is the average education years for people having a native country that is not the US?
select AVG(education_num) from census where not  native_country = 'United_States';
--> 9
;

-- 8. how many executive managers in the data set?
select count(occupation) from census where  occupation like '%Exec_managerial%';
--> 4066
;

-- 9. how many executive managers have more than 12 years of education?
select COUNT(occupation) from census where occupation = 'Exec_managerial' and education_num > 12;
--> 1977
;

-- 10. how many different native countries are found in the data set?
select count(distinct(native_country)) from census;
--> 42
;

--------------------------------------------------------------------------------------
-- The following queries are based on the courses-ddl.sql and courses-small.sql data
--------------------------------------------------------------------------------------

drop table census;
runscript from 'courses-ddl.sql';
runscript from 'courses-small.sql';

-- 11. what are the names of all students who have taken some course? Don't show duplicates.
select distinct(name) from student where tot_cred > 0;
--> Aoi
--> Bourikas
--> Brandt
--> Brown
--> Chavez
--> Levy
--> Peltier
--> Sanchez
--> Shankar
--> Tanaka
--> Williams
--> Zhang
;

-- 12. what are the names of departments that offer 4-credit courses? Don't list duplicates.
select distinct(dept_name) from course where credits = 4; 
--> Biology
--> Comp. Sci.
--> Physics
;

-- 13. What are the names and IDs of all students who have received an A in a computer science class?
select distinct student.name, student.id from student inner join takes on student.id = takes.id where takes.grade = 'A' and student.dept_name = 'Comp. Sci.';
--> Brown 76543
--> Shankar 12345
--> Zhang 00128
;

-- 14. How many B grades have been given to physics majors?
select count(*) from takes inner join student on takes.id = student.id where student.dept_name = 'Physics' AND (takes.grade ='B') 
--> 1
;

-- 15. What is the average total credits of students who have taken CS-319?
select AVG(tot_cred) from student inner join takes on takes.id = student.id where takes.course_id = 'CS-319';
--> 52
;

-- 16. What is the average total credits of students who have taken CS-101?
select AVG(tot_cred) from student inner join takes on takes.id = student.id where takes.course_id = 'CS-101';
--> 62.2857142857142857142857143
;

-- 17. What are the course IDs of courses taught by instructor Katz?
select course_id from teaches inner join instructor on teaches.id= instructor.id where instructor.name = 'Katz';
--> CS-101
--> CS-319
;

-- 18. What are the course IDs of all courses offered by instructor Crick's department?
select course_id from course inner join instructor on course.dept_name= instructor.dept_name where instructor.dept_name= 'Biology';
--> BIO-101
--> BIO-301
--> BIO-399
;

-- 19. What is the course_id, semester, and year of sections of computer science courses?
--     Don't show duplicates.
select distinct course_id, semester, year from teaches where course_id like 'CS%';
--> CS-101 Fall 2009
--> CS-101 Spring 2010
--> CS-190 Spring 2009
--> CS-315 Spring 2010
--> CS-319 Spring 2010
--> CS-347 Fall 2009
;

-- 20. What are the names of students who have taken a class taught by instructor Srinivasan?
select distinct student.name 
from instructor natural join teaches join takes 
on takes.course_id = teaches.course_id and takes.sec_id=teaches.sec_id and takes.year = teaches.year and takes.semester=teaches.semester 
join student on student.ID = takes.ID
where instructor.name = 'Srinivasan';
--> Bourikas
--> Brown
--> Levy
--> Shankar
--> Williams
--> Zhang
;