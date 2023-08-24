select students.name, students.age, faculties.name as faculty_name
from students
inner join faculties on students.faculty_id = faculties.id;


select students.id as stuent_id, students.name as student_name, avatars.id as avatar_id
from avatars
inner join students on avatars.student_id = students.id;