const express = require('express');
const jwt = require('jsonwebtoken');

const app = express();
app.use(express.json());

// Hardcoded credentials for this exercise
const HARD_USER = { username: 'user1', password: 'password123' };
// In a real app, store this in env vars or a secrets manager
const JWT_SECRET = process.env.JWT_SECRET || 'supersecret_jwt_key_for_exercise_only';

// Simple in-memory account state
let account = {
  owner: HARD_USER.username,
  balance: 1000.0
};

// Helper to create tokens
function createToken(payload) {
  // token valid for 1 hour
  return jwt.sign(payload, JWT_SECRET, { expiresIn: '1h' });
}

// Middleware to verify Bearer token in Authorization header
function authenticateToken(req, res, next) {
  const authHeader = req.headers['authorization'] || req.headers['Authorization'];
  if (!authHeader) return res.status(401).json({ error: 'Authorization header missing' });

  const parts = authHeader.split(' ');
  if (parts.length !== 2 || parts[0] !== 'Bearer') {
    return res.status(401).json({ error: 'Invalid Authorization format. Expected: Bearer <token>' });
  }

  const token = parts[1];
  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) return res.status(403).json({ error: 'Invalid or expired token' });
    req.user = user; // attach token payload
    next();
  });
}

// Public login route
app.post('/login', (req, res) => {
  const { username, password } = req.body || {};
  if (!username || !password) return res.status(400).json({ error: 'username and password required' });

  if (username === HARD_USER.username && password === HARD_USER.password) {
    const token = createToken({ username });
    return res.json({ token });
  }

  return res.status(401).json({ error: 'Invalid credentials' });
});

// Protected routes
app.get('/balance', authenticateToken, (req, res) => {
  // For exercise, only the hardcoded user can access
  if (req.user.username !== account.owner) return res.status(403).json({ error: 'Forbidden' });
  res.json({ balance: account.balance });
});

app.post('/deposit', authenticateToken, (req, res) => {
  const { amount } = req.body || {};
  if (req.user.username !== account.owner) return res.status(403).json({ error: 'Forbidden' });
  const num = Number(amount);
  if (!amount || isNaN(num) || num <= 0) return res.status(400).json({ error: 'amount must be a positive number' });
  account.balance += num;
  res.json({ balance: account.balance, message: `Deposited ${num}` });
});

app.post('/withdraw', authenticateToken, (req, res) => {
  const { amount } = req.body || {};
  if (req.user.username !== account.owner) return res.status(403).json({ error: 'Forbidden' });
  const num = Number(amount);
  if (!amount || isNaN(num) || num <= 0) return res.status(400).json({ error: 'amount must be a positive number' });
  if (num > account.balance) return res.status(400).json({ error: 'Insufficient balance' });
  account.balance -= num;
  res.json({ balance: account.balance, message: `Withdrew ${num}` });
});

// Generic 404
app.use((req, res) => {
  res.status(404).json({ error: 'Not found' });
});

const PORT = process.env.PORT || 3000;

// Start server and handle common listen errors (EADDRINUSE)
const server = app.listen(PORT, () => {
  console.log(`Banking API with JWT running on http://localhost:${PORT}`);
});

server.on('error', (err) => {
  if (err && err.code === 'EADDRINUSE') {
    console.error(`Port ${PORT} is already in use. Another server may be running.`);
    console.error('If you started the server previously, stop that process or pick a different port:');
    console.error(`  - Find process: netstat -ano | Select-String ":${PORT}"`);
    console.error('  - Or run this PowerShell to test: Test-NetConnection -ComputerName 127.0.0.1 -Port ' + PORT);
    process.exit(1);
  }
  // otherwise rethrow
  throw err;
});

// Graceful shutdown on Ctrl+C
process.on('SIGINT', () => {
  console.log('\nShutting down server...');
  server.close(() => process.exit(0));
});
