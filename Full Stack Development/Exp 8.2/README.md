# Protected Routes with JWT Verification

This project demonstrates how to implement protected routes using JSON Web Tokens (JWT) in a Node.js and Express.js application.

## Features

- JWT-based authentication
- Protected routes that require valid JWT tokens
- Public routes that don't require authentication
- Middleware for token verification
- Sample user authentication

# JWT Authentication Demo

This is a simple demonstration of JWT-based authentication in Node.js and Express.js.

## Running the Server

### Starting the Server
1. Open a terminal in the project directory
2. Run the following command:
```bash
node server.js
```
3. You should see the message: "Server running on port 3000"

### Stopping the Server
You can stop the server in two ways:
1. Press `Ctrl + C` in the terminal where the server is running
   - This is the recommended way to stop the server

2. Alternative method (if Ctrl + C doesn't work):
```bash
taskkill /F /IM node.exe
```
Note: The alternative method will stop all running Node.js processes on your system.

## Setup

1. Install dependencies:
   ```bash
   npm install
   ```

2. Start the server:
   ```bash
   node server.js
   ```

## API Endpoints

### Public Route
- GET `/api/public`
  - No authentication required
  - Returns a public message

### Login Route
- POST `/api/login`
  - Body: 
    ```json
    {
        "username": "testuser",
        "password": "password123"
    }
    ```
  - Returns JWT token on successful login

### Protected Route
- GET `/api/protected`
  - Requires JWT token in Authorization header
  - Format: `Bearer <your-token>`
  - Returns protected data and user information

## Testing the API

1. First, get a token by logging in:
   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"password123"}' http://localhost:3000/api/login
   ```

2. Access the protected route using the token:
   ```bash
   curl -H "Authorization: Bearer <your-token>" http://localhost:3000/api/protected
   ```

3. Access the public route:
   ```bash
   curl http://localhost:3000/api/public
   ```

## Security Notes

- In a production environment, never store JWT_SECRET in the code
- Use environment variables for sensitive data
- Store user credentials in a secure database
- Use proper password hashing
- Implement proper error handling
- Consider implementing token refresh mechanism
- Use HTTPS in production