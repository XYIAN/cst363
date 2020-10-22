runscript from '1994-census-summary.sql';

-- Below each question, fill in your SQL, remembering to use 'limit 10'
-- at the end of each line.
-- I have provided the answer for question 1 so that you can see what I want.

-- 1. show the age of the males in the data set
;
select age from census where sex='Male' limit 10;
--> 39
--> 50
--> 38
--> 53
--> 52
--> 42
--> 37
--> 30
--> 32
--> 40
;

-- 2. show all columns for the people with a usid between 100 and 120
;

Select * from census where usid > 99 and usid < 120 limit 10; 
--> 100 32 Federal_gov 249409 HS_grad 9 Never_married Other_service Own_child Black Male 0 0 40 United_States <=50K
--> 101 76 Private 124191 Masters 14 Married_civ_spouse Exec_managerial Husband White Male 0 0 40 United_States >50K
--> 102 44 Private 198282 Bachelors 13 Married_civ_spouse Exec_managerial Husband White Male 15024 0 60 United_States >50K
--> 103 47 Self_emp_not_inc 149116 Masters 14 Never_married Prof_specialty Not_in_family White Female 0 0 50 United_States <=50K
--> 104 20 Private 188300 Some_college 10 Never_married Tech_support Own_child White Female 0 0 40 United_States <=50K
--> 105 29 Private 103432 HS_grad 9 Never_married Craft_repair Not_in_family White Male 0 0 40 United_States <=50K
--> 106 32 Self_emp_inc 317660 HS_grad 9 Married_civ_spouse Craft_repair Husband White Male 7688 0 40 United_States >50K
--> 107 17 NA 304873 10th 6 Never_married NA Own_child White Female 34095 0 32 United_States <=50K
--> 108 30 Private 194901 11th 7 Never_married Handlers_cleaners Own_child White Male 0 0 40 United_States <=50K
--> 109 31 Local_gov 189265 HS_grad 9 Never_married Adm_clerical Not_in_family White Female 0 0 40 United_States <=50K
;

-- 3. show the workclass and education of people under 20
;
select workclass, education from census where age < 20 limit 10;
--> Private HS_grad
--> Private HS_grad
--> Private HS_grad
--> Private Some_college
--> Private 11th
--> Private HS_grad
--> NA 10th
--> Private Some_college
--> Private Some_college
--> Private 11th
;

-- 4. show all columns for people over 80
;
select * from census where age > 80 limit 10;
--> 223 90 Private 51744 HS_grad 9 Never_married Other_service Not_in_family Black Male 0 2206 40 United_States <=50K
--> 919 81 Self_emp_not_inc 136063 HS_grad 9 Married_civ_spouse Exec_managerial Husband White Male 0 0 30 United_States <=50K
--> 1041 90 Private 137018 HS_grad 9 Never_married Other_service Not_in_family White Female 0 0 40 United_States <=50K
--> 1169 88 Self_emp_not_inc 206291 Prof_school 15 Married_civ_spouse Prof_specialty Husband White Male 0 0 40 United_States <=50K
--> 1936 90 Private 221832 Bachelors 13 Married_civ_spouse Exec_managerial Husband White Male 0 0 45 United_States <=50K
--> 2304 90 Private 52386 Some_college 10 Never_married Other_service Not_in_family Asian_Pac_Islander Male 0 0 35 United_States <=50K
--> 2892 90 Private 171956 Some_college 10 Separated Adm_clerical Own_child White Female 0 0 40 Puerto_Rico <=50K
--> 2907 81 Private 114670 9th 5 Widowed Priv_house_serv Not_in_family Black Female 2062 0 5 United_States <=50K
--> 3212 82 NA 29441 7th_8th 4 Widowed NA Not_in_family White Male 0 0 5 United_States <=50K
--> 3538 81 Self_emp_not_inc 137018 HS_grad 9 Widowed Adm_clerical Not_in_family White Female 0 0 20 United_States <=50K
;

-- 5. show the sex of people who are over 80 and have never married
;
select sex from census where age > 80 and marital_status = 'Never_married' limit 10;
--> Male
--> Female
--> Male
--> Male
--> Male
--> Female
--> Male
--> Male
--> Female
--> Male
;

-- 6. show the number of years of education for people having education of 'Some_college'
;
select education_num from census where education = 'Some_college' limit 10;
--> 10
--> 10
--> 10
--> 10
--> 10
--> 10
--> 10
--> 10
--> 10
--> 10
;

-- 7. show the age, sex, years of education, and capital gains of people with capital gains > 10000
;
select age, sex, education_num, capital_gain from census where capital_gain > 1000 limit 10;
--> 31 Female 14 14084
--> 44 Female 9 14344
--> 44 Male 13 15024
--> 17 Female 6 34095
--> 40 Female 14 14084
--> 58 Female 9 15024
--> 57 Male 14 15024
--> 46 Male 16 15024
--> 38 Male 13 15024
--> 38 Male 13 15024
;

-- 8. show the sex, age, and marital status for people in the armed forces
;
select sex, age, marital_status from census where occupation = 'Armed_Forces' limit 10;
--> Male 24 Never_married
--> Male 29 Never_married
--> Male 39 Never_married
--> Male 24 Never_married
--> Male 34 Married_civ_spouse
--> Male 23 Never_married
--> Male 30 Married_civ_spouse
--> Male 46 Married_civ_spouse
--> Male 23 Never_married
;

-- 9. show the number of years of education for people over 40 years old who are in the armed forces
;
select education_num from census where age > 40 and occupation = 'Armed_Forces' limit 10;
--> 14
;

-- 10. show the marital status of people of age 50 with relationship 'Not_in_family'
;
select merital_status from census where age = 50 and relationship = 'Not_in_family' limit 10;
--> Divorced
--> Divorced
--> Divorced
--> Divorced
--> Separated
--> Divorced
--> Divorced
--> Divorced
--> Divorced
--> Never_married
;

-- 11. show all columns for 17 year olds with less than 5 years of education
;
select * from census where age = 17 and education_num < 5 limit 10;
--> 336 17 Private 270942 5th_6th 3 Never_married Other_service Other_relative White Male 0 0 48 Mexico <=50K
--> 9972 17 Private 168807 7th_8th 4 Never_married Craft_repair Not_in_family White Male 0 0 45 United_States <=50K
--> 17475 17 Private 168203 7th_8th 4 Never_married Farming_fishing Other_relative Other Male 0 0 40 Mexico <=50K
--> 28771 17 Private 46402 7th_8th 4 Never_married Sales Own_child White Male 0 0 8 United_States <=50K
;

-- 12. show the occupation of women under the age of 40 with a Doctorate degree
;
select occupation from census where sex = 'Female' and age < 40 and education = 'Doctorate' limit 10;
--> Prof_specialty
--> Prof_specialty
--> Prof_specialty
--> Exec_managerial
--> Tech_support
--> Adm_clerical
--> Prof_specialty
--> Prof_specialty
--> Prof_specialty
--> Prof_specialty
;

-- 13. show the workclass of people under the age of 25 with a Masters degree
;
select workclass from census where age < 25 and education = 'Masters' limit 10;
--> Private
--> State_gov
--> State_gov
--> Private
--> Private
--> Local_gov
--> Private
--> State_gov
--> Private
--> Private
;

-- 14. show all columns for people under the age of 21 with a Masters degree
;
select * from census where age < 21 and education = 'Masters' limit 10;
--> 12184 18 Local_gov 155905 Masters 14 Never_married Prof_specialty Own_child White Female 0 0 60 United_States <=50K
--> 31053 20 Private 190227 Masters 14 Never_married Exec_managerial Own_child White Male 0 0 25 United_States <=50K
;

-- 15. show the age of females with either a bachelors or a masters degree
;
select age from census where sex = 'Female' and (education = 'Masters' or education = 'Bachelors') limit 10;
--> 28
--> 37
--> 31
--> 23
--> 43
--> 44
--> 31
--> 47
--> 47
--> 33
;