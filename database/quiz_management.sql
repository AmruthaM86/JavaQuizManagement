CREATE DATABASE IF NOT EXISTS quiz_management;
USE quiz_management;

DROP TABLE IF EXISTS quiz_answers;
DROP TABLE IF EXISTS quiz_results;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'STUDENT'
);

CREATE TABLE questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question_text VARCHAR(500) NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_option CHAR(1) NOT NULL
);

CREATE TABLE quiz_results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    score INT NOT NULL,
    total_questions INT NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,
    taken_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE quiz_answers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    result_id INT NOT NULL,
    question_id INT NOT NULL,
    selected_option CHAR(1),
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (result_id) REFERENCES quiz_results(id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

INSERT INTO users(name,email,password,role) VALUES
('Administrator','admin@quiz.com','admin123','ADMIN'),
('Demo Student','student123','student123','STUDENT');

INSERT INTO questions(question_text,option_a,option_b,option_c,option_d,correct_option) VALUES
('Which keyword is used to inherit a class in Java?','this','extends','implements','super','B'),
('Which collection stores key-value pairs in Java?','ArrayList','HashSet','HashMap','Queue','C'),
('Which method is the entry point of a Java application?','start()','run()','main()','execute()','C'),
('Which SQL command is used to retrieve data?','INSERT','UPDATE','SELECT','DELETE','C'),
('Which OOP concept hides internal implementation details?','Inheritance','Encapsulation','Polymorphism','Abstraction','D'),
('Which Java API is commonly used to connect to relational databases?','JDBC','JSP','JVM','JRE','A'),
('Which tag is used to create a hyperlink in HTML?','<p>','<a>','<link>','<h1>','B'),
('Which CSS property changes text color?','font-style','background','color','text-size','C'),
('Which HTTP method is normally used to submit form data for creation?','GET','POST','TRACE','HEAD','B'),
('Which component processes HTTP requests in a Java web application?','Servlet','JFrame','Scanner','Thread','A');
