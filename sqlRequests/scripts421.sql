alter table students add constraint age_constraint check (age >= 16);
alter table students alter column age default 20;

alter table students alter column name set not null;
alter table students add constraint name_unique unique (name);

alter table faculties add constraint name_color_unique unique (name, color);
