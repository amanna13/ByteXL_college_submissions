## Experiment 2.3 - Java Lambda & Stream Examples

This folder contains three small demos showing lambda expressions and stream operations:

Files:
- Employee.java
- EmployeeSortDemo.java         (Part A: sort Employee objects using lambda/Comparator)
- Student.java
- StudentStreamDemo.java       (Part B: filter students with marks > 75, sort, map names)
- Product.java
- ProductStreamDemo.java       (Part C: grouping, maxBy per category, average price)

How to compile and run (Windows cmd):

1. Open cmd and change directory to this folder (use quotes because path contains spaces):

   cd /d "c:\Users\ashis\Documents\CODING\byteXL\JAVA Submissions\Exp 2.3"

2. Compile all Java files:

   javac *.java

3. Run the demos (run them one at a time):

   java EmployeeSortDemo
   java StudentStreamDemo
   java ProductStreamDemo

Notes:
- Requires a Java JDK (javac/java) on PATH.
- These are simple console demos intended for learning lambda expressions and stream APIs.
