create table tb_answer(
id int primary key auto_increment,
content_answer varchar(10),
marks float,
question_id int,
student_id int,
foreign key(question_id) references tb_questions(id)
);
alter table tb_answer add foreign key (student_id) references tb_users(id);