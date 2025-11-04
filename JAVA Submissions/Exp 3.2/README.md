Spring + Hibernate Examples (Experiment 3.2)

This workspace contains three example parts demonstrating:

- Part A: Dependency Injection in Spring using Java-based configuration
- Part B: Hibernate CRUD operations for a Student entity
- Part C: Transaction management with Spring + Hibernate (bank transfer example)

Assumptions and notes
- Java 8 (1.8) is used in the pom. Adjust `pom.xml` if you need a different Java version.
- MySQL is used for Parts B and C. Update `hibernate.cfg.xml` and `AppConfigTx` with correct DB URL, username, and password.
- The examples use simple console-based main classes for demonstration.

How to build
- From the project root, run:

```cmd
mvn clean compile
```

How to run each part (after building)
- Part A (Dependency Injection): run the `PartAApp` main class.
- Part B (Hibernate CRUD): run the `PartBApp` main class. Ensure MySQL database and credentials are set in `hibernate.cfg.xml`.
- Part C (Transactions): run the `PartCApp` main class. Configure DB credentials in `AppConfigTx`.

Next steps
- Update DB settings in `src/main/resources/hibernate.cfg.xml` (Part B) and in `AppConfigTx` (Part C).
- Create the database (e.g., `experiment_db`) and grant permissions for the configured user.
