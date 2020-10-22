-- The following questions use the exam1 schema with 
--   tables student, course and enrollment.
--
-- 1.	What are the last and first names of students majoring in Biology and 
--    have a GPA between 3.0 and 3.5? The list should be in order by lastname.
select 1;

-- 2.	Summarize the student data by major.  For each major show 
--      the number of students  with that major and the average GPA 
--      of students.  The column headings should be 
--    “major”, “numberOfStudents”, and “averageGPA”. 
select 2; 

-- 3.	What are the last and first names of students who took 
--     “Discrete Math” with a grade of “B” or “A”?
select 3; 

-- 4.	Show a list of courses with courseid,  and the count of 
--      enrolled students.   If a course has no enrollments, 
--       a count of 0 should appear.  The list should be 
--    sorted by courseid.
select 4; 

-- 5.	Who are the students (list their lastname and firstname)
--      enrolled in ’Calculus 2’ in  semester 'Spring2017', and have
--      taken and passed with a grade of C or higher
--      the required prerequisite course ’Calculus 1’?   
--      List the students in order by lastname.
select 5; 

-- 6.	There are 3 professors who teach Calculus 1:  
--      Smith, Lupin and Fenwick.  
--    We want to compare the average Calculus 2 grade of their 
--    students to see which instructor does the best job of 
--    preparing students for Calc 2. 
--    If a student took Calculus 1 but did not take Calculus 2 
--    don’t count them.  The example output below shows that
--    10 of Smith’s calculus 1 students went on to calculus 2 
--    (not necessarily with Professor Smith and their average grade 
--     in calculus 2 is 2.8.  
--    Fenwick had 25 students in calculus 1 but none of them went on
--     to calculus 2.  
--    Example output.  Your answer will have the same Instructor 
--    names, but the values in column 2 and 3 will be different.
--
--    InstructorCaculus1   NumberTakingCalculus2   AverageGradeCalculus2
--    Smith	               10	                   2.8
--    Lupin	               30	                   3.5
--    Fenwick	           25	                   3.1
--
-- You may need to use the SQL case syntax to convert letter grades into numbers 
-- that can be averaged.  A letter grade of B should be 3.0 and a letter 
-- grade of C should be 2.0, etc.  
-- For example of CASE see https://www.h2database.com/html/grammar.html#case 
select 6; 


-- 7.	Write an SQL select that would return a transcript data for a student with
--    studentid 3729.  The result should show all courses (semester, courseid, name, 
--    credits, grade) for the student.  The list of courses should be chronological
--    order:  that is 2017 Spring comes before 2017 Summer, which comes before 
--    2017 Fall.  You will need to use a case syntax to get the courses into the 
--    correct sequence.   
select 7; 

-- 8. A student wants to enroll in Math 170 Discrete Mathematics.  The rule is 
--    the student cannot enroll in a course if the student has already taken and
--    passed, or if the student has taken the course 2 times.  
--    Write an SQL statement that returns a list of studentids who cannot enroll in 
--    Discrete Math.
select 8;

-- 9.	Write a SQL update statement that changes the grade for studentid 4456 who
--    enrolled in Discrete Math in semester ‘Spring2017’ and received a 'C' grade.
--    Your answer should be a single SQL update statement.
--    Use a predicate to verify that the current grade is 'C'.
update 9;

--  The following questions use the tables from exam 1 schema: 
--     product, pc, laptop and printer.
-- 10.	What PC models have a speed of at least 3.00?  
--      List the results in order by model.
select 10;

-- 11.	List all manufacturers that makes laptops with a hard disk 
--       of at least 100GB?  List in order by maker.
select 11;

-- 12.	Find the model number and price of all products (of any 
--      type) made by manufacturer B?  
--      The result should be in order by model number and then by
--      price (low to high)  [hint:  use a union]
select 12;

-- 13.	Find the model numbers for all color laser printers.  
--      Order by model.
select 13; 

-- 14.	Find those manufacturers that sell laptops but not pc’s. 
--      Sort result by maker.
select 14;

-- 15.	Find those hard-drive sizes that occur in two or more PC’s.   
--      Sort the list low to high. [hint: use group and having]
select 15; 

-- 16.	Find those pairs of PC models that have both the same speed 
--      and RAM.  The output should have 2 columns,  "model1" and
--      "model2".  A pair should be listed only once:  e.g. if 
--      (model1, model2) is in the result then (model2, model1) 
--      should not appear.   Sort the output by the first column.
select 16; 


-- 17.	Find the maker(s) of the color printer with the lowest 
--      price. Order by maker.
select 17; 

-- 18.	Find the manufacturer of PC’s with at least three different 
--      speeds.  Order by maker.
select 18;

-- 19.	Find those manufacturers of at least two different computers 
--     (PC’s or laptops)  with speeds of at least 2.80.  
--      Order the result by maker. 
select 19; 

-- 20.  Find the manufacturer(s) of the computer (PC or laptop) with
--      the highest speed.  
--      If there are multiple makers, order by maker.
select 20; 

