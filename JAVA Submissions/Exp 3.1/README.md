Experiment 3.1 - Web Applications Using Servlets and JSP

This project contains sample implementations for the following parts:

Part a: User Login Using Servlet and HTML Form
Part b: Display Employee Records with JDBC and Servlet Integration
Part c: Student Attendance Portal Using JSP and Servlet

How to build and run (deploy to Apache Tomcat):

1. Build the WAR using Maven:

   mvn clean package

2. Deploy the generated WAR from target/exp31-servlets.war to your Tomcat's webapps folder.

3. Start Tomcat and open:

   http://localhost:8080/exp31-servlets/

Notes:
- This project uses an embedded H2 database (in-memory) for demonstration. The DB is initialized at application startup.
- Endpoints:
  - /login.html -> Login form (POST to /login)
  - /employees -> Lists employees or search by ?id={EmpID}
  - /attendance.jsp -> JSP form to add attendance (POST to /attendance)

If you need an embedded runner or additional setup (e.g., using an external DB), I can add instructions or code for that.