const express = require('express');
const jwt = require('jsonwebtoken');

const app = express();
app.use(express.json());

// JWT secret key
const JWT_SECRET = 'your-secret-key';

// Sample user (for demonstration purposes)
const sampleUser = {
    username: 'testuser',
    password: 'password123'
};

// Middleware to verify JWT token
const verifyToken = (req, res, next) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1]; // Bearer TOKEN

    if (!token) {
        return res.status(401).json({ message: 'Access denied. No token provided.' });
    }

    try {
        const verified = jwt.verify(token, JWT_SECRET);
        req.user = verified;
        next();
    } catch (error) {
        res.status(400).json({ message: 'Invalid token.' });
    }
};

// Login route - generates JWT token
app.post('/api/login', (req, res) => {
    const { username, password } = req.body;

    // Check if username and password match (basic authentication)
    const user = findUser(username);
    if (user && user.password === password) {
        // Generate JWT token
        const token = jwt.sign({ id: user.id, username: user.username }, JWT_SECRET, {
            expiresIn: '1h' // Token expires in 1 hour
        });

        res.json({
            message: 'Login successful',
            token
        });
    } else {
        res.status(401).json({ message: 'Invalid username or password' });
    }
});

// Protected route - requires valid JWT token
app.get('/api/protected', verifyToken, (req, res) => {
    res.json({
        message: 'This is a protected route',
        user: req.user
    });
});

// Login route
app.post('/login', (req, res) => {
    const { username, password } = req.body;

    // Verify credentials
    if (username === sampleUser.username && password === sampleUser.password) {
        // Create and sign a JWT token
        const token = jwt.sign({ username: sampleUser.username }, JWT_SECRET, {
            expiresIn: '1h'
        });

        res.json({ token });
    } else {
        res.status(401).json({ message: 'Invalid credentials' });
    }
});

// Protected route
app.get('/protected', verifyToken, (req, res) => {
    res.json({
        message: 'This is a protected route',
        user: req.user
    });
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});