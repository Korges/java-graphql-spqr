insert into STUDENT values(1,'Harry','Potter');
insert into STUDENT values(2,'Ron','Weasley');
insert into STUDENT values(3,'Hermiona','Granger');
insert into STUDENT values(4,'Ginny','Weasley');
insert into STUDENT values(5,'Draco','Malfoy');
insert into STUDENT values(6,'Luna','Lovegood');
insert into STUDENT values(7,'Neville','Longbottom');
insert into STUDENT values(8,'Cho','Chang');
insert into STUDENT values(9,'Fred','Potter');
insert into STUDENT values(10,'George','Potter');

insert into TEACHER values(1,'Albus','Dumbledore');
insert into TEACHER values(2,'Severus','Snape');

insert into SUBJECT values(1, 'Transmutation', 1);
insert into SUBJECT values(2, 'Potions', 2);
insert into SUBJECT values(3, 'Defense against Black Magic', 2);

insert into LECTURE values(1, 'Forbidden spells', 2);
insert into LECTURE values(2, 'Dangerous animals', 2);
insert into LECTURE values(3, 'Dragons', 2);
insert into LECTURE values(4, 'Multi-juice potion', 2);
insert into LECTURE values(5, 'Bezoar', 2);
insert into LECTURE values(6, 'Felix Felicis', 2);
insert into LECTURE values(7, 'Transmutation of objects I', 1);
insert into LECTURE values(8, 'Transmutation of objects II', 1);

insert into STUDENT_SUBJECT_LIST values(1, 1);
insert into STUDENT_SUBJECT_LIST values(1, 2);
insert into STUDENT_SUBJECT_LIST values(2, 1);
insert into STUDENT_SUBJECT_LIST values(2, 2);
insert into STUDENT_SUBJECT_LIST values(3, 1);
insert into STUDENT_SUBJECT_LIST values(3, 2);
insert into STUDENT_SUBJECT_LIST values(4, 1);
insert into STUDENT_SUBJECT_LIST values(4, 2);
insert into STUDENT_SUBJECT_LIST values(5, 1);
insert into STUDENT_SUBJECT_LIST values(5, 2);
insert into STUDENT_SUBJECT_LIST values(6, 1);
insert into STUDENT_SUBJECT_LIST values(6, 3);
insert into STUDENT_SUBJECT_LIST values(7, 1);
insert into STUDENT_SUBJECT_LIST values(7, 3);
insert into STUDENT_SUBJECT_LIST values(8, 1);
insert into STUDENT_SUBJECT_LIST values(8, 3);
insert into STUDENT_SUBJECT_LIST values(9, 1);
insert into STUDENT_SUBJECT_LIST values(9, 3);
insert into STUDENT_SUBJECT_LIST values(10, 1);
insert into STUDENT_SUBJECT_LIST values(10, 3);

insert into SUBJECT_LECTURE_LIST values(3, 1);
insert into SUBJECT_LECTURE_LIST values(3, 2);
insert into SUBJECT_LECTURE_LIST values(3, 3);
insert into SUBJECT_LECTURE_LIST values(2, 4);
insert into SUBJECT_LECTURE_LIST values(2, 5);
insert into SUBJECT_LECTURE_LIST values(2, 6);
insert into SUBJECT_LECTURE_LIST values(1, 7);
insert into SUBJECT_LECTURE_LIST values(1, 8);

insert into TEACHER_SUBJECT_LIST values(1, 1);
insert into TEACHER_SUBJECT_LIST values(2, 2);
insert into TEACHER_SUBJECT_LIST values(2, 3);