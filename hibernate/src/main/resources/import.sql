INSERT INTO course (id, name, created_date, last_updated_date, deleted) VALUES (1001, 'First course', sysdate(), sysdate(), false);
INSERT INTO course (id, name, created_date, last_updated_date, deleted) VALUES (1002, 'Second course', sysdate(), sysdate(), false);
INSERT INTO course (id, name, created_date, last_updated_date, deleted) VALUES (1003, 'Third course', sysdate(), sysdate(), false);


INSERT INTO passport (id, number) VALUES (3001, 'E12345');
INSERT INTO passport (id, number) VALUES (3002, 'L989124');
INSERT INTO passport (id, number) VALUES (3003, 'Z45745');


INSERT INTO student (id, name, passport_id) VALUES (2001, 'Alex', 3001);
INSERT INTO student (id, name, passport_id) VALUES (2002, 'Greg', 3002);
INSERT INTO student (id, name, passport_id) VALUES (2003, 'Felix', 3003);


INSERT INTO review (id, rating, description, course_id) VALUES (4001, 'FIVE', 'Awesome', 1001);
INSERT INTO review (id, rating, description, course_id) VALUES (4002, 'TWO', 'Very bad', 1002);
INSERT INTO review (id, rating, description, course_id) VALUES (4003, 'FOUR', 'Good course', 1001);

INSERT INTO COURSE_STUDENT (student_id, course_id) VALUES (2001, 1001);
INSERT INTO COURSE_STUDENT (student_id, course_id) VALUES (2002, 1001);
INSERT INTO COURSE_STUDENT (student_id, course_id) VALUES (2003, 1002);
INSERT INTO COURSE_STUDENT (student_id, course_id) VALUES (2001, 1002);
