# Java-Based Quiz Management System — Web Application

A complete beginner-friendly Java web application using JSP, Servlets, JDBC and MySQL.

## Features
- User registration and login
- Dashboard
- MCQ quiz interface
- Countdown timer
- Automatic answer validation
- Automatic score/percentage calculation
- Result page
- Quiz history
- Admin question management
- Add, edit and delete questions
- MySQL database

## Technology
Java 8+, JSP, Servlets, JDBC, MySQL, HTML5, CSS3, JavaScript, Apache Tomcat 9.

## Setup
1. Install JDK and Apache Tomcat 9.
2. Install MySQL.
3. Run `database/quiz_management.sql`.
4. Edit database credentials in `src/main/java/com/quiz/util/DBConnection.java`.
5. Add MySQL Connector/J and Servlet API dependencies if your IDE does not already provide them.
6. Import the project as a Dynamic Web Project/Maven web project.
7. Deploy on Tomcat.
8. Open `/JavaQuizManagement/login.jsp`.

## Demo accounts
Admin: admin@quiz.com / admin123
Student: student@quiz.com / student123

The SQL file creates these accounts.

## Project structure
src/main/java/com/quiz/
  model/
  util/
  dao/
  servlet/

src/main/webapp/
  css/
  js/
  WEB-INF/
  *.jsp

database/
  quiz_management.sql
