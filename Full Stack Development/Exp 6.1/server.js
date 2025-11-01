const express = require('express');
const app = express();
const PORT = process.env.PORT || 3000;

// Logging middleware - applied globally
function logger(req, res, next) {
  const now = new Date().toISOString();
  console.log(`[${now}] ${req.method} ${req.originalUrl}`);
  next();
}

// Bearer token authentication middleware
function authenticateBearer(req, res, next) {
  const authHeader = req.get('Authorization') || req.get('authorization');
  if (!authHeader) {
    return res.status(401).json({ error: 'Authorization header missing' });
  }

  const parts = authHeader.split(' ');
  if (parts.length !== 2 || parts[0] !== 'Bearer') {
    return res.status(400).json({ error: 'Malformed Authorization header. Expected: "Bearer <token>"' });
  }

  const token = parts[1];
  if (token !== 'mysecrettoken') {
    return res.status(403).json({ error: 'Invalid token' });
  }

  // Token valid
  next();
}

// Apply logger globally
app.use(logger);

// Public route
app.get('/', (req, res) => {
  res.json({ message: 'Public route: no authentication required.' });
});

// Protected route
app.get('/protected', authenticateBearer, (req, res) => {
  res.json({ message: 'Protected route: access granted with valid token.' });
});

app.listen(PORT, () => {
  console.log(`Express server listening on http://localhost:${PORT}`);
});
