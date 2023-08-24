select student.name, student.age, faculty.name as faculty_name
from student
inner join faculty on student.faculty_id = faculty.id;


select student.id as stuent_id, student.name as student_name, avatar.id as avatar_id
from avatar
inner join student on avatar.student_id = student.id;