-- liquibase formatted sql

-- changeset egor:1
create index students_name_indx on students (name);
create index faculties_nameAndColor_indx on faculties (name, color);