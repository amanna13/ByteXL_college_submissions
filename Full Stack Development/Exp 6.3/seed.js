require('dotenv').config();
const mongoose = require('mongoose');
const Account = require('./models/Account');

const MONGO = process.env.MONGO_URI || 'mongodb://127.0.0.1:27017/exp6_3_db';

async function seed() {
  await mongoose.connect(MONGO, { useNewUrlParser: true, useUnifiedTopology: true });
  console.log('Connected to MongoDB for seeding');

  // Remove existing demo accounts with these names
  await Account.deleteMany({ name: { $in: ['Alice', 'Bob'] } });

  const a = new Account({ name: 'Alice', balance: 1000 });
  const b = new Account({ name: 'Bob', balance: 500 });
  await a.save();
  await b.save();

  console.log('Seeded accounts:');
  console.log({ aliceId: a._id.toString(), bobId: b._id.toString() });
  process.exit(0);
}

seed().catch(err => {
  console.error(err);
  process.exit(1);
});
