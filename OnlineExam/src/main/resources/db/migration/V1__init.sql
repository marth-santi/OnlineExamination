CREATE TABLE tb_users(
  id INT NOT NULL AUTO_INCREMENT,
  fullname VARCHAR(255),
  mobile VARCHAR(255),
  email VARCHAR(255),
  address VARCHAR(255),
  username VARCHAR(255),
  pass VARCHAR(255),
  roles SMALLINT,
  confirm BIT(1),
  PRIMARY KEY (id));
  
CREATE TABLE tb_subject (
  id INT NOT NULL AUTO_INCREMENT,
  subject_name VARCHAR(255),
  start_date TIMESTAMP,
  PRIMARY KEY (id));
  
CREATE TABLE tb_marks (
  student_id INT NOT NULL,
  subject_id INT NOT NULL,
  marks SMALLINT,
  test_date TIMESTAMP,
  PRIMARY KEY (student_id, subject_id),
  CONSTRAINT tb_marks_ibfk_1
    FOREIGN KEY (student_id)
    REFERENCES tb_users(id),
  CONSTRAINT tb_marks_ibfk_2
    FOREIGN KEY (subject_id)
    REFERENCES tb_subject (id));
    
CREATE TABLE tb_questions (
  id INT NOT NULL AUTO_INCREMENT,
  question VARCHAR(255),
  op1 VARCHAR(255),
  op2 VARCHAR(255),
  op3 VARCHAR(255),
  op4 VARCHAR(255),
  answer VARCHAR(255),
  subject_id INT,
  PRIMARY KEY (id),
  CONSTRAINT tb_questions_ibfk_1
    FOREIGN KEY (subject_id)
    REFERENCES tb_subject (id));