// Base Class
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  getDetails() {
    return `Name: ${this.name}, Age: ${this.age}`;
  }
}

// Student Subclass
class Student extends Person {
  constructor(name, age, course) {
    super(name, age); // Call parent constructor
    this.course = course;
  }

  // Override getDetails method
  getDetails() {
    return `${super.getDetails()}, Course: ${this.course}`;
  }
}

// Teacher Subclass
class Teacher extends Person {
  constructor(name, age, subject) {
    super(name, age); // Call parent constructor
    this.subject = subject;
  }

  // Override getDetails method
  getDetails() {
    return `${super.getDetails()}, Subject: ${this.subject}`;
  }
}

// Demonstration
const student1 = new Student("Alice", 20, "Computer Science");
const teacher1 = new Teacher("Mr. Smith", 40, "Mathematics");

console.log(student1.getDetails()); 
console.log(teacher1.getDetails()); 
