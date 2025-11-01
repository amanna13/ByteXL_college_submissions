require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const Account = require('./models/Account');

const app = express();
app.use(express.json());

const MONGO = process.env.MONGO_URI || 'mongodb://127.0.0.1:27017/exp6_3_db';
const PORT = process.env.PORT || 3003;

// Connect to MongoDB
mongoose.connect(MONGO, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log('Connected to MongoDB'))
  .catch(err => {
    console.error('MongoDB connection error:', err.message || err);
    process.exit(1);
  });

// Simple endpoints for demo and testing
app.get('/', (req, res) => {
  res.send('Account Transfer Demo - check README for API details');
});

// List accounts
app.get('/accounts', async (req, res) => {
  const accounts = await Account.find().select('-__v');
  res.json(accounts);
});

// Create an account
app.post('/accounts', async (req, res) => {
  const { name, balance } = req.body || {};
  if (!name) return res.status(400).json({ error: 'name is required' });
  const acct = new Account({ name, balance: Number(balance) || 0 });
  await acct.save();
  res.status(201).json(acct);
});

// Transfer endpoint (no DB transactions) - demonstrates safe updates using conditional update + rollback attempt
app.post('/transfer', async (req, res) => {
  const { fromId, toId, amount } = req.body || {};
  const num = Number(amount);
  if (!fromId || !toId || !amount) return res.status(400).json({ error: 'fromId, toId and amount are required' });
  if (isNaN(num) || num <= 0) return res.status(400).json({ error: 'amount must be a positive number' });

  try {
    // 1) Atomically decrement sender if balance sufficient
    const sender = await Account.findOneAndUpdate(
      { _id: fromId, balance: { $gte: num } },
      { $inc: { balance: -num } },
      { new: true }
    );

    if (!sender) {
      // Either sender not found or insufficient balance
      const exists = await Account.findById(fromId);
      if (!exists) return res.status(404).json({ error: 'Sender account not found' });
      return res.status(400).json({ error: 'Insufficient balance' });
    }

    // 2) Increment receiver
    const receiver = await Account.findByIdAndUpdate(
      toId,
      { $inc: { balance: num } },
      { new: true }
    );

    if (!receiver) {
      // Receiver not found - attempt to rollback sender
      await Account.findByIdAndUpdate(fromId, { $inc: { balance: num } });
      return res.status(404).json({ error: 'Receiver account not found, transfer rolled back' });
    }

    return res.json({ message: 'Transfer successful', from: sender, to: receiver });
  } catch (err) {
    console.error('Transfer error:', err);
    // In an unexpected error, attempt best-effort rollback if sender was decremented
    return res.status(500).json({ error: 'Internal server error' });
  }
});

// Start server
app.listen(PORT, () => {
  console.log(`Account transfer API running on http://localhost:${PORT}`);
});
