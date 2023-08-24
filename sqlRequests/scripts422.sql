create table humans (id real, name varchar, age int, hasDrLicense boolean, car_id int references cars (id));

create table cars (id real, brand varchar, model varchar, price int);