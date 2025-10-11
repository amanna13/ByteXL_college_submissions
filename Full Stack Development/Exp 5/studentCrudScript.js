const mongoose = require('mongoose');
const Student = require('./models/Student');
require('dotenv').config();

async function runStudentCRUD() {
  try {
    await mongoose.connect(process.env.MONGO_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true
    });
    console.log('Connected to MongoDB');


    // 1. Create 4 students
    const studentData = [
      { name: 'John Doe', age: 21, course: 'Computer Science' },
      { name: 'Alice Smith', age: 20, course: 'Physics' },
      { name: 'Bob Johnson', age: 22, course: 'Mathematics' },
      { name: 'Carol White', age: 23, course: 'Chemistry' }
    ];
    const newStudents = await Student.insertMany(studentData);
    console.log('Added:', newStudents);

    // 2. Retrieve all students
    const students = await Student.find();
    console.log('All Students:', students);

    // 3. Update 3 students by ID
    for (let i = 0; i < 3 && i < students.length; i++) {
      const updated = await Student.findByIdAndUpdate(
        students[i]._id,
        { name: students[i].name + ' Updated', age: students[i].age + 1 },
        { new: true, runValidators: true }
      );
      console.log('Updated:', updated);
    }

    // 4. Delete 2 students by ID
    for (let i = 0; i < 2 && i < students.length; i++) {
      const deleted = await Student.findByIdAndDelete(students[i]._id);
      console.log('Deleted:', deleted);
    }

    await mongoose.disconnect();
    console.log('Disconnected from MongoDB');
  } catch (err) {
    console.error('Error:', err.message);
    process.exit(1);
  }
}

runStudentCRUD();
