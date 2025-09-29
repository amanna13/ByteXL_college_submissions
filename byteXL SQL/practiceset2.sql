CREATE DATABASE working;
USE working;
CREATE TABLE Departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    dept_id INT NOT NULL,
    FOREIGN KEY (dept_id) REFERENCES Departments(dept_id)
);
INSERT INTO Departments (dept_id, dept_name) VALUES (1, 'Computer Science');
INSERT INTO Departments (dept_id, dept_name) VALUES (2, 'Electrical');
INSERT INTO Departments (dept_id, dept_name) VALUES (3, 'Mechanical');
INSERT INTO Departments (dept_id, dept_name) VALUES (4, 'Civil');
INSERT INTO Departments (dept_id, dept_name) VALUES (5, 'Electronics');
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (101, 'DBMS', 1);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (102, 'Operating Systems', 1);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (103, 'Power Systems', 2);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (104, 'Digital Circuits', 2);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (105, 'Thermodynamics', 3);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (106, 'Fluid Mechanics', 3);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (107, 'Structural Engineering', 4);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (108, 'Surveying', 4);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (109, 'Embedded Systems', 5);
INSERT INTO Courses (course_id, course_name, dept_id) VALUES (110, 'VLSI Design', 5);
DESCRIBE Departments;
SELECT * FROM Departments;
SELECT dept_name
FROM Departments
WHERE dept_id IN (
    SELECT dept_id
    FROM Courses
    GROUP BY dept_id
    HAVING COUNT(course_id) >=2 
);
CREATE USER 'viewer_user'@'localhost' identified by 'Yadav@2005';

GRANT SELECT ON COURSES TO 'viewer_user'@'localhost'; 
FLUSH PRIVILEGES;
SHOW GRANTS;
